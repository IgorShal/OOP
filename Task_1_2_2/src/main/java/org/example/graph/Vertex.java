package org.example.graph;

import java.util.ArrayList;

public class Vertex<T, E extends Number> {
    public T value;
    public ArrayList<Edge<T, E>> incidentEdges;

    public Vertex(T value) {
        incidentEdges = new ArrayList<>();
        this.value = value;
    }
}
