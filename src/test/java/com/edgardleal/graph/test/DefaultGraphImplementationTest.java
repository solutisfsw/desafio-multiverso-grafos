package com.edgardleal.graph.test;

import com.edgardleal.graph.IGraph;
import com.edgardleal.graph.NodeNotFoundException;
import com.edgardleal.graph.def.DefaultGraphImplementation;
import com.edgardleal.graph.def.DefaultNodeImplementation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultGraphImplementationTest {

    @Test(expected = NodeNotFoundException.class)
    public void testNewNode() throws NodeNotFoundException {
        IGraph graph = new DefaultGraphImplementation();

        graph.addNode(new DefaultNodeImplementation("A"));

        assertTrue(graph.getNode("A") != null);

        graph.getNode("Z");
    }

    @Test
   public void testCreateVertex() throws NodeNotFoundException {
        IGraph graph = new DefaultGraphImplementation();

        graph.addNode(new DefaultNodeImplementation("A"));
        graph.addNode(new DefaultNodeImplementation("B"));

        graph.createVertex("A", "B", 10);

        assertTrue(graph.getNode("A") != null);

        assertEquals(graph.getNode("A").getVertexList().size(), 1);
    }

}
