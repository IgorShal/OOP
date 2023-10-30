package org.example.graph;


/**
 * Класс вершины, содержит инфу о названии вершины.
 */
public class Vertex<T> {
    private T value;

    /**
     * Забыл как эта штука назывется.Генератор вроде.
     */
    public Vertex(T value) {
        this.value = value;
    }
    /**
     * Геттер.
     */
    public T getValue(){
        return this.value;
    }
    /**
     * Сеттер.
     */
    public void setValue(T newValue){
        this.value = newValue;
    }
}
