# Solução para o Desafio Multiverso

Aplicação em linha de comando feita em Python 3.7 como solução para o Desafio Multiverso com algumas funcionalidades
para realizar operações com grafos.

A seguir o grafo apresentado no desafio e as perguntas a serem respondidas:

[![N|Solid](https://github.com/solutisfsw/desafio-multiverso-grafos/raw/master/grafo.png)](Grafo)

* A distância de A a C passando por B?
* A distância entre A e D?
* A distância de A a C passando por D?
* O número de rotas saindo de C e voltando a C com no máximo 3 paradas?
* O número de rotas entre A e C com no máximo 4 paradas?
* A menor rota (em espaço-tempo) entre A e C?
* A menor rota (em espaço-tempo) saindo de B e voltando a B?
* O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?

## Instalação e Uso

É necessário o Python versão 3.7.1 ou superior para instalar e executar a aplicação.
Rode `pip install .` para só instalar ou `pip install -e .` para instalar em modo de desenvolvimento:

```
$ cd solution/
$ pip install -e .
```

A única dependência externa da aplicação é o `docopt`, biblioteca em Python usada para construir
aplicação em linha de comando.

Teste a instalação com o seguinte:

```
$ solveit --help
solveit - CLI program to execute the solution to the Desafio Multiverso.

Usage:
  solveit show-solution
  solveit count-routes --from=VERTEX --to=VERTEX [--max-stops=NUM --max-distance=NUM] <graph-file>
  solveit find-path --from=VERTEX --to=VERTEX [--through=VERTEX] <graph-file>
  solveit distance --from=VERTEX --to=VERTEX [--through=VERTEX] <graph-file>
  solveit -h | --help
  solveit --version

Options:
  --from=VERTEX            Source vertex for the query.
  --to=VERTEX              Destination vertex for the query.
  --through=VERTEX         Intermediate vertex to go through in the requested path.
  --max-stops=NUM          Maximum number of stops to make in a route.
  --max-distance=NUM       Maximum distance the route must have.
  -h --help                Show help screen.
  --version                Show version.

Commands:
  show-solution            Print the solution to the Desafio Multiverso.
  count-routes             Count how many routes there are between two vertices.
  find-path                Find the shortest path between two vertices.
  distance                 Calculate the shortest distance between two vertices.

Examples:

    Print the solution to Desafio Multiverso
    $ solveit show-solution

    Count routes between A and C in a graph
    $ solveit count-routes --from=A --to=C ./my-graph-file.txt

    Print shortest path between B and E going through C in a graph
    $ solveit find-path --from=B --to=E --through=C ./my-graph-file.txt
```

Rode o seguinte para visualizar as respostas do desafio:

```
$ solveit show-solution
```

Utilize os outros comandos para testar outros grafos.

### Formato de entrada dos Grafos

Escreva em um arquivo de texto as arestas do grafo, por exemplo:

```
A B 50
A E 70
A D 50
B C 40
C D 40
C E 20
D C 40
D E 80
E B 30
```

Cada linha é uma aresta, por exemplo `A B 50` cria uma aresta de A a B com custo 50.

### Testes

Execute os testes de unidade com o seguinte comando:

```
$ python setup.py test
```


