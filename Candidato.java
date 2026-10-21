public class Candidato {

    //criando os atributos da classe candidato
    private static int proximoId = 1;
    private final int id;
    private String nome;
    private Estado estado;
    private Sexo sexo;
    private Sexo sexoInteresse;
    private ListaIdentificadores<Interesse> interesses;
    private ListaIdentificadores<Integer> companheiros;

    //método para criar o candidato
    public Candidato(String nome, Estado estado, Sexo sexo, Sexo sexoInteresse ){
        this.id = proximoId++;
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

    public void inserePotencialCompanheiro(Candidato u, Candidato v) {
        if(verificaPotencialCompanheiro(u, v)) {
            u.companheiros.add(companheiros.getSize(), v.getId());
        }
    }

    public long numeroCompanheiros(Candidato u) {
        return this.companheiros.getSize();
    }

    public String listaCompanheiros(Candidato u) {
        return u.companheiros.toString();
    }

    public boolean temInteresse(Candidato u, int id) {
        // precisa implementar lógica de SEARCH
        return false;
    }

    public boolean reside(Candidato u, String sigla) {
        if(sigla.equals(u.getEstado().getSigla()))
            return true;
        return false;
    }

    //verifica potenciais companheiros
    public boolean verificaPotencialCompanheiro(Candidato u, Candidato v) {

        // Verifica se residem no mesmo estado.
        boolean mesmoEstado = u.getEstado().equals(v.getEstado());

        boolean interesseDeU = u.getSexoInteresse() == v.getSexo()
                || u.getSexoInteresse() == Sexo.Indiferente;
        boolean interesseDeV = v.getSexoInteresse() == u.getSexo()
                || v.getSexoInteresse() == Sexo.Indiferente;

        return mesmoEstado && interesseDeU && interesseDeV;
        }

    public void adicionaInteresse(Interesse inter) {
        this.interesses.add(this.interesses.getSize(), inter);
    }

    @Override
    public String toString() {
        return "Nome: " +this.getNome();
    }
}
