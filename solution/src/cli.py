import argparse

import graphutils

CHALLENGE_GRAPH_STRING = """
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
    parser = argparse.ArgumentParser(
        description="CLI program to execute the solution to the Desafio Multiverso.")
    parser.add_argument("--show-solution", action="store_true",
        help="Print the solution to the requested challenge.")
    args = parser.parse_args()

    if args.show_solution:
        graph = graphutils.Graph.from_string(CHALLENGE_GRAPH_STRING)

        print("Questões e respostas do Desafio Multiverso!!", end="\n\n")

        print("- A distância de A a C passando por B?")
        _, res = graph.find_shortest_path('A', 'C', 'B')
        print(f"{res} unidades de espaço-tempo.")

        print("- A distância entre A e D?")
        _, res = graph.find_shortest_path('A', 'D')
        print(f"{res} unidades de espaço-tempo.")

        print("- A distância entre A e C passando por D?")
        _, res = graph.find_shortest_path('A', 'C', 'D')
        print(f"{res} unidades de espaço-tempo.")

        print("- O número de rotas saindo de C e voltando a C com no máximo 3 paradas?")
        res = graph.count_routes('C', 'C', max_stops=3)
        print(f"{res} rotas.")

        print("- O número de rotas entre A e C com no máximo 4 paradas?")
        res = graph.count_routes('A', 'C', max_stops=4)
        print(f"{res} rotas.")

        print("- A menor rota (em espaço-tempo) entre A e C?")
        res, cost = graph.find_shortest_path('A', 'C')
        res = [str(vertex) for vertex in res]
        print(f"Menor rota: {' -> '.join(res)}. Distância total: {cost} unidades de espaço-tempo.")

        print("- A menor rota (em espaço-tempo) saindo de B e voltando a B?")
        res, cost = graph.find_shortest_path('B', 'B')
        res = [str(vertex) for vertex in res]
        print(f"Menor rota: {' -> '.join(list(res))}. Distância total: {cost} unidades de espaço-tempo.")

        print("- O número de diferentes rotas saindo de C e voltando a C "
              "com distância máxima de 300 unidades de espaço-tempo?")
        res = graph.count_routes('C', 'C', max_distance=300)
        print(f"{res} rotas.")


if __name__ == "__main__":
    main()
