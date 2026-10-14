public class Candidato {

    //criando os atributos da classe candidato
    private int id;
    private String nome;
    private Estado estado;
    private Sexo sexo;
    private Sexo sexoInteresse;

    //achei mais interessante criar uma classe ListaIdentificadores para conseguir manipular as listas
    private ListaIdentificadores<Interesse> interesses;
    private ListaIdentificadores<Integer> companheiros;

    //método para criar o candidato
    public Candidato(int id, String nome, Estado estado, Sexo sexo, Sexo sexoInteresse ){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = sexoInteresse;
        this.interesses = new ListaIdentificadores<>();
        this.companheiros = new ListaIdentificadores<>();
    }

     public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Sexo getSexoInteresse() {
        return sexoInteresse;
    }

    //método para atualizar o cadastro do candidato
    public void atualizaCadastro(String nome, Estado estado, Sexo sexo, Sexo sexoInteresse) {
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = sexoInteresse;
    }

    //verifica potenciais companheiros
    public boolean verificaPotencialCompanheiro(Candidato u, Candidato v) {

        // Verifica se residem no mesmo estado.
        boolean mesmoEstado = u.getEstado().equals(v.getEstado());




        // Verifica se há interesse mútuo ('I' significa indiferente).
        boolean interesseDeU = u.getSexoInteresse() == v.getSexo()
                || u.getSexoInteresse() == Sexo.Indiferente;
        boolean interesseDeV = v.getSexoInteresse() == u.getSexo()
                || v.getSexoInteresse() == Sexo.Indiferente;

        return mesmoEstado && interesseDeU && interesseDeV;
        }

    public void adicionaInteresse(Interesse inter) {
        this.interesses.add(this.interesses.getSize(), inter);
    }
}
