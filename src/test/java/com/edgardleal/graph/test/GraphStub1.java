package com.edgardleal.graph.test;

import com.edgardleal.graph.NodeNotFoundException;
import com.edgardleal.graph.def.DefaultGraphImplementation;
import com.edgardleal.graph.def.DefaultNodeImplementation;

public class GraphStub1 extends DefaultGraphImplementation {
    public GraphStub1() throws NodeNotFoundException {
        this.addNode(new DefaultNodeImplementation("A"));
        this.addNode(new DefaultNodeImplementation("B"));
        this.addNode(new DefaultNodeImplementation("C"));
        this.addNode(new DefaultNodeImplementation("D"));
        this.addNode(new DefaultNodeImplementation("E"));


        this.createVertex("A", "B", 50f);
        this.createVertex("A", "E", 70f);
        this.createVertex("A", "D", 50f);

        this.createVertex("B", "C", 40f);

        this.createVertex("C", "E", 20f);
        this.createVertex("C", "D", 40f);

        this.createVertex("D", "E", 80f);
        this.createVertex("D", "C", 40f);

        this.createVertex("E", "B", 30f);

    }
}
