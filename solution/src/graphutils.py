import heapq
import math
import re
import sys
import uuid
from collections import deque
from typing import Hashable, Deque, Tuple, Dict, Union

PathAndCost = Tuple[Deque, int]
VertexOrString = Union["Vertex", str]


class PathNotFoundError(Exception):
    pass


class Vertex(object):
    """ Graph Vertex """

    def __init__(self, id: Hashable):
        self._id = id

    def __eq__(self, other):
        if not isinstance(other, Vertex):
            return False

        return hash(self) == hash(other)

    def __lt__(self, other):
        return str(self) < str(other)

    def __gt__(self, other):
        return str(self) > str(other)

    def __hash__(self):
        return hash(self._id)

    def __str__(self):
        return str(self._id)

    def __repr__(self):
        return str(self)


class Graph(object):
    """ Directed Graph Abstraction """

    def __init__(self):
        self._adjacency_dict = {}

    def add_edge(self, start: VertexOrString, end: VertexOrString, weight: int) -> None:
        """
        Add a directional edge from start to end with certain weight.

        Args:
            start: Start of the edge.
            end: End of the edge.
            weight: Weight of the edge.
        """
        if isinstance(start, str):
            start = Vertex(start)
        if isinstance(end, str):
            end = Vertex(end)

        if not start in self._adjacency_dict:
            self._adjacency_dict[start] = {}
        if not end in self._adjacency_dict:
            self._adjacency_dict[end] = {}

        self._adjacency_dict[start][end] = weight

    def remove_edge(self, start: Vertex, end: Vertex) -> None:
        """
        Remove edge from start to end.

        Args:
            start: Start of the edge to be removed.
            end: End of the edge to be removed.
        """
        if start not in self._adjacency_dict:
            raise KeyError(f"Node {start} not found in the Graph.")
        if end not in self._adjacency_dict[end]:
            raise KeyError(f"Node {end} doesn't have an edge from {start}.")

        del self._adjacency_dict[start][end]

    def remove_vertex(self, target: Vertex) -> None:
        """
        Remove a vertex from the graph, including all edges from and to it.

        Args:
            target: Vertex to be removed.
        """
        if target not in self._adjacency_dict:
            raise KeyError(f"Node {target} not found in the Graph.")

        del self._adjacency_dict[target]

        for source, connections in self._adjacency_dict.items():
            if target in connections:
                del self._adjacency_dict[source][target]

    def get_neighbours_dict(self, target: Vertex) -> Dict[Vertex, int]:
        return self._adjacency_dict[target]

    def add_vertex_copy(self, target: Vertex) -> Vertex:
        """
        Create a vertex copy and add it to the graph.
        Useful for finding paths from a vertex to itself.

        Args:
            target: Vertex to copy and add to the graph.
        Returns:
            Newly created copy of target.
        """
        copy = Vertex(str(uuid.uuid4())[:8])

        for vert in self._adjacency_dict:
            if vert != target and target in self.get_neighbours_dict(vert):
                self._adjacency_dict[vert][copy] = self._adjacency_dict[vert][target]

        self._adjacency_dict[copy] = self._adjacency_dict[target].copy()

        return copy

    def count_routes(self,
                     src: Vertex,
                     dest: Vertex,
                     max_stops: int = sys.maxsize,
                     max_distance: int = sys.maxsize) -> int:
        """
        Count how many different routes there are from src to dest.
        Limitations on how many stops in the routes and the maximum distance
        for the routes can be passed in max_stops and max_distance parameters.

        Args:
            src: Starting vertex.
            dest: Destination vertex.
            max_stops: Maximum number of stops allowed.
            max_distance: Maximum distance allowed.

        Returns:
            How many routes were found given the constraints.
        """
        if isinstance(src, str):
            src = Vertex(src)
        if isinstance(dest, str):
            dest = Vertex(dest)

        if src == dest:
            dest = self.add_vertex_copy(src)
            temp_dest = True
        else:
            temp_dest = False

        visited = {vertex: False for vertex in self._adjacency_dict}
        total = self._do_count_routes(src,
                                      dest,
                                      0,
                                      max_stops,
                                      0,
                                      max_distance,
                                      visited)

        if temp_dest:
            self.remove_vertex(dest)

        return total

    def _do_count_routes(self,
                         cur: Vertex,
                         dest: Vertex,
                         stops: int,
                         max_stops: int,
                         distance: int,
                         max_distance: int,
                         visited: Dict[Vertex, bool]) -> int:
        """
        Recursive backtracking DFS algorithm to count routes from
        starting vertex to dest vertex.

        Args:
            cur: Current vertex.
            dest: Destination vertex.
            stops: How many stops passed so far.
            max_stops: Maximum number of stops allowed.
            distance: Current distance sum.
            max_distance: Maximum distance allowed.
            visited: Visited vertices so far.

        Returns:
            How many routes ended in dest vertex so far.
        """
        visited[cur] = True

        if cur == dest:
            visited[cur] = False
            return 1

        sum = 0
        for vert in self.get_neighbours_dict(cur):
            new_distance = distance + self._adjacency_dict[cur][vert]
            if not visited[vert] and stops <= max_stops and new_distance <= max_distance:
                sum += self._do_count_routes(vert,
                                             dest,
                                             stops + 1,
                                             max_stops,
                                             new_distance,
                                             max_distance,
                                             visited)

        visited[cur] = False

        return sum

    def _run_dijkstra(self, src: Vertex, dest: Vertex) -> PathAndCost:
        """
        Dijsktra single-source shortest paths algorithm.
        Used here to find the shortest path from src to dest.

        Complexity: O(E + V*logV)
        where E is quantity of edges, and V quantity of vetices
        of the graph.

        Args:
            src: Source vertex.
            dest: Destination vertex.

        Returns:
            Tuple containing the path from src to dest and the cost
            of the path.
        """
        distances = {vertex: math.inf for vertex in self._adjacency_dict}
        previous_vertices = {vertex: None for vertex in self._adjacency_dict}

        distances[src] = 0

        priority_queue = []

        heapq.heappush(priority_queue, (0, src))

        while priority_queue:
            cur_distance, cur_vertex = heapq.heappop(priority_queue)

            if cur_distance > distances[cur_vertex]:
                continue

            # Break loop if we reach the destination
            if cur_vertex == dest:
                break

            for target, weight in self.get_neighbours_dict(cur_vertex).items():

                # Relaxation step
                if distances[cur_vertex] + weight < distances[target]:
                    distances[target] = distances[cur_vertex] + weight
                    previous_vertices[target] = cur_vertex
                    heapq.heappush(priority_queue, (distances[target], target))

        cost_sum = 0
        path, cur_vertex = deque(), dest

        # Build shortest path
        while previous_vertices[cur_vertex] is not None:
            path.appendleft(cur_vertex)
            cost_sum += self._adjacency_dict[previous_vertices[cur_vertex]][cur_vertex]
            cur_vertex = previous_vertices[cur_vertex]

        if path:
            path.appendleft(cur_vertex)

        return path, cost_sum

    def find_shortest_path(self,
                           src: VertexOrString,
                           dest: VertexOrString,
                           mid: VertexOrString = None) -> PathAndCost:
        """
        Find the shortest path in the graph from src to dest,
        passing through mid if it not None.

        Args:
            src: Source vertex.
            dest: Destination vertex.
            mid: Vertex that should be in the path from src to dest.

        Returns:
            Tuple containing the path from src to dest and the cost
            of the path.
        """
        if isinstance(src, str):
            src = Vertex(src)
        if isinstance(dest, str):
            dest = Vertex(dest)
        if isinstance(mid, str):
            mid = Vertex(mid)

        if src not in self._adjacency_dict:
            raise ValueError(f"Can't find source vertex {src} in the graph.")
        if dest not in self._adjacency_dict:
            raise ValueError(f"Can't find destination vertex {dest} in the graph.")
        if mid and mid not in self._adjacency_dict:
            raise ValueError(f"Can't find intermediate vertex {mid} in the graph.")

        if src == dest:
            dest = self.add_vertex_copy(src)
            temp_dest = True
        else:
            temp_dest = False

        # If there's an intermediate vertex,
        # run shortest path algorithm from src to intermediate
        # than from intermediate to dest.
        if mid is not None:
            path_from_src_to_mid, cost1 = self._run_dijkstra(src, mid)
            path_from_mid_to_dest, cost2 = self._run_dijkstra(mid, dest)

            full_path = deque(
                list(path_from_src_to_mid) + list(path_from_mid_to_dest)[1:])
            full_cost = cost1 + cost2
        else:
            full_path, full_cost = self._run_dijkstra(src, dest)

        if temp_dest:
            if full_path:
                # Remove temporary vertex from path list
                # and put source vertex instead
                full_path.pop()
                full_path.append(src)
            # Remove temporary vertex from graph
            self.remove_vertex(dest)

        if not full_path:
            raise PathNotFoundError(
                f"Couldn't find path from {src} to {'itself' if temp_dest else dest}."
            )

        return full_path, full_cost

    @classmethod
    def from_filepath(cls, filepath: str):
        """
        Build Graph object from a file that contains a graph described as string.

        Args:
            filepath: Path of the file that contains the graph.

        Returns:
            Graph instance.
        """

        with open(filepath, "r") as f:
            return cls.from_string(f.read())

    @classmethod
    def from_string(cls, graph_data: str):
        """
        Build Graph object from a graph described as string.

        The string must have a line per edge, like this:
        A B 10
        C D 20

        This graph says that there's an edge from A to B with weight 10,
        and from C to D with weight 20.

        Args:
            graph_data: The string representing a graph.

        Returns:
            Graph instance.
        """

        graph = cls()

        for line in graph_data.splitlines():

            if not line:
                continue

            splitted_line = re.split("\\s+", line)

            if len(splitted_line) != 3:
                raise ValueError(f"Invalid line found in line: {line}")

            src = Vertex(splitted_line[0])
            dest = Vertex(splitted_line[1])
            weight = int(splitted_line[2])

            graph.add_edge(src, dest, weight)

        return graph
