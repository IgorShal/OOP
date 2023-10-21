package org.example.graph;

public class Edge<T, E extends Number> {
    public Vertex<T, E> start;
    public Vertex<T, E> end;
    public E weight;

    Edge(Object weight, Vertex<T, E> start, Vertex<T, E> end) {
        this.weight = (E) weight;
        this.start = start;
        this.end = end;
        start.incidentEdges.add(this);
    }

}
