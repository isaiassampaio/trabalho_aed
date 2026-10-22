import java.util.Scanner;

import Model.Candidato;
import Model.ListaIdentificadores;
import View.CandidatoView;

/** Controller da aplicação: coordena View e Model. */
public class App {
    private final Scanner entrada;
    private final CandidatoView view;
    private final ListaIdentificadores<Candidato> candidatos = new ListaIdentificadores<>();
    private int quantidade;
    private static final int GRAU_MINIMO_INTERESSE = 4;

    public App(Scanner entrada) {
        this.entrada = entrada;
        this.view = new CandidatoView(entrada);
        this.quantidade = Dados.carregar(candidatos);
    }

    public void iniciar() {
        Candidato candidato = cadastrar();
        atualizarCompanheiros(candidato);
        int opcao;
        do {
            view.exibirMenu();
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {
                case 1:
                    view.atualizaCadastro(candidato);
                    atualizarCompanheiros(candidato);
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
                    if (candidato != null) {
                        view.atualizaCadastro(candidato);
                        atualizarCompanheiros(candidato);
                    }
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
        view.lerInteresses(candidato);
        candidatos.add(candidatos.getSize(), candidato);
        quantidade++;
        view.mensagem("Candidato cadastrado.");
        return candidato;
    }

    private void atualizarCompanheiros(Candidato candidato) {
        candidato.limparCompanheiros();

        for (int i = 0; i < candidatos.getSize(); i++) {
            if (candidato.getId() != candidatos.get(i).getId()) {
                if (candidato.verificaPotencialCompanheiro(candidato, candidatos.get(i))
                        && candidato.calculaInteresseEmComun(candidatos.get(i)) >= GRAU_MINIMO_INTERESSE) {
                    candidato.inserePotencialCompanheiro(candidato, candidatos.get(i));
                    candidatos.get(i).inserePotencialCompanheiro(candidatos.get(i), candidato);
                }
            }
        }
    }

    private void listarMaisCompanheiros() {
        System.out.print("k: ");
        int k = entrada.nextInt();
        entrada.nextLine();
        while (k < 1 || k > quantidade) {
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
        view.listar(listaSemCompanheiros, Integer.valueOf(String.valueOf(listaSemCompanheiros.getSize())));
    }

    private void candidatosPorInteresse() {
        System.out.print("Digite o código da área de interesse (1 a 8): ");
        int idInteresse = entrada.nextInt();
        entrada.nextLine();

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
        System.out.print("Digite a sigla do estado: ");
        String sigla = entrada.nextLine().trim().toUpperCase();

        ListaIdentificadores<Candidato> lista = new ListaIdentificadores<>();

        for (int i = 0; i < candidatos.getSize(); i++) {
            Candidato atual = candidatos.get(i);

            if (atual.reside(atual, sigla)) {
                lista.add(lista.getSize(), atual);
            }
        }

        view.listarIdNome(lista);
    }
}
