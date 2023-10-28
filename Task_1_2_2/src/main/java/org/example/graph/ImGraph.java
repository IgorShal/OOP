package org.example.graph;

import java.util.ArrayList;

/**
 *Граф на матрице инцидентости.
 */
public class ImGraph<T, E extends Number> extends Graph<T, E> {
    public ArrayList<ArrayList<Number>> incidenceMatrix;


    /**
     * Забыл как эта штука назывется.Генератор вроде.
     */
    public ImGraph(ArrayList<Vertex<T>> vertexs) {
        this.vertexs = vertexs;
        this.incidenceMatrix = new ArrayList<>();
        for (int i = 0; i < vertexs.size(); i++) {
            this.incidenceMatrix.add(new ArrayList<Number>());
        }
        this.edges = new ArrayList<>();
    }

    /**
     * Добавляем вершину.
     */
    @Override
    public void addVertical(T value) {
        this.vertexs.add(new Vertex<>(value));
        this.incidenceMatrix.add(new ArrayList<Number>());
        for (int i = 0; i < this.edges.size(); i++) {
            this.incidenceMatrix.get(this.incidenceMatrix.size() - 1).add(0);
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
            for (int i = 0; i < this.incidenceMatrix.size(); i++) {
                if (i == endV) {
                    this.incidenceMatrix.get(i).add(-1);
                } else if (i == startV) {
                    this.incidenceMatrix.get(i).add(1);
                } else {
                    this.incidenceMatrix.get(i).add(0);
                }
            }


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

        for (int i = 0; i < this.incidenceMatrix.size(); i++) {
            for (int j = 0; j < indexes.size(); j++) {
                this.incidenceMatrix.get(i).remove((int) indexes.get(j) - j);
            }
        }
        this.incidenceMatrix.remove(index);
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

            for (int i = 0; i < this.incidenceMatrix.size(); i++) {
                this.incidenceMatrix.get(i).remove(index);
            }
        }

    }

}
