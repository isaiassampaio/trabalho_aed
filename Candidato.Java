public class Candidato {

    //criando os atributos da classe candidato
    private int id;
    private String nome;
    private String estado;
    private char sexo;
    private char sexoInteresse;

    //achei mais interessante criar uma classe ListaIdentificadores para conseguir manipular as listas
    private ListaIdentificadores interesses;
    private ListaIdentificadores companheiros;

    //método para criar o candidato
    public Candidato(int id, String nome, String estado, char sexo, char sexoInteresse ){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = sexoInteresse;
        interesses = new ListaIdentificadores();
        companheiros = new ListaIdentificadores();
    }

     public int getId() {
        return id;
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

    //método para atualizar o cadastro do candidato
    public void atualizaCadastro(String nome, String estado, char sexo, char sexoInteresse) {
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = sexoInteresse;
    }

    //verifica potenciais companheiros
    public boolean verificaPotencialCompanheiro(Candidato u, Candidato v) {

        //verifica se residem no mesmo estado
        if (u.getEstado().equals(v.getEstado())){
            System.out.println("São do mesmo estado");
            return true;
        }else{
            System.out.println("Não são do mesmo estado");
            return false;
        }




        //verifica se o sexo de interesse do candidato u é = ao sexo do candidato v, ou se o sexo de interesse de u = I. Também verifica a mesma coisa para o candidato v
        if (u.getSexoInteresse() == v.getSexo()
            || u.getSexoInteresse() == 'I') {
        if (v.getSexoInteresse() == u.getSexo()
                || v.getSexoInteresse() == 'I') {
            return true;
        }
    }
    return false;






}
