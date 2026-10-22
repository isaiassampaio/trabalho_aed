package View;

import java.util.Scanner;
import Enum.Estado;
import Enum.Interesse;
import Enum.Sexo;
import Model.Candidato;
import Model.ListaIdentificadores;

public class CandidatoView {
    private final Scanner scan;

    public CandidatoView(Scanner scan) {
        this.scan = scan;
    }

    public int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            String texto = scan.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                mensagem("Digite um número inteiro válido.");
            }
        }
    }

    private String lerNome() {
        while (true) {
            System.out.print("Nome: ");
            String nome = scan.nextLine().trim();
            if (!nome.isEmpty()) return nome;
            mensagem("O nome não pode ficar vazio.");
        }
    }

    public Estado lerEstado() {
        while (true) {
            System.out.print("Estado (sigla): ");
            Estado estado = Estado.getEstado(scan.nextLine().trim().toUpperCase());
            if (estado != null) return estado;
            mensagem("Estado inválido. Digite uma sigla válida.");
        }
    }

    private Sexo lerSexo(boolean preferencia) {
        while (true) {
            System.out.print(preferencia ? "Sexo de interesse (F/M/I): " : "Sexo (F/M): ");
            String texto = scan.nextLine().trim().toUpperCase();
            if (texto.equals("F") || texto.equals("M") || (preferencia && texto.equals("I"))) {
                return Sexo.getSexo(texto);
            }
            mensagem("Valor inválido. " + (preferencia ? "Digite F, M ou I." : "Digite F ou M."));
        }
    }

    public Candidato lerCandidato() {
        String nome = lerNome();
        Estado estado = lerEstado();
        Sexo sexo = lerSexo(false);
        Sexo preferencia = lerSexo(true);
        return new Candidato(nome, estado, sexo, preferencia);
    }

    public ListaIdentificadores<Interesse> lerInteresses() {
        System.out.println("1 - Esportes\n2 - Artes\n3 - Música\n4 - Cinema\n"
                + "5 - Tecnologia\n6 - Animais\n7 - Gastronomia\n8 - Ciências");
        while (true) {
            System.out.print("Separe os interesses por vírgula (vazio para nenhum): ");
            String texto = scan.nextLine().trim();
            ListaIdentificadores<Interesse> lista = new ListaIdentificadores<>();
            if (texto.isEmpty()) return lista;
            boolean[] escolhidos = new boolean[Interesse.values().length];
            boolean valido = true;
            for (String item : texto.split(",", -1)) {
                try {
                    int codigo = Integer.parseInt(item.trim());
                    if (codigo < 1 || codigo > escolhidos.length || escolhidos[codigo - 1]) {
                        valido = false;
                        break;
                    }
                    escolhidos[codigo - 1] = true;
                    lista.add(lista.getSize(), Interesse.values()[codigo - 1]);
                } catch (NumberFormatException e) {
                    valido = false;
                    break;
                }
            }
            if (valido) return lista;
            mensagem("Informe códigos de 1 a 8, sem repetições, separados por vírgula.");
        }
    }

    public void exibirMenu() {
        System.out.println("\n=== SISTEMA DE RELACIONAMENTOS ===");
        System.out.println("1 - Cadastrar candidato\n2 - Listar candidatos");
        System.out.println("3 - Candidatos com mais potenciais companheiros");
        System.out.println("4 - Candidatos sem companheiros");
        System.out.println("5 - Candidatos por área de interesse");
        System.out.println("6 - Candidatos por estado");
        System.out.println("7 - Atualizar cadastro por ID");
        System.out.println("8 - Alterar grau mínimo de interesse");
        System.out.println("9 - Consultar potenciais companheiros por ID");
        System.out.println("0 - Sair");
    }

    public void listar(ListaIdentificadores<Candidato> candidatos, int quantidade) {
        if (quantidade == 0) {
            mensagem("Nenhum candidato cadastrado.");
            return;
        }
        for (int i = 0; i < quantidade; i++) {
            Candidato c = candidatos.get(i);
            System.out.println("ID: " + c.getId() + "| Nome: " + c.getNome());
        }
    }

    public void mensagem(String texto) {
        System.out.println(texto);
    }

    public void atualizaCadastro(Candidato candidato) {
        String nome = lerNome();
        Estado estado = lerEstado();
        Sexo sexo = lerSexo(false);
        Sexo preferencia = lerSexo(true);
        ListaIdentificadores<Interesse> interesses = lerInteresses();
        candidato.atualizaCadastro(nome, estado, sexo, preferencia);
        candidato.limparInteresses();
        for (int i = 0; i < interesses.getSize(); i++) {
            candidato.adicionaInteresse(interesses.get(i));
        }
    }

    public void listarIdNome(ListaIdentificadores<Candidato> lista) {
        System.out.println("\n ---- Candidatos Encontrados ----");
        for (int i = 0; i < lista.getSize(); i++) {
            Candidato candidato = lista.get(i);
            System.out.println("ID: " + candidato.getId() + "| Nome: " + candidato.getNome());
        }
        System.out.println("\nTotal de candidatos encontrados " + lista.getSize());
    }

    public int grauMinimoInteresse() {
        while (true) {
            int grau = lerInteiro("Grau mínimo (0 a 15): ");
            if (grau >= 0 && grau <= 15) return grau;
            mensagem("O grau deve estar entre 0 e 15.");
        }
    }

    public int lerId() {
        return lerInteiro("ID: ");
    }
}
