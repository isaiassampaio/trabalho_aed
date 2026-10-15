
public class Dados {

    // classe para pré cadastrar os candidatos no sistema
    public static int carregar(Candidato[] candidatos) {
        int quantidade = 0;

        candidatos[quantidade++] = new Candidato(
                1, "Lucas Godoy", Estado.MINAS_GERAIS,
                Sexo.MASCULINO, Sexo.FEMININO);

        candidatos[quantidade++] = new Candidato(
                2, "Ana Silva", Estado.MINAS_GERAIS,
                Sexo.FEMININO, Sexo.MASCULINO);

        candidatos[quantidade++] = new Candidato(
                3, "Pedro Santos", Estado.SAO_PAULO,
                Sexo.MASCULINO, Sexo.INDIFERENTE);

        candidatos[quantidade++] = new Candidato(
                4, "Mariana Costa", Estado.SAO_PAULO,
                Sexo.FEMININO, Sexo.INDIFERENTE);

        candidatos[quantidade++] = new Candidato(
                5, "João Oliveira", Estado.RIO_DE_JANEIRO,
                Sexo.MASCULINO, Sexo.FEMININO);

        candidatos[quantidade++] = new Candidato(
                6, "Beatriz Souza", Estado.RIO_DE_JANEIRO,
                Sexo.FEMININO, Sexo.MASCULINO);

        candidatos[quantidade++] = new Candidato(
                7, "Gabriel Lima", Estado.MINAS_GERAIS,
                Sexo.MASCULINO, Sexo.MASCULINO);

        candidatos[quantidade++] = new Candidato(
                8, "Juliana Ferreira", Estado.BAHIA,
                Sexo.FEMININO, Sexo.INDIFERENTE);

        candidatos[quantidade++] = new Candidato(
                9, "Rafael Almeida", Estado.PARANA,
                Sexo.MASCULINO, Sexo.INDIFERENTE);

        candidatos[quantidade++] = new Candidato(
                10, "Camila Rodrigues", Estado.BAHIA,
                Sexo.FEMININO, Sexo.FEMININO);

        candidatos[0].adicionaInteresse(Interesse.ESPORTES);
        candidatos[0].adicionaInteresse(Interesse.MUSICA);
        candidatos[0].adicionaInteresse(Interesse.TECNOLOGIA);

        candidatos[1].adicionaInteresse(Interesse.ESPORTES);
        candidatos[1].adicionaInteresse(Interesse.MUSICA);
        candidatos[1].adicionaInteresse(Interesse.ANIMAIS);

        candidatos[2].adicionaInteresse(Interesse.CINEMA);
        candidatos[2].adicionaInteresse(Interesse.TECNOLOGIA);
        candidatos[2].adicionaInteresse(Interesse.CIENCIAS);

        candidatos[3].adicionaInteresse(Interesse.CINEMA);
        candidatos[3].adicionaInteresse(Interesse.ARTES);
        candidatos[3].adicionaInteresse(Interesse.CIENCIAS);

        candidatos[4].adicionaInteresse(Interesse.ESPORTES);
        candidatos[4].adicionaInteresse(Interesse.GASTRONOMIA);
        candidatos[4].adicionaInteresse(Interesse.MUSICA);

        candidatos[5].adicionaInteresse(Interesse.ESPORTES);
        candidatos[5].adicionaInteresse(Interesse.GASTRONOMIA);
        candidatos[5].adicionaInteresse(Interesse.ANIMAIS);

        candidatos[6].adicionaInteresse(Interesse.TECNOLOGIA);
        candidatos[6].adicionaInteresse(Interesse.CIENCIAS);
        candidatos[6].adicionaInteresse(Interesse.CINEMA);

        candidatos[7].adicionaInteresse(Interesse.ARTES);
        candidatos[7].adicionaInteresse(Interesse.MUSICA);
        candidatos[7].adicionaInteresse(Interesse.ANIMAIS);

        candidatos[8].adicionaInteresse(Interesse.TECNOLOGIA);
        candidatos[8].adicionaInteresse(Interesse.CIENCIAS);
        candidatos[8].adicionaInteresse(Interesse.GASTRONOMIA);

        candidatos[9].adicionaInteresse(Interesse.ARTES);
        candidatos[9].adicionaInteresse(Interesse.MUSICA);
        candidatos[9].adicionaInteresse(Interesse.CINEMA);

        return quantidade;

    }
}
