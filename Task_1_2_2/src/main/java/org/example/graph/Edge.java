package org.example.graph;

/**
 *Класс ребра, ребро содержит инфу о начале, конце и весе.
 * Вес ограничен числами.
 *
 */
public class Edge<T, E extends Number> {
    public Vertex<T> start;
    public Vertex<T> end;
    public E weight;
    /**
     * Забыл как эта штука назывется.Генератор вроде.
     */
    Edge(Object weight, Vertex<T> start, Vertex<T> end) {
        this.weight = (E) weight;
        this.start = start;
        this.end = end;

    }

}
