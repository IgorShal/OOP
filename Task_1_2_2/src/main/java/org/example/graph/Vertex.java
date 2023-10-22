package org.example.graph;

import java.util.ArrayList;
/**
 *Класс вершины, содержит инфу о названии вершины.
 *
 */
public class Vertex<T> {
    public T value;

    /**
     * Забыл как эта штука назывется.Генератор вроде.
     */
    public Vertex(T value) {
        this.value = value;
    }
}
