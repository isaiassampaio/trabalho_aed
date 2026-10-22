package Model;

import Enum.Interesse;
import Enum.Estado;
import Enum.Sexo;

public class Candidato {

    // criando os atributos da classe candidato
    private static int proximoId = 1;
    private final int id;
    private String nome;
    private Estado estado;
    private Sexo sexo;
    private Sexo sexoInteresse;
    private ListaIdentificadores<Interesse> interesses;
    private ListaIdentificadores<Integer> companheiros;

    // método para criar o candidato
    public Candidato(String nome, Estado estado, Sexo sexo, Sexo sexoInteresse) {
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

    public ListaIdentificadores<Integer> listaCompanheiros() {
        ListaIdentificadores<Integer> copia = new ListaIdentificadores<>();
        for (int i = 0; i < companheiros.getSize(); i++) {
            copia.add(copia.getSize(), companheiros.get(i));
        }
        return copia;
    }

    // método para atualizar o cadastro do candidato
    public void atualizaCadastro(String nome, Estado estado, Sexo sexo, Sexo sexoInteresse) {
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = sexoInteresse;
    }

    public void inserePotencialCompanheiro(Candidato u, Candidato v, int grau) {
        if (!verificaPotencialCompanheiro(u, v, grau)) {
            return;
        }

        for (int i = 0; i < u.companheiros.getSize(); i++) {
            if (u.companheiros.get(i) == v.getId()) {
                return;
            }
        }

        u.companheiros.add(u.companheiros.getSize(), v.getId());
    }

    public long numeroCompanheiros(Candidato u) {
        return u.companheiros.getSize();
    }

    public void limparCompanheiros() {
        companheiros = new ListaIdentificadores<>();
    }

    public void removerCompanheiro(int id) {
        for (int i = 0; i < companheiros.getSize();) {
            if (companheiros.get(i) == id) companheiros.remove(i);
            else i++;
        }
    }
    
    public boolean temInteresse(Candidato u, int id) {
        for (int i = 0; i < u.interesses.getSize(); i++) {
            Interesse interesseAtual = u.interesses.get(i);

            if (interesseAtual.ordinal() + 1 == id) {
                return true;
            }
        }

        return false;
    }

    public boolean reside(Candidato u, String sigla) {
        if (u == null || u.getEstado() == null || sigla == null) {
            return false;
        }

                return sigla.equalsIgnoreCase(u.getEstado().getSigla());
    }

    // verifica potenciais companheiros
    public boolean verificaPotencialCompanheiro(Candidato u, Candidato v, int grau) {

        if (u == null || v == null || u.getId() == v.getId()) return false;

        // Verifica se residem no mesmo estado.
        boolean mesmoEstado = reside(u, v.getEstado().getSigla());

        boolean interesseDeU = u.getSexoInteresse() == v.getSexo()
                || u.getSexoInteresse() == Sexo.getSexo("I");
        boolean interesseDeV = v.getSexoInteresse() == u.getSexo()
                || v.getSexoInteresse() == Sexo.getSexo("I");
        boolean grauMinimo = u.calculaInteresseEmComun(v) >= grau;

        return mesmoEstado && interesseDeU && interesseDeV && grauMinimo;
    }

    public void limparInteresses() {
        interesses = new ListaIdentificadores<>();
    }

    public void adicionaInteresse(Interesse inter) {
        for(int i=0;i<interesses.getSize();i++) {
            if(interesses.get(i) == inter) {
                return;
            }
        }

        interesses.add(interesses.getSize(), inter);
    }

    public int calculaInteresseEmComun(Candidato outro) {
        int pontuacao = 0;

        for (int i = 0; i < interesses.getSize(); i++) {
            Interesse interesseAtual = interesses.get(i);

            for (int j = 0; j < outro.interesses.getSize(); j++) {
                if (interesseAtual == outro.interesses.get(j)) {
                    switch (interesseAtual) {
                        case ESPORTES:
                        case MUSICA:
                        case CINEMA:
                            pontuacao += 1;
                            break;
                        case ARTES:
                        case ANIMAIS:
                        case GASTRONOMIA:
                            pontuacao += 2;
                            break;
                        case TECNOLOGIA:
                        case CIENCIAS:
                            pontuacao += 3;
                            break;
                    }
                    break;
                }
            }
        }
        return pontuacao;
    }
}
