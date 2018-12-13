package com.edgardleal.graph;

import java.util.List;

public interface INode {
    String getId();

    void addVertex(IVertex vertex);

    List<IVertex> getVertexList();
}
