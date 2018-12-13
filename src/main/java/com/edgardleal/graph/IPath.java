package com.edgardleal.graph;

import java.util.List;

public interface IPath extends List<IVertex> {
    void addVertex(IVertex vertex);

    float getTotalDistance();

    int hasVisited(INode node);

    int hasVisited(String id);

    IPath clone();
}
