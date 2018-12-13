package com.edgardleal.graph.test;

import com.edgardleal.graph.IGraph;
import com.edgardleal.graph.IPath;
import com.edgardleal.graph.NodeNotFoundException;
import com.edgardleal.graph.PathNotFoundException;
import com.edgardleal.graph.def.DefaultPathFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultPathFinderTest {

    @Test(expected = PathNotFoundException.class)
    public void testPathNotFound() throws PathNotFoundException, NodeNotFoundException {
        IGraph graph = new GraphStubSimpleAB();
        DefaultPathFinder pathFinder = new DefaultPathFinder("C", "A");
        pathFinder.analize(graph);

        pathFinder.getBestPath();
    }


    @Test
    public void simplesTest() throws PathNotFoundException, NodeNotFoundException {
        IGraph graph = new GraphStubSimpleAB();
        DefaultPathFinder pathFinder = new DefaultPathFinder("B", "A");
        pathFinder.analize(graph);

        assertEquals(pathFinder.getBestPath().getTotalDistance(), 50, 0.01);
    }

    /**
     * A - 50 -> B - 10 -> C
     */
    @Test
    public void testABCPath() throws PathNotFoundException, NodeNotFoundException {
        IGraph graph = new GraphStubSimpleABC();
        DefaultPathFinder pathFinder = new DefaultPathFinder("C", "A");
        pathFinder.analize(graph);

        assertEquals(pathFinder.getBestPath().getTotalDistance(), 60, 0.01);
    }

    @Test
    public void testMultiPathTarget1() throws PathNotFoundException, NodeNotFoundException {
        IGraph graph = new GraphStubMultiPathABCD();
        DefaultPathFinder pathFinder = new DefaultPathFinder("C", "A");
        pathFinder.analize(graph);

        IPath path = pathFinder.getBestPath();

        assertEquals(1, pathFinder.getTotalPaths());
        assertEquals(60, path.getTotalDistance(),0.01);
    }

    @Test
    public void testMultiPathTargetFindD() throws PathNotFoundException, NodeNotFoundException {
        IGraph graph = new GraphStubMultiPathABCD();
        DefaultPathFinder pathFinder = new DefaultPathFinder("D", "A");
        pathFinder.analize(graph);

        IPath path = pathFinder.getBestPath();

        assertEquals(1, pathFinder.getTotalPaths());
        assertEquals(70, path.getTotalDistance(),0.01);
    }
}
