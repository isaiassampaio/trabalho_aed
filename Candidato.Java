public class Candidato {

    //criando os atributos da classe candidato
    private int id;
    private String nome;
    private String estado;
    private Char sexo;
    private Char sexoInteresse;

    //achei mais interessante criar uma classe ListaIdentificadores para conseguir manipular as listas
    private ListaIdentificadores interesses;
    private ListaIdentificadores companheiros;

    //construtor da classe candidato
    Public Candidato(int id, String nome, String estado, Char sexo; Char sexoInteresse ){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = sexoInteresse;
        interesses = new ListaIdentificadores();
        companheiros = new ListaIdentificadores();
    }

     public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public char getSexo() {
        return sexo;
    }

    public char getSexoInteresse() {
        return sexoInteresse;
    }

        




}
