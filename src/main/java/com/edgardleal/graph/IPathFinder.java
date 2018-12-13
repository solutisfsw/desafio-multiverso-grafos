package com.edgardleal.graph;

import java.util.List;
import java.util.function.Predicate;

public interface IPathFinder {
    void analize(IGraph graph) throws NodeNotFoundException;

    IPath getBestPath() throws PathNotFoundException;

    IPath getPathThatContainsNode(String id) throws PathNotFoundException;

    int getTotalPaths();

    List<IPath> filter(Predicate<IPath> filterFunction);
}
