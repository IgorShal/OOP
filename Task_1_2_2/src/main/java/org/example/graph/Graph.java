package org.example.graph;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graph<T, E extends Number> {
    public ArrayList<Vertex<T, E>> vertexs;
    public ArrayList<Edge<T, E>> edges;

    public Graph(ArrayList<Vertex<T, E>> vertexs) {
        this.vertexs = vertexs;
        this.edges = new ArrayList<>();
    }

    public void addVertical(T value) {
        this.vertexs.add(new Vertex<>(value));
    }

    public void addEdge(E value, T start, T end) {
        Vertex<T, E> startV = null;
        Vertex<T, E> endV = null;
        for (int i = 0; i < this.vertexs.size(); i++) {
            if (this.vertexs.get(i).value == start) {
                startV = this.vertexs.get(i);
            }
            if (this.vertexs.get(i).value == end) {
                endV = this.vertexs.get(i);
            }
        }
        if (startV != null && endV != null) {
            this.edges.add(new Edge<>(value, startV, endV));
        }
    }

    public void deleteVertical(T value) {
        for (int i = 0; i < this.edges.size(); i++) {
            Edge<T, E> edge = this.edges.get(i);
            if (edge.start.value == value || edge.end.value == value) {
                this.edges.remove(edge);
                i -= 1;
            }
        }
        for (Vertex<T, E> v : vertexs) {
            if (v.value == value) {
                this.vertexs.remove(v);
            }
            break;
        }
    }

    public void deleteEdge(E value, T start, T end) {
        for (int i = 0; i < this.vertexs.size(); i++) {
            Vertex<T, E> vert = this.vertexs.get(i);
            if (vert.value == start) {
                for (int j = 0; j < vert.incidentEdges.size(); j++) {
                    Edge<T, E> e = vert.incidentEdges.get(j);
                    if (e.end.value == end && Objects.equals(e.weight, value)) {
                        this.edges.remove(e);
                        vert.incidentEdges.remove(e);
                        return;
                    }

                }

            }
        }
    }


    public Vertex<T, E> getVertex(T value) {
        for (Vertex<T, E> v : this.vertexs) {
            if (v.value == value) {
                return v;
            }
        }
        return null;
    }

    public Edge<T, E> getEdge(E weight, T start, T end) {
        for (Edge<T, E> e : this.edges) {
            if (Objects.equals(e.weight, weight) && e.start.value == start && e.end.value == end) {
                return e;
            }
        }
        return null;
    }

    public void setVertex(T value, T newValue) {
        Vertex<T, E> vert = getVertex(value);
        vert.value = newValue;
    }

    public void setEdges(E weight, T start, T end, E newWeight, T newStart, T newEnd) {
        Edge<T, E> e = getEdge(weight, start, end);
        e.weight = newWeight;
        getVertex(start).incidentEdges.remove(e);
        e.start = getVertex(newStart);
        e.end = getVertex(newEnd);
        getVertex(newStart).incidentEdges.add(e);
    }

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
                if (res[j].equals("-1")) {
                    continue;
                }
                Number result = Double.parseDouble(res[i]);
                this.edges.add(new Edge<>(result, this.vertexs.get(i), this.vertexs.get(j)));
            }
        }

    }

    public void printAdjacencyMatrix() {
        int format = 0;
        for (Edge e : this.edges) {
            if ((e.weight + "").length() > format) {
                format = (e.weight + "").length() + 1;
            }
        }
        for (Vertex<T, E> v : this.vertexs) {
            System.out.printf(" %" + format + "s", v.value);
        }
        System.out.print("\n");
        for (Vertex<T, E> v : this.vertexs) {
            System.out.print(v.value);
            for (Vertex<T, E> v2 : this.vertexs) {
                boolean isConnected = false;
                for (Edge<T, E> e : this.edges) {
                    if (e.start.value == v.value && e.end.value == v2.value) {
                        isConnected = true;
                        System.out.printf(" %" + format + "s", e.weight);
                    } else if (e.end.value == v.value && e.start.value == v2.value) {
                        isConnected = true;
                        System.out.printf(" %" + format + "s", "-" + e.weight);
                    }
                }
                if (!isConnected) {
                    System.out.printf(" %" + format + "s", "inf");
                }
            }
            System.out.print("\n");
        }
    }

    public void printAdjacencyList() {
        for (Vertex<T, E> v : this.vertexs) {
            System.out.print(v.value + ":{");
            for (int i = 0; i < v.incidentEdges.size(); i++) {
                Edge<T, E> e = v.incidentEdges.get(i);
                System.out.printf("{%s,%s}", e.end.value, e.weight);
                if (i != v.incidentEdges.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("}");
        }
    }

    public void printIncidenceList() {
        int format = 0;
        for (Edge e : this.edges) {
            if ((e.weight + "").length() > format) {
                format = (e.weight + "").length() + 1;
            }
        }
        for (int i = 0; i < this.vertexs.size(); i++) {
            System.out.printf(" %" + format + "s", i);
        }
        System.out.print("\n");
        for (Vertex<T, E> v : this.vertexs) {
            System.out.print(v.value);
            for (Edge<T, E> e : this.edges) {
                if (e.start.value == v.value) {
                    System.out.printf(" %" + format + "s", e.weight);
                } else if (e.end.value == v.value) {
                    System.out.printf(" %" + format + "s", "-" + e.weight);
                } else {
                    System.out.printf(" %" + format + "s", "0");
                }

            }
            System.out.print("\n");
        }
    }


}
