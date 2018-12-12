package com.edgardleal.graph.def;

import com.edgardleal.graph.IGraph;
import com.edgardleal.graph.INode;
import com.edgardleal.graph.IVertex;
import com.edgardleal.graph.NodeNotFoundException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class DefaultGraphImplementation implements IGraph {
    private List<INode> nodes;
    private Map<String, INode> nodeMap;

    public DefaultGraphImplementation() {
        this.nodes = new LinkedList<>();
        this.nodeMap = new HashMap<>();
    }

    @Override
    public void addNode(INode node) {
        this.nodeMap.put(node.getId(), node);
        this.nodes.add(node);
    }

    @Override
    public IVertex createVertex(String origin, String target, float distance) throws NodeNotFoundException {
        final INode originNode = getNode(origin);
        final IVertex iVertex = new DefaultVertexImplementation(getNode(target), originNode, distance);

        originNode.addVertex(iVertex);

        return iVertex;
    }

    @Override
    public INode getNode(String key) throws NodeNotFoundException {
        final INode result = this.nodeMap.get(key);
        if (result == null) throw new NodeNotFoundException("Node not found: " + key);
        return result;
    }
}

