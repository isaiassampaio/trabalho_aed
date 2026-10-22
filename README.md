# Sistema de relacionamentos

Trabalho de Algoritmos e Estruturas de Dados que implementa, em Java, um sistema de cadastro de candidatos e busca de potenciais companheiros. A aplicação funciona pelo terminal e utiliza uma lista implementada no próprio projeto para armazenar os dados.

## Requisitos e execução

É necessário ter o JDK instalado, com os comandos `javac` e `java` disponíveis no terminal. Na pasta raiz do projeto, execute:

```bash
mkdir -p build
javac -encoding UTF-8 -d build Main.java App.java Dados.java Enum/*.java Model/*.java View/*.java
java -cp build Main
```

Execute o programa a partir da pasta raiz: é nela que o arquivo `candidatos.txt` é procurado. Se ele não existir, será gerado automaticamente com 270 candidatos, sendo 10 para cada estado e o Distrito Federal.

## Funcionalidades

O menu permite:

1. Cadastrar candidato, informando nome, estado, sexo, preferência e interesses.
2. Listar candidatos.
3. Listar os `k` candidatos com mais potenciais companheiros.
4. Listar candidatos sem potenciais companheiros.
5. Buscar candidatos por área de interesse.
6. Buscar candidatos por estado.
7. Atualizar um cadastro pelo ID.
8. Alterar o grau mínimo de interesse necessário para formar uma combinação.
9. Consultar os potenciais companheiros de um candidato pelo ID.
0. Encerrar o programa.

Os cadastros e as alterações feitos pelo menu ficam apenas em memória durante a execução; eles não são gravados em `candidatos.txt`. Ao reiniciar, os dados são carregados novamente do arquivo.

## Regras para potenciais companheiros

Dois candidatos são considerados potenciais companheiros quando residem no mesmo estado, suas preferências de sexo são compatíveis em ambos os sentidos e a soma dos pontos das áreas de interesse em comum atinge o grau mínimo. A preferência `I` indica que o sexo do outro candidato é indiferente. O grau mínimo inicial é 2 e pode ser alterado no menu para um valor de 0 a 15.

| Código | Área de interesse | Pontos |
| --- | --- | --- |
| 1 | Esportes | 1 |
| 2 | Artes | 2 |
| 3 | Música | 1 |
| 4 | Cinema | 1 |
| 5 | Tecnologia | 3 |
| 6 | Animais | 2 |
| 7 | Gastronomia | 2 |
| 8 | Ciências | 3 |

Por exemplo, dois candidatos que compartilham Música e Tecnologia somam 4 pontos de interesse em comum.

## Arquivo de candidatos

O arquivo `candidatos.txt` começa com a quantidade de registros. Cada registro possui, em linhas separadas: o separador `****`, nome, sigla do estado, sexo (`M` ou `F`), preferência (`M`, `F` ou `I`), quantidade de áreas de interesse e os códigos dessas áreas. Exemplo:

```text
1
****
Ana Silva
SP
F
I
2
3
5
```

Para gerar um novo arquivo de exemplo, execute o comando abaixo na pasta raiz **após a compilação**. Ele substitui o `candidatos.txt` existente:

```bash
java -cp build Dados
```

A geração combina nomes e sobrenomes e escolhe o primeiro nome de acordo com o sexo sorteado.

## Organização do projeto

- `Main.java`: ponto de entrada da aplicação.
- `App.java`: coordena o menu, os cadastros e as consultas.
- `Dados.java`: gera e carrega o arquivo de candidatos.
- `Model/`: contém o modelo de candidato e a implementação da lista.
- `View/`: contém a interação com o usuário pelo terminal.
- `Enum/`: define estados, sexos e áreas de interesse.
- `candidatos.txt`: arquivo de entrada dos candidatos.
