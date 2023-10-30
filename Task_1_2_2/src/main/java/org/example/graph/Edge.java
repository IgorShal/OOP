package org.example.graph;

/**
 * Класс ребра, ребро содержит инфу о начале, конце и весе.
 * Вес ограничен числами.
 */
public class Edge<T, E extends Number> {
    private Vertex<T> start;
    private Vertex<T> end;
    private E weight;

    /**
     * Забыл как эта штука назывется.Генератор вроде.
     */
    Edge(Object weight, Vertex<T> start, Vertex<T> end) {
        this.weight = (E) weight;
        this.start = start;
        this.end = end;

    }

    /**
     * Геттер.
     */
    public E getWeight() {
        return this.weight;
    }

    /**
     * Сеттер.
     */
    public void setWeight(E newWeight) {
        this.weight = newWeight;
    }

    /**
     * Геттер.
     */
    public Vertex<T> getStart() {
        return this.start;
    }

    /**
     * Геттер.
     */
    public Vertex<T> getEnd() {
        return this.end;
    }

    /**
     * Сеттер.
     */
    public void setStart(Vertex<T> newStart) {
        this.start = newStart;
    }

    /**
     * Сеттер.
     */
    public void setEnd(Vertex<T> newEnd) {
        this.start = newEnd;
    }

}
