package org.example.graph;

import java.util.ArrayList;

/**
 *Граф на матрице смежности.
 */
public class AmGraph<T, E extends Number> extends Graph<T, E> {
    private ArrayList<ArrayList<E>> adjacencyMatrix;


    /**
     * Конструктор.
     *
     */
    public AmGraph(ArrayList<Vertex<T>> vertexs) {
        this.setVertexs(vertexs);
        this.adjacencyMatrix = new ArrayList<>();
        for (int i = 0; i < vertexs.size(); i++) {
            this.adjacencyMatrix.add(new ArrayList<E>());
            for (int j = 0; j < vertexs.size(); j++) {
                this.adjacencyMatrix.get(i).add(null);
            }
        }
        this.setEdges(new ArrayList<>());
    }

    /**
     * Добавляем вершину.
     */
    @Override
    public void addVertical(T value) {
        this.getVertexs().add(new Vertex<>(value));
        this.adjacencyMatrix.add(new ArrayList<E>());

        for (int i = 0; i < this.getVertexs().size(); i++) {
            this.adjacencyMatrix.get(i).add(null);
            this.adjacencyMatrix.get(this.getVertexs().size() - 1).add(null);
        }
    }

    /**
     * Добавляем ребро.
     */
    @Override
    public void addEdge(E value, T start, T end) {
        int startV = -1;
        int endV = -1;
        for (int i = 0; i < this.getVertexs().size(); i++) {
            if (this.getVertexs().get(i).getValue() == start) {
                startV = i;
            }
            if (this.getVertexs().get(i).getValue() == end) {
                endV = i;
            }
        }
        if (startV != -1 && endV != -1) {
            Edge<T, E> newE = new Edge<>(value, this.getVertexs().get(startV),
                    this.getVertexs().get(endV));
            this.getEdges().add(newE);
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
        for (int i = 0; i < this.getEdges().size(); i++) {
            Edge<T, E> edge = this.getEdges().get(i);
            if (edge.getStart().getValue() == value ||
                    edge.getEnd().getValue()== value) {
                indexes.add(i);
            }
        }
        for (int i = 0; i < indexes.size(); i++) {
            this.getEdges().remove((int) indexes.get(i) - i);
        }
        index = getVertex(value);
        for (int i = 0; i < this.adjacencyMatrix.size(); i++) {
            this.adjacencyMatrix.get(i).remove(index);
        }
        this.adjacencyMatrix.remove(index);
        this.getVertexs().remove(index);
    }

    /**
     * Удаляем ребро.
     */
    @Override
    public void deleteEdge(E value, T start, T end) {
        int index = -1;
        index = getEdge(value, start, end);
        if (index != -1) {
            this.getEdges().remove(index);
            int startVertIndex = getVertex(start);
            int endVertIndex = getVertex(end);
            this.adjacencyMatrix.get(startVertIndex).set(endVertIndex, null);
            this.adjacencyMatrix.get(endVertIndex).set(startVertIndex, null);
        }

    }
}
