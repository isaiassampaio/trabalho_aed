import java.util.Scanner;

import Enum.Interesse;
import Model.Candidato;
import Model.ListaIdentificadores;
import View.CandidatoView;

/** Controller da aplicação: coordena View e Model. */
public class App {
    private final Scanner entrada;
    private final CandidatoView view;
    private final ListaIdentificadores<Candidato> candidatos = new ListaIdentificadores<>();
    private int quantidade;
    private static int GRAU_MINIMO_INTERESSE = 2;

    public App(Scanner entrada) {
        this.entrada = entrada;
        this.view = new CandidatoView(entrada);
        this.quantidade = Dados.carregar(candidatos);
        calcularTodosCompanheiros();
    }

    public void iniciar() {
        int opcao;
        do {
            view.exibirMenu();
            opcao = view.lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    view.listar(candidatos, quantidade);
                    break;
                case 3:
                    listarMaisCompanheiros();
                    break;
                case 4:
                    candidatosSemCompanheiros();
                    break;
                case 5:
                    candidatosPorInteresse();
                    break;
                case 6:
                    candidatosPorEstado();
                    break;
                case 7:
                    atualizarCadastroPorId();
                    break;
                case 8:
                    GRAU_MINIMO_INTERESSE = view.grauMinimoInteresse();
                    calcularTodosCompanheiros();
                    break;
                case 9: 
                    potenciaisCompanheiros();
                    break;
                case 0:
                    view.mensagem("Encerrando.");
                    break;
                default:
                    view.mensagem("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }

    private Candidato cadastrar() {
        Candidato candidato = view.lerCandidato();
        if (candidato == null) {
            view.mensagem("Dados inválidos. Cadastro cancelado.");
            return null;
        }
        ListaIdentificadores<Interesse> interesses = view.lerInteresses();
        for(int i=0;i<interesses.getSize();i++) {
            candidato.adicionaInteresse(interesses.get(i));
        }
        candidatos.add(candidatos.getSize(), candidato);
        quantidade++;
        view.mensagem("Candidato cadastrado.");
        atualizarCompanheiros(candidato);
        return candidato;
    }

    private void atualizarCompanheiros(Candidato candidato) {
        for(int i=0;i<candidatos.getSize();i++) {
            if(candidatos.get(i).getId() != candidato.getId()) {
                candidatos.get(i).removerCompanheiro(candidato.getId());
            }
        }
        candidato.limparCompanheiros();

        for (int i = 0; i < candidatos.getSize(); i++) {
            if (candidato.getId() != candidatos.get(i).getId()) {
                if (candidato.verificaPotencialCompanheiro(candidato, candidatos.get(i), GRAU_MINIMO_INTERESSE)) {
                    candidato.inserePotencialCompanheiro(candidato, candidatos.get(i), GRAU_MINIMO_INTERESSE);
                    candidatos.get(i).inserePotencialCompanheiro(candidatos.get(i), candidato, GRAU_MINIMO_INTERESSE);
                }
            }
        }
    }

    private void calcularTodosCompanheiros() {
        for (int i = 0; i < candidatos.getSize(); i++) candidatos.get(i).limparCompanheiros();
        for (int i = 0; i < candidatos.getSize(); i++) {
            Candidato primeiro = candidatos.get(i);
            for (int j = i + 1; j < candidatos.getSize(); j++) {
                Candidato outro = candidatos.get(j);
                if (primeiro.verificaPotencialCompanheiro(primeiro, outro, GRAU_MINIMO_INTERESSE)) {
                    primeiro.inserePotencialCompanheiro(primeiro, outro, GRAU_MINIMO_INTERESSE);
                    outro.inserePotencialCompanheiro(outro, primeiro, GRAU_MINIMO_INTERESSE);
                }
            }
        }
    }

    private void atualizarCadastroPorId() {
        int id = view.lerInteiro("ID do candidato: ");
        int posicao = buscaPosicaoPorId(id);
        if (posicao < 0) {
            view.mensagem("Candidato não encontrado.");
            return;
        }
        Candidato candidato = candidatos.get(posicao);
        view.atualizaCadastro(candidato);
        atualizarCompanheiros(candidato);
    }

    private void listarMaisCompanheiros() {
        int k = view.lerInteiro("k: ");
        if (k < 1 || k > quantidade) {
            view.mensagem("K deve estar entre 1 e " + quantidade + ".");
            return;
        }

        ListaIdentificadores<Candidato> listaOrdenados = new ListaIdentificadores<>();
        for (int i = 0; i < candidatos.getSize(); i++) {
            Candidato atual = candidatos.get(i);
            int posicao = 0;
            while (posicao < listaOrdenados.getSize() && listaOrdenados.get(posicao)
                    .numeroCompanheiros(listaOrdenados.get(posicao)) >= atual.numeroCompanheiros(atual)) {
                posicao++;
            }
            listaOrdenados.add(posicao, atual);
        }
        view.listar(listaOrdenados, k);
    }

    public void candidatosSemCompanheiros() {
        ListaIdentificadores<Candidato> listaSemCompanheiros = new ListaIdentificadores<>();
        for (int i = 0; i < candidatos.getSize(); i++) {
            Candidato atual = candidatos.get(i);
            if (atual.numeroCompanheiros(atual) == 0) {
                listaSemCompanheiros.add(listaSemCompanheiros.getSize(), atual);
            }
        }

        if(listaSemCompanheiros.getSize() == 0) {
            view.mensagem("Nenhum candidato sem companheiros foi encontrado.");
            return;
        }

        view.listar(listaSemCompanheiros, Integer.valueOf(String.valueOf(listaSemCompanheiros.getSize())));
    }

    private void candidatosPorInteresse() {
        int idInteresse = view.lerInteiro("Digite o código da área de interesse (1 a 8): ");

        if (idInteresse < 1 || idInteresse > 8) {
            view.mensagem("Área de interesse inválida.");
            return;
        }

        ListaIdentificadores<Candidato> lista = new ListaIdentificadores<>();

        for (int i = 0; i < candidatos.getSize(); i++) {
            Candidato atual = candidatos.get(i);

            if (atual.temInteresse(atual, idInteresse)) {
                lista.add(lista.getSize(), atual);
            }
        }

        view.listarIdNome(lista);
    }

    //consulta os candidatos por estado
    private void candidatosPorEstado() {
        String sigla = view.lerEstado().getSigla();

        ListaIdentificadores<Candidato> lista = new ListaIdentificadores<>();

        for (int i = 0; i < candidatos.getSize(); i++) {
            Candidato atual = candidatos.get(i);

            if (atual.reside(atual, sigla)) {
                lista.add(lista.getSize(), atual);
            }
        }

        view.listarIdNome(lista);
    }

    private void potenciaisCompanheiros() {
        int id = view.lerId();
        Candidato atual = buscaCandidatoPorId(id);
        if(atual == null) {
            view.mensagem("Candidato não encontrado.");
            return;
        } 
        ListaIdentificadores<Candidato> lista = new ListaIdentificadores<>();

        ListaIdentificadores<Integer> companheiros = atual.listaCompanheiros();
        for(int i=0;i<companheiros.getSize();i++) {
            Candidato candidato = buscaCandidatoPorId(companheiros.get(i));
            if(candidato != null) {
                lista.add(lista.getSize(), candidato);
            } 
        }

        view.listarIdNome(lista);
    }

    private int buscaPosicaoPorId(int id) {
        for(int posicao=0;posicao<candidatos.getSize(); posicao++) {
            if(candidatos.get(posicao).getId() == id) {
                return posicao;
            }
        }
        return -1;
    }

    private Candidato buscaCandidatoPorId(int id) {
        for(int i=0;i<candidatos.getSize();i++) {
            if(candidatos.get(i).getId() == id) {
                return candidatos.get(i);
            }
        }
        return null;
    }
}
