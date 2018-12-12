package com.edgardleal.graph.def;

import com.edgardleal.graph.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DefaultPathFinder implements IPathFinder {
    private String target;
    private String origin;
    private List<IPath> pathList;
    private boolean isSorted = false;

    public DefaultPathFinder(String target, String origin) {
        this.target = target;
        this.origin = origin;
        this.pathList = new LinkedList<>();
    }

    @Override
    public void analize(IGraph graph) throws NodeNotFoundException {
        this.isSorted = false;
        final INode nodeOrigin = graph.getNode(this.origin);
        for (IVertex iVertex : nodeOrigin.getVertexList()) {
            this.find(iVertex.getTarget(), new DefaultPathImplementation(iVertex), this.target);
        }
    }

    private void sort() {
        if (this.isSorted) {
            return;
        }
        Collections.sort(this.pathList, DefaultPathFinder::pathComparator);
        this.isSorted = true;
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
        for(IPath path: this.pathList) {
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
            if (current.hasVisited(vertex.getTarget()) > 1) {
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
