import Enum.Estado;
import Enum.Interesse;
import Enum.Sexo;
import Model.Candidato;
import Model.ListaIdentificadores;

public class Dados {

    // Cadastra 10 candidatos para cada um dos 27 estados brasileiros.
    public static int carregar(ListaIdentificadores<Candidato> candidatos) {
        Estado[] estados = {
            Estado.ACRE, Estado.ALAGOAS, Estado.AMAPA, Estado.AMAZONAS,
            Estado.BAHIA, Estado.CEARA, Estado.DISTRITO_FEDERAL,
            Estado.ESPIRITO_SANTO, Estado.GOIAS, Estado.MARANHAO,
            Estado.MATO_GROSSO, Estado.MATO_GROSSO_DO_SUL, Estado.MINAS_GERAIS,
            Estado.PARA, Estado.PARAIBA, Estado.PARANA, Estado.PERNAMBUCO,
            Estado.PIAUI, Estado.RIO_DE_JANEIRO, Estado.RIO_GRANDE_DO_NORTE,
            Estado.RIO_GRANDE_DO_SUL, Estado.RONDONIA, Estado.RORAIMA,
            Estado.SANTA_CATARINA, Estado.SAO_PAULO, Estado.SERGIPE,
            Estado.TOCANTINS
        };

        String[] gentilicos = {
            "Acreano", "Alagoano", "Amapaense", "Amazonense", "Baiano",
            "Cearense", "Brasiliense", "Capixaba", "Goiano", "Maranhense",
            "Mato-Grossense", "Sul-Mato-Grossense", "Mineiro", "Paraense",
            "Paraibano", "Paranaense", "Pernambucano", "Piauiense", "Carioca",
            "Potiguar", "Gaucho", "Rondoniense", "Roraimense", "Catarinense",
            "Paulista", "Sergipano", "Tocantinense"
        };

        String[] primeirosNomes = {
            "Lucas", "Ana", "Pedro", "Mariana", "Joao",
            "Beatriz", "Gabriel", "Juliana", "Rafael", "Camila",
            "Mateus", "Larissa", "Felipe", "Amanda", "Thiago",
            "Isabela", "Bruno", "Carolina", "Diego", "Leticia",
            "Vinicius", "Sofia", "Gustavo", "Helena", "Eduardo"
        };

        Sexo[] sexos = {
            Sexo.Masculino, Sexo.Feminino, Sexo.Masculino, Sexo.Feminino,
            Sexo.Masculino, Sexo.Feminino, Sexo.Masculino, Sexo.Feminino,
            Sexo.Masculino, Sexo.Feminino
        };

        Sexo[] interessesSexo = {
            Sexo.Feminino, Sexo.Masculino, Sexo.Indiferente, Sexo.Indiferente,
            Sexo.Feminino, Sexo.Masculino, Sexo.Masculino, Sexo.Feminino,
            Sexo.Indiferente, Sexo.Indiferente
        };

        Interesse[] interesses = Interesse.values();
        int quantidade = 0;

        for (int estado = 0; estado < estados.length; estado++) {
            for (int indice = 0; indice < sexos.length; indice++) {
                int nomeIndex = (estado * 10 + indice) % primeirosNomes.length;
                String nome = primeirosNomes[nomeIndex] + " " + gentilicos[estado];
                Candidato candidato = new Candidato(nome, estados[estado],
                        sexos[indice], interessesSexo[indice]);

                // Cada candidato recebe uma combinação diferente de interesses.
                int inicio = (estado * 3 + indice) % interesses.length;
                candidato.adicionaInteresse(interesses[inicio]);
                candidato.adicionaInteresse(interesses[(inicio + 1) % interesses.length]);
                if (indice % 3 == 0) {
                    candidato.adicionaInteresse(interesses[(inicio + 2) % interesses.length]);
                }
            candidatos.add(candidatos.getSize(), candidato);
            quantidade++;
            }
        }
        return quantidade;
    }
}
