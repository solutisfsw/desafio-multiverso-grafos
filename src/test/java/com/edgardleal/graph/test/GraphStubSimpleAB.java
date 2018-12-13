package com.edgardleal.graph.test;

import com.edgardleal.graph.NodeNotFoundException;
import com.edgardleal.graph.def.DefaultGraphImplementation;
import com.edgardleal.graph.def.DefaultNodeImplementation;

public class GraphStubSimpleAB extends DefaultGraphImplementation {
    public GraphStubSimpleAB() throws NodeNotFoundException {
        this.addNode(new DefaultNodeImplementation("A"));
        this.addNode(new DefaultNodeImplementation("B"));

        this.createVertex("A", "B", 50);
    }
}

