package com.edgardleal.graph.def;

import com.edgardleal.graph.INode;
import com.edgardleal.graph.IVertex;

public class DefaultVertexImplementation implements IVertex {

    private INode target;
    private INode origin;
    private float distance;

    public DefaultVertexImplementation(INode target, INode origin, float distance) {
        this.target = target;
        this.origin = origin;
        this.distance = distance;
    }

    @Override
    public INode getOrigin() {
        return this.origin;
    }

    @Override
    public INode getTarget() {
        return this.target;
    }

    @Override
    public float getDistance() {
        return this.distance;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f -> %s", getOrigin(), getDistance(), getTarget());
    }
}

