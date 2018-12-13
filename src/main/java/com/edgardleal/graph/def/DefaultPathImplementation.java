package com.edgardleal.graph.def;

import com.edgardleal.graph.INode;
import com.edgardleal.graph.IPath;
import com.edgardleal.graph.IVertex;

import java.util.*;

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
        this.visiteds.merge(key, 1, (a, b) -> a + b);
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

    @Override
    public int size() {
        return this.vertexList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.vertexList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return o != null && o instanceof IPath && this.hasVisited((INode) o) > 0;
    }

    @Override
    public Iterator<IVertex> iterator() {
        return this.vertexList.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.vertexList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(IVertex iVertex) {
        return this.add(iVertex);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends IVertex> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends IVertex> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        this.vertexList.clear();
        this.visiteds.clear();
    }

    @Override
    public IVertex get(int index) {
        return this.vertexList.get(index);
    }

    @Override
    public IVertex set(int index, IVertex element) {
        return null;
    }

    @Override
    public void add(int index, IVertex element) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public IVertex remove(int index) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int indexOf(Object o) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ListIterator<IVertex> listIterator() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ListIterator<IVertex> listIterator(int index) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<IVertex> subList(int fromIndex, int toIndex) {
        throw new RuntimeException("Not implemented");
    }

}
