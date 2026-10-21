public enum Sexo {
    Masculino('M'),
    Feminino('F'),
    Indiferente('I');

    private char sigla;

    Sexo(char sigla) {
        this.sigla = sigla;
    }

    public char getSigla() {
        return sigla;
    }

    public static Sexo getSexo(String sigla) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.getSigla() == sigla.charAt(0)) {
                return sexo;
            }
        }
        return null;
    }
}
