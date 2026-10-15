import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // public Candidato(int id, String nome, String estado, char sexo, char
        // sexoInteresse ){
        Scanner entrada = new Scanner(System.in);

        // cria o objeto candidatos
        Candidato[] candidatos = new Candidato[100];
        // ja inicia o programa com os candidatos pré cadastrados
        int qtdCandidato = Dados.carregar(candidatos);
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE RELACIONAMENTOS ===");
            System.out.println("1 - Cadastrar candidato");
            System.out.println("2 - Listar candidatos");
            System.out.println("3 - Consultar potenciais companheiros");
            System.out.println("4 - Listar candidatos sem companheiros");
            System.out.println("5 - Listar candidatos por interesse");
            System.out.println("6 - Listar candidatos por estado");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n---- Cadastro de Candidato----\n ");

                    if (qtdCandidato == candidatos.length) {
                        System.out.println("limite de candidatos atingido");
                        break;
                    }

                    System.out.println("--Nome:");
                    String nome = entrada.nextLine();
                    System.out.println();

                    System.out.println("--Estado (Por extenso, por exemplo: Minas Gerais):");
                    String nomeEstado = entrada.nextLine().trim();

                    Estado estado = null;

                    for (Estado e : Estado.values()) {
                        if (e.getNome().equalsIgnoreCase(nomeEstado)) {
                            estado = e;
                            break;

                        }
                    }

                    if (estado == null) {
                        System.out.println("Estado inválido.");
                        break;
                    }
                    System.out.println();

                    System.out.println("--Sexo (Masculino/Feminino):");
                    Sexo sexo = Sexo.valueOf(entrada.nextLine().toUpperCase());
                    System.out.println();

                    System.out.println("--Sexo de interesse (Masculino/Feminino/Indiferente):");
                    Sexo sexoInteresse = Sexo.valueOf(entrada.nextLine().toUpperCase());

                    candidatos[qtdCandidato] = new Candidato(qtdCandidato + 1, nome, estado, sexo, sexoInteresse);

                    qtdCandidato++;
                    System.out.println();

                    System.out.println("\n---- Candidato cadastrado.---- \n");
                    break;

                case 2:
                    System.out.println("\n---- Listar candidato ----\n");

                    if (qtdCandidato == 0) {
                        System.out.println("Nenhum Candidato Cadastrado");
                        break;
                    }

                    for (int i = 0; i < qtdCandidato; i++) {
                        Candidato c = candidatos[i];

                        System.out.println("ID: " + c.getId());
                        System.out.println("Nome: " + c.getNome());
                        System.out.println("Estado: " + c.getEstado());
                        System.out.println("Sexo: " + c.getSexo());
                        System.out.println("Sexo de interesse: " + c.getSexoInteresse());
                        System.out.println("-------------------------");
                    }
                    break;

                case 3:
                    System.out.println("Consultar potenciais companheiros");
                    break;

                case 4:
                    System.out.println("Listar candidatos sem companheiros");
                    break;

                case 5:
                    System.out.println("Listar candidatos por interesse");
                    break;

                case 6:
                    System.out.println("Listar candidatos por estado");
                    break;

                case 0:
                    System.out.println("Encerrando");

                default:
                    System.out.println("Opção inválida.");

            }
        } while (opcao != 0);
        {

            entrada.close();

        }

        Candidato c = new Candidato(1, "Isaías", Estado.MINAS_GERAIS, Sexo.MASCULINO, Sexo.FEMININO);
        c.adicionaInteresse(Interesse.ANIMAIS);

    }
}