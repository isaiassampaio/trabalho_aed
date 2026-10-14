public class Main {
    public static void main(String[] args) {
        //    public Candidato(int id, String nome, String estado, char sexo, char sexoInteresse ){

        Candidato c = new Candidato(1, "Isaías", Estado.MINAS_GERAIS, Sexo.Masculino, Sexo.Feminino);        
        c.adicionaInteresse(Interesse.ANIMAIS);
        
    }
}