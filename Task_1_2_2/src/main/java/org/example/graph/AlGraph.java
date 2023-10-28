package org.example.graph;

import java.util.ArrayList;

/**
 *Граф на списке смежности.
 */
public class AlGraph<T, E extends Number> extends Graph<T, E> {
    public ArrayList<ArrayList<Edge<T, E>>> adjacencyList;

    /**
     * Забыл как эта штука назывется.Генератор вроде.
     *
     */
    public AlGraph(ArrayList<Vertex<T>> vertexs) {
        this.vertexs = vertexs;
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertexs.size(); i++) {
            this.adjacencyList.add(new ArrayList<Edge<T, E>>());
        }
        this.edges = new ArrayList<>();
    }

    /**
     * Добавляем вершину.
     */
    @Override
    public void addVertical(T value) {
        this.vertexs.add(new Vertex<>(value));
        this.adjacencyList.add(new ArrayList<Edge<T, E>>());
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

        this.adjacencyList.remove(index);
        for (int i = 0; i < this.adjacencyList.size(); i++) {
            for (int j = 0; j < this.adjacencyList.get(i).size(); j++) {
                Edge<T, E> curr = this.adjacencyList.get(i).get(j);
                if (curr.start.value == value || curr.end.value == value) {
                    this.adjacencyList.get(i).remove(j);
                }
            }
        }
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

            for (int i = 0; i < this.adjacencyList.size(); i++) {
                Edge<T, E> cur = this.edges.get(index);
                this.adjacencyList.get(i).remove(cur);
            }
            this.edges.remove(index);
        }
    }
}
