package org.example.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Абстрактный класс графа.
 */
public abstract class Graph<T, E extends Number> {
    private ArrayList<Vertex<T>> vertexs;

    private ArrayList<Edge<T, E>> edges;

    /**
     * Геттер.
     */
    public ArrayList<Vertex<T>> getVertexs() {
        return this.vertexs;
    }

    /**
     * Геттер.
     */
    public ArrayList<Edge<T, E>> getEdges() {
        return this.edges;
    }

    /**
     * Сеттер.
     */
    public void setVertexs(ArrayList<Vertex<T>> newVs) {
        this.vertexs = newVs;
    }

    /**
     * Сеттер.
     */
    public void setEdges(ArrayList<Edge<T, E>> edges) {
        this.edges = edges;
    }

    public abstract void addVertical(T value);

    /**
     * Добавляем ребро.
     */
    public abstract void addEdge(E value, T start, T end);

    /**
     * Удаляем вершину.
     */
    public abstract void deleteVertical(T value);

    /**
     * Удаляем ребро.
     */
    public abstract void deleteEdge(E value, T start, T end);

    /**
     * Геттер вершины.
     */
    public int getVertex(T value) {
        for (int i = 0; i < this.vertexs.size(); i++) {
            if (this.vertexs.get(i).getValue() == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Геттер ребра.
     */
    public int getEdge(E weight, T start, T end) {
        for (int i = 0; i < this.edges.size(); i++) {
            if ((Objects.equals(this.edges.get(i).getWeight(), weight) || weight == null)
                    && this.edges.get(i).getStart().getValue() == start
                    && this.edges.get(i).getEnd().getValue() == end) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Сеттер вершины.
     */

    public void setVertex(T value, T newValue) {
        this.vertexs.get(getVertex(value)).setValue(newValue);
    }

    /**
     * Сеттер ребра.
     */
    public void setEdges(E weight, T start, T end, E newWeight, T newStart, T newEnd) {
        deleteEdge(weight, start, end);
        addEdge(newWeight, newStart, newEnd);
    }

    /**
     * Заполняем граф из файлика, e в файле значит отсутствие дороги.
     */

    public void makeGraphFromAdjacencyMatrix(String fileName) throws IOException {
        Path path = Path.of(fileName);
        List<String> list = Files.readAllLines(path);
        if (list.isEmpty()) {
            throw new IOException("Error");
        }

        for (int i = 0; i < list.size(); i++) {
            String string = list.get(i);
            String[] res = string.split(" ").clone();
            for (int j = 0; j < res.length; j++) {
                if (res[j].equals("e")) {
                    continue;
                }
                Number result = Double.parseDouble(res[j]);
                this.addEdge((E) result, this.vertexs.get(i).getValue(), this.vertexs.get(j).getValue());
            }
        }
    }

    /**
     * Фордим беллмана.
     */
    public ArrayList<Vertex<T>> bellManFord(T start, double infinity) {
        ArrayList<Vertex<T>> result = (ArrayList<Vertex<T>>) this.vertexs.clone();
        ArrayList<Number> paths = new ArrayList<>();
        for (int i = 0; i < this.vertexs.size(); i++) {
            paths.add(infinity);
        }
        paths.set(getVertex(start), 0.0);
        for (int k = 1; k < this.vertexs.size(); k++) {
            for (int i = 0; i < this.vertexs.size(); i++) {
                for (int j = 0; j < this.vertexs.size(); j++) {
                    int index = -1;
                    index = this.getEdge(null, this.vertexs.get(j).getValue(),
                            this.vertexs.get(i).getValue());
                    if (index == -1) {
                        continue;
                    }

                    double cur = (double) paths.get(j)
                            + (double) this.edges.get(index).getWeight();
                    if (cur < (double) paths.get(i)) {
                        paths.set(i, cur);
                    }
                }
            }
        }
        for (int i = 0; i < this.vertexs.size(); i++) {
            for (int j = 1; j < this.vertexs.size(); j++) {
                if ((double) paths.get(j) < (double) paths.get(j - 1)) {
                    Collections.swap(paths, j, j - 1);
                    Collections.swap(result, j, j - 1);
                }
            }
        }
        return result;
    }
}
