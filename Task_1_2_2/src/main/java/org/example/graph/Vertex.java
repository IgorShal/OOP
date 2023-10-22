package org.example.graph;

import java.util.ArrayList;
/**
 *Класс вершины, содержит инфу о названии вершины.
 *
 */
public class Vertex<T> {
    public T value;


    public Vertex(T value) {
        this.value = value;
    }
}
