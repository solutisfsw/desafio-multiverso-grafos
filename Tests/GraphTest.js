//Teste unitários Link
n1 = new Node("A");
n2 = new Node("B");
n3 = new Node("C");
n4 = new Node("D");

n1.addLink( new Link(n2, 10) );
n1.addLink(new Link(n4, 10) );
n2.addLink(new Link(n1, 20) );
n3.addLink(new Link(n1, 20) );
n4.addLink(new Link(n2, 20) );
n4.addLink(new Link(n3, 10) );

graph = new Graph([n1, n2, n3, n4]);
assert(graph instanceof Graph, "erro ao instanciar Grafo");
assert(graph.getNodes().length == 4, "erro ao atribuir nós ao Grafo");


rs = graph.getAllRoutes(n4, n2);
assert(rs.length == 2, "erro ao calcular as rotas posíveis");

rs = graph.getAllRoutes(n1, n4);

assert(rs.length == 2, "erro ao calcular as rotas posíveis");
assert(rs[1][0] == -1, "erro ao calcular o peso rotas impossíveis");
assert(rs[1][1] == 10, "erro ao calcular o peso rotas possíveis");


rs = graph.getAllRoutes(n1, n1);

g = graph.getRouteToYPassingX(rs, n1, n3);
assert(g[0].length == 1, "erro ao calcular rota passando por x");

g = graph.getCountRouteToYMaxStop(rs, n1, 1);
assert(g == 1, "erro ao calcular rota com maximo de paradas");
g = graph.getCountRouteToYMaxDistance(rs, 30, n1);
assert(g == 1, "erro ao calcular rota com maximo de distância");

g = graph.getLessRoute(rs, n1);
assert(g[0] == 30, "erro ao calcular menor rota");

console.log("Teste Graph - 100%");
