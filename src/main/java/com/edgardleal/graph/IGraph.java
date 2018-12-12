package com.edgardleal.graph;

public interface IGraph {
    void addNode(INode node);

    IVertex createVertex(String origin, String target, float distance) throws NodeNotFoundException;

    INode getNode(String key) throws NodeNotFoundException;
}
