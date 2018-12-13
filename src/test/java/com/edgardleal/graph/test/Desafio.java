package com.edgardleal.graph.test;

import com.edgardleal.graph.*;
import com.edgardleal.graph.def.DefaultPathFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class Desafio {

    private IGraph graph;

    @Before
    public void setup() throws NodeNotFoundException {
        graph = new GraphStub1();
    }

    @Test
    public void testDistanciaEntreAeCPassandoPorB() throws NodeNotFoundException, PathNotFoundException {
        DefaultPathFinder pathFinder = new DefaultPathFinder("C", "A");
        pathFinder.analize(graph);

        IPath path = pathFinder.getPathThatContainsNode("B");

        assertEquals(4, pathFinder.getTotalPaths());
        assertEquals(90, path.getTotalDistance(), 0.01);
    }

    @Test
    public void testDistanciaEntreAeD() throws NodeNotFoundException, PathNotFoundException {
        DefaultPathFinder pathFinder = new DefaultPathFinder("D", "A");
        pathFinder.analize(graph);

        IPath path = pathFinder.getBestPath();

        assertEquals(3, pathFinder.getTotalPaths());
        assertEquals(50, path.getTotalDistance(), 0.01);
    }

    @Test
    public void testDistanciaEntreAeCPassandoPorD() throws NodeNotFoundException, PathNotFoundException {
        DefaultPathFinder pathFinder = new DefaultPathFinder("C", "A");
        pathFinder.analize(graph);

        final IPath path = pathFinder.getPathThatContainsNode("D");
        pathFinder.printAll();

        assertEquals(4, pathFinder.getTotalPaths());
        assertEquals(90, path.getTotalDistance(), 0.01);
    }

    /**
     * O número de rotas saindo de C e voltando a C com no máximo 3 paradas?
     */
    @Test
    public void testNumeroDeRotasCParaCMaximo3Paradas() throws NodeNotFoundException {
        DefaultPathFinder pathFinder = new DefaultPathFinder("C", "C");
        pathFinder.setBackLimit(2);
        pathFinder.analize(graph);

        pathFinder.printAll();
        List<IPath> list = pathFinder.filter((IPath path) -> path.size() <= 3);

        assertEquals(2, list.size());
    }
}
