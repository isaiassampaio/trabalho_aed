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

    public Candidato lerCandidato() {
        System.out.print("Nome: ");
        String nome = scan.nextLine();

        System.out.print("Estado (sigla): ");
        Estado estado = null;
        while (estado == null) {
            String siglaEstado = scan.nextLine().trim().toUpperCase();
            estado = Estado.getEstado(siglaEstado);

            if (estado == null) {
                System.out.println("Estado inválido! Digite uma sigla válida:");
            }
        }

        System.out.print("Sexo (F/M): ");
        String siglaSexo = scan.nextLine().toUpperCase();
        Sexo sexo = Sexo.getSexo(siglaSexo);

        System.out.print("F - Feminino\nM - Masculino\nI - Indiferente\n\n Interesse: ");
        String siglaInteresse = scan.nextLine().toUpperCase();
        Sexo sexoInteresse = Sexo.getSexo(siglaInteresse);

        return new Candidato(nome, estado, sexo, sexoInteresse);
    }

    public void lerInteresses(Candidato candidato) {
        System.out.print("1 - Esportes\n2 - Artes\n3 - Música\n4 - Cinema\n"
                + "5 - Tecnologia\n6 - Animais\n7 - Gastronomia\n8 - Ciências\n\n"
                + "Separe por ',' os interesses: ");
        String texto = scan.nextLine();
        for (String item : texto.split(",")) {
            try {
                int opcao = Integer.parseInt(item.trim());
                if (opcao >= 1 && opcao <= Interesse.values().length) {
                    candidato.adicionaInteresse(Interesse.values()[opcao - 1]);
                }
            } catch (NumberFormatException ignorado) {

            }
        }
    }

    public void exibirMenu() {
        System.out.println("\n=== SISTEMA DE RELACIONAMENTOS ===");
        System.out.println("1 - Atualizar Cadastro\n2 - Listar candidatos");
        System.out.println("3. Imprimir id/nome dos K candidatos com maior número de potenciais companheiros;");
        System.out.println("4. Imprimir id/nome dos candidatos para os quais nenhum companheiro foi encontrado;");
        System.out.println("5. Imprimir id/nome de candidatos com interesse em uma determinada área;");
        System.out.println("6. Imprimir id/nome de candidatos que residem em um determinado estado.");
        System.out.println("7 - Atualizar o candidato cadastrado ao iniciar");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void listar(ListaIdentificadores<Candidato> candidatos, int quantidade) {
        if (quantidade == 0) {
            mensagem("Nenhum candidato cadastrado.");
            return;
        }
        for (int i = 0; i < quantidade; i++) {
            Candidato c = candidatos.get(i);
            System.out.printf("ID: %d | Nome: %s | Estado: %s | Sexo: %s | Interesse: %s%n", c.getId(), c.getNome(),
                    c.getEstado(), c.getSexo(), c.getSexoInteresse());
        }
    }

    public void mensagem(String texto) {
        System.out.println(texto);
    }

    public void atualizaCadastro(Candidato candidato) {
        System.out.print("Nome: ");
        String nome = scan.nextLine();

        System.out.print("Estado (sigla): ");
        String siglaEstado = scan.nextLine().toUpperCase();
        Estado estado = Estado.getEstado(siglaEstado);

        System.out.print("Sexo (F/M): ");
        String siglaSexo = scan.nextLine().toUpperCase();
        Sexo sexo = Sexo.getSexo(siglaSexo);

        System.out.print("F - Feminino\nM - Masculino\nI - Indiferente\n\n Interesse: ");
        String siglaInteresse = scan.nextLine().toUpperCase();
        Sexo sexoInteresse = Sexo.getSexo(siglaInteresse);

        candidato.atualizaCadastro(nome, estado, sexo, sexoInteresse);
    }

    public void listarIdNome(ListaIdentificadores<Candidato> lista) {
        System.out.println("\n ---- Candidatos Encontrados ----");

        for (int i = 0; i < lista.getSize(); i++) {
            Candidato candidato = lista.get(i);

            System.out.println("ID: " + candidato.getId() + "| Nome: " + candidato.getNome());
        }

        System.out.println("\nTotal de candidatos encontrados " + lista.getSize());
    }
}
