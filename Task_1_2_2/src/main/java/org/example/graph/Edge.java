package org.example.graph;

public class Edge<T, E extends Number> {
    public Vertex<T> start;
    public Vertex<T> end;
    public E weight;

    Edge(Object weight, Vertex<T> start, Vertex<T> end) {
        this.weight = (E) weight;
        this.start = start;
        this.end = end;

    }

}
