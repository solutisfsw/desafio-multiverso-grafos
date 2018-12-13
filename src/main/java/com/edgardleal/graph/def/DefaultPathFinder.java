package com.edgardleal.graph.def;

import com.edgardleal.graph.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefaultPathFinder implements IPathFinder {
    private String target;
    private String origin;
    private List<IPath> pathList;
    private boolean isSorted = false;
    private int backLimit = 1;

    public DefaultPathFinder(String target, String origin) {
        this.target = target;
        this.origin = origin;
        this.pathList = new LinkedList<>();
    }

    private void sort() {
        if (this.isSorted) {
            return;
        }
        Collections.sort(this.pathList, DefaultPathFinder::pathComparator);
        this.isSorted = true;
    }

    /**
     * This method will inform how many times should pass through a Node. <br/>
     * The default value is 1 ( One ).
     *
     * @param value a int with desired value
     */
    public void setBackLimit(int value) {
        this.backLimit = value;
    }

    private static int pathComparator(IPath o1, IPath o2) {
        return o1.getTotalDistance() > o2.getTotalDistance() ? 1 : o1.getTotalDistance() < o2.getTotalDistance() ? -1 : 0;
    }

    @Override
    public IPath getBestPath() throws PathNotFoundException {
        if (this.pathList.size() == 0) {
            throw new PathNotFoundException();
        }
        this.sort();
        return this.pathList.get(0);
    }

    @Override
    public IPath getPathThatContainsNode(String id) throws PathNotFoundException {
        this.sort();
        IPath result = null;
        for (IPath path : this.pathList) {
            if (path.hasVisited(id) > 0) {
                result = path;
                break;
            }
        }
        if (result == null) {
            throw new PathNotFoundException(String.format("Cant find a path that contains the node [%s]", id));

        }
        return result;
    }

    @Override
    public int getTotalPaths() {
        return this.pathList.size();
    }

    /**
     * This method could be used after an {@link #analize(IGraph)} to filter specifics paths.
     *
     * @param filterFunction an {@link Predicate<IPath>} function to filter results
     * @return a {@link List<IPath>} with the results
     */
    @Override
    public List<IPath> filter(Predicate<IPath> filterFunction) {
        return this.pathList.stream().filter(filterFunction).collect(Collectors.toList());
    }

    @Override
    public void analize(IGraph graph) throws NodeNotFoundException {
        this.isSorted = false;
        final INode nodeOrigin = graph.getNode(this.origin);
        for (IVertex iVertex : nodeOrigin.getVertexList()) {
            this.find(iVertex.getTarget(), new DefaultPathImplementation(iVertex), this.target);
        }
    }

    /**
     * Walk through the Graph registering each path found.
     *
     * @param start   a {@link INode} where to  start the search
     * @param current a {@link IPath} the current Path
     * @param target  a  {@link String} with the id for the target Node
     */
    private void find(INode start, IPath current, String target) {

        if (start.getId().equals(target)) {
            this.pathList.add(current);
            return;
        }
        if (start.getVertexList().size() == 0) {
            return;
        }
        final IPath originalPathCopy = current.clone();
        for (int i = 0; i < start.getVertexList().size(); i++) {
            final IVertex vertex = start.getVertexList().get(i);
            if (current.hasVisited(vertex.getTarget()) > this.backLimit) {
                continue;
            }

            if (i == 0) {
                current.addVertex(vertex);

                if (vertex.getTarget().getId().equals(target)) {
                    this.pathList.add(current);
                } else {
                    find(vertex.getTarget(), current, target);
                }

            } else {
                final IPath clone = originalPathCopy.clone();
                clone.addVertex(vertex);
                if (vertex.getTarget().getId().equals(target)) {
                    this.pathList.add(clone);
                } else {
                    find(vertex.getTarget(), clone, target);
                }
            }
        }

    }

    public void printAll() {
        this.pathList.stream().forEach(System.out::println);
    }

}
