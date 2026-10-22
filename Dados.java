import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import Enum.Estado;
import Enum.Interesse;
import Enum.Sexo;
import Model.Candidato;
import Model.ListaIdentificadores;

/** Gera e carrega cadastros no formato de candidatos.txt do enunciado. */
public class Dados {
    private static final String ARQUIVO = "candidatos.txt";
    private static final String[] NOMES_MASCULINOS = {
        "Lucas", "Pedro", "Joao", "Gabriel", "Rafael", "Mateus", "Felipe",
        "Thiago", "Bruno", "Diego", "Vinicius", "Gustavo", "Eduardo"
    };
    private static final String[] NOMES_FEMININOS = {
        "Ana", "Mariana", "Beatriz", "Juliana", "Camila", "Larissa", "Amanda",
        "Isabela", "Carolina", "Leticia", "Sofia", "Helena"
    };
    private static final String[] SOBRENOMES = {
        "Silva", "Santos", "Oliveira", "Souza", "Pereira", "Costa", "Rodrigues",
        "Almeida", "Nascimento", "Lima", "Araujo", "Fernandes", "Carvalho",
        "Gomes", "Martins", "Rocha", "Ribeiro", "Alves", "Monteiro", "Barbosa"
    };

    /** Execute java Dados para gerar um novo arquivo de exemplo. */
    public static void main(String[] args) throws IOException {
        gerar(Paths.get(ARQUIVO));
        System.out.println("Arquivo " + ARQUIVO + " gerado com 270 candidatos.");
    }

    private static void gerar(Path arquivo) throws IOException {
        Random random = new Random();
        Estado[] estados = Estado.values();
        try (BufferedWriter saida = Files.newBufferedWriter(arquivo, StandardCharsets.UTF_8)) {
            saida.write(Integer.toString(estados.length * 10));
            saida.newLine();
            for (Estado estado : estados) {
                for (int i = 0; i < 10; i++) {
                    boolean masculino = random.nextBoolean();
                    String[] nomes = masculino ? NOMES_MASCULINOS : NOMES_FEMININOS;
                    escreverLinha(saida, "****");
                    escreverLinha(saida, nomes[random.nextInt(nomes.length)] + " "
                            + SOBRENOMES[random.nextInt(SOBRENOMES.length)]);
                    escreverLinha(saida, estado.getSigla());
                    escreverLinha(saida, masculino ? "M" : "F");
                    String[] preferencias = {"M", "F", "I"};
                    escreverLinha(saida, preferencias[random.nextInt(preferencias.length)]);
                    int quantidadeAreas = 2 + random.nextInt(2);
                    escreverLinha(saida, Integer.toString(quantidadeAreas));
                    boolean[] escolhidas = new boolean[Interesse.values().length];
                    for (int j = 0; j < quantidadeAreas; j++) {
                        int area;
                        do { area = random.nextInt(escolhidas.length); } while (escolhidas[area]);
                        escolhidas[area] = true;
                        escreverLinha(saida, Integer.toString(area + 1));
                    }
                }
            }
        }
    }

    private static void escreverLinha(BufferedWriter saida, String valor) throws IOException {
        saida.write(valor);
        saida.newLine();
    }

    public static int carregar(ListaIdentificadores<Candidato> candidatos) {
        Path arquivo = Paths.get(ARQUIVO);
        try {
            if (Files.notExists(arquivo)) {
                gerar(arquivo);
                System.out.println("Arquivo " + ARQUIVO + " gerado com 270 candidatos.");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível gerar " + ARQUIVO + ": " + e.getMessage(), e);
        }
        try (BufferedReader entrada = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            int quantidade = lerNumero(entrada, "quantidade de candidatos");
            if (quantidade < 0) throw new IOException("Quantidade de candidatos negativa.");
            for (int i = 0; i < quantidade; i++) {
                if (!"****".equals(lerLinha(entrada, "separador"))) {
                    throw new IOException("Separador inválido no candidato " + (i + 1));
                }
                String nome = lerLinha(entrada, "nome");
                String sigla = lerLinha(entrada, "estado");
                String sexoTexto = lerLinha(entrada, "sexo");
                String preferenciaTexto = lerLinha(entrada, "preferência");
                Estado estado = Estado.getEstado(sigla);
                if (nome.isEmpty() || estado == null || !(sexoTexto.equals("M") || sexoTexto.equals("F"))
                        || !(preferenciaTexto.equals("M") || preferenciaTexto.equals("F") || preferenciaTexto.equals("I"))) {
                    throw new IOException("Cadastro inválido no candidato " + (i + 1));
                }
                Candidato candidato = new Candidato(nome, estado, Sexo.getSexo(sexoTexto), Sexo.getSexo(preferenciaTexto));
                int quantidadeAreas = lerNumero(entrada, "quantidade de áreas");
                Interesse[] areas = Interesse.values();
                if (quantidadeAreas < 0 || quantidadeAreas > areas.length) throw new IOException("Quantidade de áreas inválida.");
                boolean[] escolhidas = new boolean[areas.length];
                for (int j = 0; j < quantidadeAreas; j++) {
                    int codigo = lerNumero(entrada, "área de interesse");
                    if (codigo < 1 || codigo > areas.length || escolhidas[codigo - 1]) throw new IOException("Área inválida ou repetida.");
                    escolhidas[codigo - 1] = true;
                    candidato.adicionaInteresse(areas[codigo - 1]);
                }
                candidatos.add(candidatos.getSize(), candidato);
            }
            if (entrada.readLine() != null) throw new IOException("Dados adicionais após o último candidato.");
            return quantidade;
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível carregar " + ARQUIVO + ": " + e.getMessage(), e);
        }
    }

    private static String lerLinha(BufferedReader entrada, String campo) throws IOException {
        String linha = entrada.readLine();
        if (linha == null) throw new IOException("Fim de arquivo ao ler " + campo + ".");
        return linha.trim();
    }

    private static int lerNumero(BufferedReader entrada, String campo) throws IOException {
        try {
            return Integer.parseInt(lerLinha(entrada, campo));
        } catch (NumberFormatException e) {
            throw new IOException("Número inválido em " + campo + ".", e);
        }
    }
}
