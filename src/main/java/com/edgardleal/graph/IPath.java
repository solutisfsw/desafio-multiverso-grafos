package com.edgardleal.graph;

public interface IPath {
    void addVertex(IVertex vertex);

    float getTotalDistance();

    int hasVisited(INode node);
    int hasVisited(String id);

    IPath clone();
}
