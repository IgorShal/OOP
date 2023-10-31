package org.example.graph;

import java.util.ArrayList;

/**
 * Граф на списке смежности.
 */
public class AlGraph<T, E extends Number> extends Graph<T, E> {
    private ArrayList<ArrayList<Edge<T, E>>> adjacencyList;

    /**
     * Конструктор.
     */
    public AlGraph(ArrayList<Vertex<T>> vertexs) {
        this.setVertexs(vertexs);
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertexs.size(); i++) {
            this.adjacencyList.add(new ArrayList<Edge<T, E>>());
        }
        this.setEdges(new ArrayList<>());
    }

    /**
     * Добавляем вершину.
     */
    @Override
    public void addVertical(T value) {
        this.getVertexs().add(new Vertex<>(value));
        this.adjacencyList.add(new ArrayList<Edge<T, E>>());
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
            this.adjacencyList.get(startV).add(newE);
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
            if (edge.getStart().getValue() == value
                    || edge.getEnd().getValue() == value) {

                indexes.add(i);

            }
        }
        for (int i = 0; i < indexes.size(); i++) {
            this.getEdges().remove((int) indexes.get(i) - i);
        }

        index = this.getVertexIndex(value);

        this.adjacencyList.remove(index);
        for (int i = 0; i < this.adjacencyList.size(); i++) {
            for (int j = 0; j < this.adjacencyList.get(i).size(); j++) {
                Edge<T, E> curr = this.adjacencyList.get(i).get(j);
                if (curr.getStart().getValue() == value
                        || curr.getEnd().getValue() == value) {
                    this.adjacencyList.get(i).remove(j);
                }
            }
        }
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

            for (int i = 0; i < this.adjacencyList.size(); i++) {
                Edge<T, E> cur = this.getEdges().get(index);
                this.adjacencyList.get(i).remove(cur);
            }
            this.getEdges().remove(index);
        }
    }
}
