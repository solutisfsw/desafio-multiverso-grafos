package com.edgardleal.graph;

public interface IPathFinder {
    void analize(IGraph graph) throws NodeNotFoundException;

    IPath getBestPath() throws PathNotFoundException;
    IPath getPathThatContainsNode(String id) throws PathNotFoundException;

    int getTotalPaths();
}
