package com.edgardleal.graph.def;

import com.edgardleal.graph.INode;
import com.edgardleal.graph.IVertex;

import java.util.LinkedList;
import java.util.List;

public class DefaultNodeImplementation implements INode {
    private String id;
    private List<IVertex> vertexList;

    public DefaultNodeImplementation(String id) {
        this.id = id;
        this.vertexList = new LinkedList<>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void addVertex(IVertex vertex) {
        this.vertexList.add(vertex);
    }

    @Override
    public List<IVertex> getVertexList() {
        return this.vertexList;
    }
}
