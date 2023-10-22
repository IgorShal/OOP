package org.example.graph;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Класс графа, из условия неособо понял, что от нас требуется,
 * поэтому сделал все три имплементации в одном классе.
 */
public class Graph<T, E extends Number> {
    public ArrayList<Vertex<T>> vertexs;
    public ArrayList<Edge<T, E>> edges;

    public ArrayList<ArrayList<Edge<T, E>>> adjacencyList;
    public ArrayList<ArrayList<E>> adjacencyMatrix;

    public ArrayList<ArrayList<Number>> incidenceMatrix;

    public Graph(ArrayList<Vertex<T>> vertexs) {
        this.vertexs = vertexs;
        this.adjacencyMatrix = new ArrayList<>();
        this.incidenceMatrix = new ArrayList<>();
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertexs.size(); i++) {
            this.adjacencyList.add(new ArrayList<Edge<T, E>>());
            this.adjacencyMatrix.add(new ArrayList<E>());
            for (int j = 0; j < vertexs.size(); j++) {
                this.adjacencyMatrix.get(i).add(null);
            }
            this.incidenceMatrix.add(new ArrayList<Number>());

        }
        this.edges = new ArrayList<>();
    }

    /**
     * Добавляем вершину.
     */
    public void addVertical(T value) {
        this.vertexs.add(new Vertex<>(value));
        this.adjacencyList.add(new ArrayList<Edge<T, E>>());
        this.adjacencyMatrix.add(new ArrayList<E>());

        for (int i = 0; i < vertexs.size(); i++) {
            this.adjacencyMatrix.get(i).add(null);
            this.adjacencyMatrix.get(vertexs.size() - 1).add(null);
        }
        this.incidenceMatrix.add(new ArrayList<Number>());
        for (int i = 0; i < this.edges.size(); i++) {
            this.incidenceMatrix.get(this.incidenceMatrix.size() - 1).add(0);
        }
    }

    /**
     * Добавляем ребро.
     */
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
            Edge<T, E> newEdge = new Edge<>(value, this.vertexs.get(startV), this.vertexs.get(endV));
            this.edges.add(newEdge);
            this.adjacencyList.get(startV).add(newEdge);
            this.adjacencyMatrix.get(startV).set(endV, value);
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

        for (int i = 0; i < this.adjacencyMatrix.size(); i++) {
            this.adjacencyMatrix.get(i).remove(index);
        }
        this.adjacencyMatrix.remove(index);

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
    public void deleteEdge(E value, T start, T end) {
        int index = -1;
        index = getEdge(value, start, end);
        if (index != -1) {

            for (int i = 0; i < this.adjacencyList.size(); i++) {
                Edge<T, E> cur = this.edges.get(index);
                this.adjacencyList.get(i).remove(cur);
            }
            this.edges.remove(index);
            int startVertIndex = getVertex(start);
            int endVertIndex = getVertex(end);
            this.adjacencyMatrix.get(startVertIndex).set(endVertIndex, null);
            this.adjacencyMatrix.get(endVertIndex).set(startVertIndex, null);

            for (int i = 0; i < this.incidenceMatrix.size(); i++) {
                this.incidenceMatrix.get(i).remove(index);
            }
        }

    }

    /**
     * Геттер вершины.
     */
    public int getVertex(T value) {
        for (int i = 0; i < this.vertexs.size(); i++) {
            if (this.vertexs.get(i).value == value) {
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
            if (Objects.equals(this.edges.get(i).weight, weight) && this.edges.get(i).start.value == start
                    && this.edges.get(i).end.value == end) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Сеттер вершины.
     */
    public void setVertex(T value, T newValue) {
        this.vertexs.get(getVertex(value)).value = newValue;
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
                this.addEdge((E) result, this.vertexs.get(i).value, this.vertexs.get(j).value);
            }
        }
    }

    /**
     * Форд-Беллман через МС.
     */
    public ArrayList<Vertex<T>> bellManFordUsingAmatrix(T start, double infinity) {
        ArrayList<Vertex<T>> result = (ArrayList<Vertex<T>>) this.vertexs.clone();
        ArrayList<Number> paths = new ArrayList<>();
        for (int i = 0; i < this.vertexs.size(); i++) {
            paths.add(infinity);
        }
        paths.set(getVertex(start), 0.0);
        for (int k = 1; k < this.vertexs.size(); k++) {
            for (int i = 0; i < this.vertexs.size(); i++) {
                for (int j = 0; j < this.vertexs.size(); j++) {
                    if (this.adjacencyMatrix.get(j).get(i) == null)
                        continue;
                    double cur = (double) paths.get(j)
                            + (double) this.adjacencyMatrix.get(j).get(i);
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

    /**
     * Форд-Беллман через СС.
     */
    public ArrayList<Vertex<T>> bellManFordUsingAlist(T start, double infinity) {
        ArrayList<Vertex<T>> result = (ArrayList<Vertex<T>>) this.vertexs.clone();
        ArrayList<Number> paths = new ArrayList<>();
        for (int i = 0; i < this.vertexs.size(); i++) {
            paths.add(infinity);
        }
        paths.set(getVertex(start), 0.0);
        for (int k = 1; k < this.vertexs.size(); k++) {
            for (int i = 0; i < this.vertexs.size(); i++) {
                for (int j = 0; j < this.vertexs.size(); j++) {
                    if (i >= this.adjacencyList.get(j).size()) {
                        continue;
                    }
                    double cur = (double) paths.get(j)
                            + (double) this.adjacencyList.get(j).get(i).weight;
                    int index = getVertex(this.adjacencyList.get(j).get(i).end.value);
                    if (cur < (double) paths.get(index)) {
                        paths.set(index, cur);
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

    /**
     * Форд-Беллман через МИ.
     */
    public ArrayList<Vertex<T>> bellManFordUsingImatrix(T start, double infinity) {
        ArrayList<Vertex<T>> result = (ArrayList<Vertex<T>>) this.vertexs.clone();
        ArrayList<Number> paths = new ArrayList<>();
        for (int i = 0; i < this.vertexs.size(); i++) {
            paths.add(infinity);
        }
        paths.set(getVertex(start), 0.0);
        for (int k = 1; k < this.vertexs.size(); k++) {
            for (int i = 0; i < this.edges.size(); i++) {
                for (int j = 0; j < this.vertexs.size(); j++) {
                    if (((int) this.incidenceMatrix.get(j).get(i) == 0)) {
                        continue;
                    }
                    Edge<T, E> curEdge = this.edges.get(i);
                    int end = getVertex(curEdge.end.value);
                    double cur = (double) paths.get(j)
                            + (double) curEdge.weight;
                    if (cur < (double) paths.get(end)) {
                        paths.set(end, cur);
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
