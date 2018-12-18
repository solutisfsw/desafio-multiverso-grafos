"""
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
"""
import sys

from docopt import docopt

from src.graphutils import PathNotFoundError
from . import __VERSION__
from . import graphutils

CHALLENGE_GRAPH_STRING = \
    """
    A B 50
    A E 70
    A D 50
    B C 40
    C D 40
    C E 20
    D C 40
    D E 80
    E B 30
    """


def main():
    options = docopt(__doc__, version=__VERSION__)

    if options["show-solution"]:
        graph = graphutils.Graph.from_string(CHALLENGE_GRAPH_STRING)

        print("Questões e respostas do Desafio Multiverso!!", end="\n\n")

        print("- A distância de A a C passando por B?")
        _, res = graph.find_shortest_path('A', 'C', 'B')
        print(f"{res} unidades de espaço-tempo.", end="\n\n")

        print("- A distância entre A e D?")
        _, res = graph.find_shortest_path('A', 'D')
        print(f"{res} unidades de espaço-tempo.", end="\n\n")

        print("- A distância entre A e C passando por D?")
        _, res = graph.find_shortest_path('A', 'C', 'D')
        print(f"{res} unidades de espaço-tempo.", end="\n\n")

        print("- O número de rotas saindo de C e voltando a C com no máximo 3 paradas?")
        res = graph.count_routes('C', 'C', max_stops=3)
        print(f"{res} rotas.", end="\n\n")

        print("- O número de rotas entre A e C com no máximo 4 paradas?")
        res = graph.count_routes('A', 'C', max_stops=4)
        print(f"{res} rotas.", end="\n\n")

        print("- A menor rota (em espaço-tempo) entre A e C?")
        res, cost = graph.find_shortest_path('A', 'C')
        res = [str(vertex) for vertex in res]
        print(f"Menor rota: {' -> '.join(res)}. Distância total: {cost} unidades de espaço-tempo.", end="\n\n")

        print("- A menor rota (em espaço-tempo) saindo de B e voltando a B?")
        res, cost = graph.find_shortest_path('B', 'B')
        res = [str(vertex) for vertex in res]
        print(f"Menor rota: {' -> '.join(res)}. Distância total: {cost} unidades de espaço-tempo.", end="\n\n")

        print("- O número de diferentes rotas saindo de C e voltando a C "
              "com distância máxima de 300 unidades de espaço-tempo?")
        res = graph.count_routes('C', 'C', max_distance=300)
        print(f"{res} rotas.", end="\n\n")

    elif options["count-routes"]:
        graph = graphutils.Graph.from_filepath(options["<graph-file>"])
        src = options["--from"]
        dest = options["--to"]
        max_stops = int(options["--max-stops"]) if options["--max-stops"] else sys.maxsize
        max_distance = int(options["--max-distance"]) if options["--max-distance"] else sys.maxsize

        try:
            res = graph.count_routes(src, dest, max_stops, max_distance)
        except ValueError as err:
            raise SystemExit(err)

        print(f"{res} rotas.")

    elif options["find-path"]:
        graph = graphutils.Graph.from_filepath(options["<graph-file>"])
        src = options["--from"]
        dest = options["--to"]
        mid = options["--through"]

        try:
            path, cost = graph.find_shortest_path(src, dest, mid)
        except (PathNotFoundError, ValueError) as err:
            raise SystemExit(err)

        path = [str(vert) for vert in path]
        print(f"Rota: {' -> '.join(path)}")
        print(f"{cost} unidades de espaço tempo.")

    elif options["distance"]:
        graph = graphutils.Graph.from_filepath(options["<graph-file>"])
        src = options["--from"]
        dest = options["--to"]
        mid = options["--through"]

        try:
            _, cost = graph.find_shortest_path(src, dest, mid)
        except (PathNotFoundError, ValueError) as err:
            raise SystemExit(err)

        print(f"{cost} unidades de espaço-tempo.")

    raise SystemExit()


if __name__ == "__main__":
    main()
