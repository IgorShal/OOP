package org.example.graph;

import java.util.ArrayList;

/**
 *Граф на матрице смежности.
 */
public class AmGraph<T, E extends Number> extends Graph<T, E> {
    public ArrayList<ArrayList<E>> adjacencyMatrix;


    /**
     * Забыл как эта штука назывется.Генератор вроде.
     *
     */
    public AmGraph(ArrayList<Vertex<T>> vertexs) {
        this.vertexs = vertexs;
        this.adjacencyMatrix = new ArrayList<>();
        for (int i = 0; i < vertexs.size(); i++) {
            this.adjacencyMatrix.add(new ArrayList<E>());
            for (int j = 0; j < vertexs.size(); j++) {
                this.adjacencyMatrix.get(i).add(null);
            }
        }
        this.edges = new ArrayList<>();
    }

    /**
     * Добавляем вершину.
     */
    @Override
    public void addVertical(T value) {
        this.vertexs.add(new Vertex<>(value));
        this.adjacencyMatrix.add(new ArrayList<E>());

        for (int i = 0; i < vertexs.size(); i++) {
            this.adjacencyMatrix.get(i).add(null);
            this.adjacencyMatrix.get(vertexs.size() - 1).add(null);
        }
    }

    /**
     * Добавляем ребро.
     */
    @Override
    public void addEdge(E value, T start, T end) {
        int startV = -1;
        int endV = -1;
        for (int i = 0; i < this.vertexs.size(); i++) {
            if (this.vertexs.get(i).value == start) {
                startV = i;
            }
            if (this.vertexs.get(i).value == end) {
                endV = i;
            }
        }
        if (startV != -1 && endV != -1) {
            Edge<T, E> newE = new Edge<>(value, this.vertexs.get(startV), this.vertexs.get(endV));
            this.edges.add(newE);
            this.adjacencyMatrix.get(startV).set(endV, value);


        }
    }

    /**
     * Удаляем вершину.
     */
    @Override
    public void deleteVertical(T value) {
        int index = -1;
        ArrayList<Number> indexes = new ArrayList<>();
        for (int i = 0; i < this.edges.size(); i++) {
            Edge<T, E> edge = this.edges.get(i);
            if (edge.start.value == value || edge.end.value == value) {
                indexes.add(i);
            }
        }
        for (int i = 0; i < indexes.size(); i++) {
            this.edges.remove((int) indexes.get(i) - i);
        }
        index = getVertex(value);
        for (int i = 0; i < this.adjacencyMatrix.size(); i++) {
            this.adjacencyMatrix.get(i).remove(index);
        }
        this.adjacencyMatrix.remove(index);
        this.vertexs.remove(index);
    }

    /**
     * Удаляем ребро.
     */
    @Override
    public void deleteEdge(E value, T start, T end) {
        int index = -1;
        index = getEdge(value, start, end);
        if (index != -1) {
            this.edges.remove(index);
            int startVertIndex = getVertex(start);
            int endVertIndex = getVertex(end);
            this.adjacencyMatrix.get(startVertIndex).set(endVertIndex, null);
            this.adjacencyMatrix.get(endVertIndex).set(startVertIndex, null);
        }

    }
}
