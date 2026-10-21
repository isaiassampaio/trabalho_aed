import java.util.Scanner;

public class CandidatoView {
    public static Candidato lerCandidato() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scan.next();

        System.out.print("Estado (sigla): ");
        String estado = scan.next().toUpperCase();

        System.out.print("Sexo (F/M): ");
        String sexo = scan.next().toUpperCase();

        System.out.print("F - Feminino\nM - Masculino\nI - Indiferente\n\n Interesse: ");
        String sexoInteresse = scan.next().toUpperCase();

        Candidato candidato = new Candidato(nome, Estado.getEstado(estado), Sexo.getSexo(sexo), Sexo.getSexo(sexoInteresse));
        return candidato;
    }


    public static void lerInteresses(Candidato candidato) {
        Scanner scan = new Scanner(System.in);

        System.out.print("1 - Esportes\n2 - Artes\n3 - Música\n4 - Cinema\n5 - Tecnologia\n6 - Animais\n7 - Gastronomia\n8 - Ciências\n\nSepare por ',' os interesses: ");
        String entrada = scan.next();
        
        String[] interesses = entrada.split(",");

        for(String interesse : interesses) {
            int opcao = Integer.parseInt(interesse.trim());

            if(opcao >= 1 && opcao <= Interesse.values().length) {
                candidato.adicionaInteresse(Interesse.values()[opcao - 1]);
            }
        }
    }
}
