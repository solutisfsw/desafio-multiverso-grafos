package com.edgardleal.graph.test;

import com.edgardleal.graph.NodeNotFoundException;
import com.edgardleal.graph.def.DefaultGraphImplementation;
import com.edgardleal.graph.def.DefaultNodeImplementation;

public class GraphStubSimpleABC extends DefaultGraphImplementation {
    public GraphStubSimpleABC() throws NodeNotFoundException {
        this.addNode(new DefaultNodeImplementation("A"));
        this.addNode(new DefaultNodeImplementation("B"));
        this.addNode(new DefaultNodeImplementation("C"));

        this.createVertex("A", "B", 50);
        this.createVertex("B", "C", 10);
    }
}

