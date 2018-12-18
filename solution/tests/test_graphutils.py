import os
import unittest
from collections import deque

from src.graphutils import Graph, Vertex, PathNotFoundError

CHALLENGE_GRAPH_FILEPATH = os.path.join("tests", "assets", "challenge_graph.txt")
CYCLIC_GRAPH_FILEPATH = os.path.join("tests", "assets", "cyclic_graph.txt")
SIMPLE_GRAPH_FILEPATH = os.path.join("tests", "assets", "simple_graph.txt")


class TestGraphBuildMethods(unittest.TestCase):

    def test_build_graph_from_filepath(self):
        simple_graph = Graph.from_filepath(SIMPLE_GRAPH_FILEPATH)

        # Simple graph has 4 vertices
        self.assertEqual(len(simple_graph._adjacency_dict), 4)

        A, B, C, D = Vertex('A'), Vertex('B'), Vertex('C'), Vertex('D')

        A_neighbours = simple_graph.get_neighbours_dict(A)
        B_neighbours = simple_graph.get_neighbours_dict(B)
        C_neighbours = simple_graph.get_neighbours_dict(C)
        D_neighbours = simple_graph.get_neighbours_dict(D)

        self.assertEqual(A_neighbours[B], 10)
        self.assertEqual(A_neighbours[D], 50)
        self.assertEqual(B_neighbours[C], 20)
        self.assertEqual(B_neighbours[D], 30)

        self.assertEqual(len(C_neighbours), 0)
        self.assertEqual(len(D_neighbours), 0)

        # Assert A doesn't have an edge with C
        with self.assertRaises(KeyError):
            weight = A_neighbours[C]


class TestGraphCountingMethods(unittest.TestCase):

    def test_count_routes_in_simple_graph(self):
        simple_graph = Graph.from_filepath(SIMPLE_GRAPH_FILEPATH)

        A, B, C, D = Vertex('A'), Vertex('B'), Vertex('C'), Vertex('D')

        self.assertEqual(1, simple_graph.count_routes(A, C))
        self.assertEqual(2, simple_graph.count_routes(A, D))
        self.assertEqual(1, simple_graph.count_routes(B, D))

    def test_count_routes_in_cyclic_graph(self):
        cyclic_graph = Graph.from_filepath(CYCLIC_GRAPH_FILEPATH)

        A, B, C, D = Vertex('A'), Vertex('B'), Vertex('C'), Vertex('D')

        self.assertEqual(1, cyclic_graph.count_routes(A, A))
        self.assertEqual(1, cyclic_graph.count_routes(A, C))
        self.assertEqual(1, cyclic_graph.count_routes(A, D))

    def test_count_routes_in_graph_from_challenge(self):
        challenge_graph = Graph.from_filepath(CHALLENGE_GRAPH_FILEPATH)

        A, B, C, D, E = Vertex('A'), Vertex('B'), Vertex('C'), Vertex('D'), Vertex('E')

        # Q 4
        self.assertEqual(3, challenge_graph.count_routes(C, C, max_stops=3))
        self.assertEqual(2, challenge_graph.count_routes(C, C, max_stops=2))
        self.assertEqual(1, challenge_graph.count_routes(C, C, max_stops=1))
        self.assertEqual(0, challenge_graph.count_routes(C, C, max_stops=0))

        # Q 5
        self.assertEqual(4, challenge_graph.count_routes(A, C, max_stops=4))
        self.assertEqual(2, challenge_graph.count_routes(A, C, max_stops=1))

        # Q 8
        self.assertEqual(3, challenge_graph.count_routes(C, C, max_distance=300))
        self.assertEqual(2, challenge_graph.count_routes(C, C, max_distance=100))
        self.assertEqual(1, challenge_graph.count_routes(C, C, max_distance=80))


class TestGraphPathMethods(unittest.TestCase):

    def test_find_shortest_path_in_simple_graph(self):
        simple_graph = Graph.from_filepath(SIMPLE_GRAPH_FILEPATH)

        A, B, C, D = Vertex('A'), Vertex('B'), Vertex('C'), Vertex('D')

        res = (deque([A, B]), 10)
        self.assertEqual(res, simple_graph.find_shortest_path(A, B))

        res = (deque([A, B, C]), 30)
        self.assertEqual(res, simple_graph.find_shortest_path(A, C))

        res = (deque([A, B, D]), 40)
        self.assertEqual(res, simple_graph.find_shortest_path(A, D))

        with self.assertRaises(PathNotFoundError):
            simple_graph.find_shortest_path(D, A)

    def test_find_shortest_path_in_cyclic_graph(self):
        cyclic_graph = Graph.from_filepath(CYCLIC_GRAPH_FILEPATH)

        A, B, C, D = Vertex('A'), Vertex('B'), Vertex('C'), Vertex('D')

        res = (deque([A, B, C, A]), 30)
        self.assertEqual(res, cyclic_graph.find_shortest_path(A, A))

        res = (deque([B, C, A]), 20)
        self.assertEqual(res, cyclic_graph.find_shortest_path(B, A))

        res = (deque([B, C, D]), 20)
        self.assertEqual(res, cyclic_graph.find_shortest_path(B, D, mid=C))

        with self.assertRaises(PathNotFoundError):
            cyclic_graph.find_shortest_path(D, A)

    def test_find_shortest_path_in_graph_from_challenge(self):
        challenge_graph = Graph.from_filepath(CHALLENGE_GRAPH_FILEPATH)

        A, B, C, D, E = Vertex('A'), Vertex('B'), Vertex('C'), Vertex('D'), Vertex('E')

        # Q 6
        res = (deque([A, B, C]), 90)
        self.assertEqual(res, challenge_graph.find_shortest_path(A, C))

        # Q 7
        res = (deque([B, C, E, B]), 90)
        self.assertEqual(res, challenge_graph.find_shortest_path(B, B))

        # Q 2
        res = (deque([A, D]), 50)
        self.assertEqual(res, challenge_graph.find_shortest_path(A, D))

        # Q 1
        res = (deque([A, B, C]), 90)
        self.assertEqual(res, challenge_graph.find_shortest_path(A, C, mid=B))

        # Q 3
        res = (deque([A, D, C]), 90)
        self.assertEqual(res, challenge_graph.find_shortest_path(A, C, mid=D))
