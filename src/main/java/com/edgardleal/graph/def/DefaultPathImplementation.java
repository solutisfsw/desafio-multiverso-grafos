package com.edgardleal.graph.def;

import com.edgardleal.graph.INode;
import com.edgardleal.graph.IPath;
import com.edgardleal.graph.IVertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DefaultPathImplementation implements IPath {
    private List<IVertex> vertexList;
    private float totalDistance = 0f;
    private Map<String, Integer> visiteds;

    public DefaultPathImplementation() {
        this.init();
    }

    private void init() {
        this.vertexList = new LinkedList<>();
        this.visiteds = new HashMap<>();
    }

    public DefaultPathImplementation(IVertex iVertex) {
        this.init();
        this.addVertex(iVertex);
    }

    @Override
    public void addVertex(IVertex vertex) {
        this.increaseVisitedNumber(vertex.getOrigin().getId());
        this.increaseVisitedNumber(vertex.getTarget().getId());
        this.totalDistance += vertex.getDistance();
        this.vertexList.add(vertex);
    }

    private void increaseVisitedNumber(String key) {
        Integer current = this.visiteds.get(key);
        if (current == null) {
            this.visiteds.put(key, 1);
        } else {
            this.visiteds.put(key, current + 1);
        }
    }

    @Override
    public float getTotalDistance() {
        return this.totalDistance;
    }

    @Override
    public int hasVisited(INode node) {
        return this.hasVisited(node.getId());
    }

    @Override
    public int hasVisited(String id) {
        final Integer result = this.visiteds.get(id);
        return result != null ? result : 0;
    }

    @Override
    public IPath clone() {
        final IPath result = new DefaultPathImplementation();
        for (IVertex vertex : this.vertexList) {
            result.addVertex(vertex);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (IVertex vertex : this.vertexList) {
            stringBuilder
                    .append(vertex.getOrigin().getId())
                    .append(" - ")
                    .append(vertex.getDistance()).append(" -> ")
                    .append(i++ >= this.vertexList.size() - 1 ? vertex.getTarget().getId() : "");
        }
        stringBuilder.append(" = ").append(this.getTotalDistance());

        return stringBuilder.toString();
    }
}
