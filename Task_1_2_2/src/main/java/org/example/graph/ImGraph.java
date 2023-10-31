package org.example.graph;

import java.util.ArrayList;

/**
 * Граф на матрице инцидентости.
 */
public class ImGraph<T, E extends Number> extends Graph<T, E> {
    private ArrayList<ArrayList<Number>> incidenceMatrix;


    /**
     * Конструктор.
     */
    public ImGraph(ArrayList<Vertex<T>> vertexs) {
        this.setVertexs(vertexs);
        this.incidenceMatrix = new ArrayList<>();
        for (int i = 0; i < vertexs.size(); i++) {
            this.incidenceMatrix.add(new ArrayList<Number>());
        }
        this.setEdges(new ArrayList<>());
    }

    /**
     * Добавляем вершину.
     */
    @Override
    public void addVertical(T value) {
        this.getVertexs().add(new Vertex<>(value));
        this.incidenceMatrix.add(new ArrayList<Number>());
        for (int i = 0; i < this.getVertexs().size(); i++) {
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

        for (int i = 0; i < this.incidenceMatrix.size(); i++) {
            for (int j = 0; j < indexes.size(); j++) {
                this.incidenceMatrix.get(i).remove((int) indexes.get(j) - j);
            }
        }
        this.incidenceMatrix.remove(index);
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

            for (int i = 0; i < this.incidenceMatrix.size(); i++) {
                this.incidenceMatrix.get(i).remove(index);
            }
        }

    }

}
