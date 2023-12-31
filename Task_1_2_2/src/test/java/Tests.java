import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.example.graph.AlGraph;
import org.example.graph.AmGraph;
import org.example.graph.ImGraph;
import org.example.graph.Vertex;
import org.junit.jupiter.api.Test;


/**
 * Класс тестов.
 */
public class Tests {
    /**
     * Добавляем вершину через списки смежности.
     */
    @Test
    public void testAddVerticalAl() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        AlGraph<String, Integer> graph = new AlGraph<>(vertices);

        graph.addVertical("C");

        assertEquals(3, graph.getVertexs().size());
        assertEquals("C", graph.getVertexs().get(2).getValue());

    }

    /**
     * 3
     * Добавляем вершину через матрицу смежности.
     */
    @Test
    public void testAddVerticalAm() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        AmGraph<String, Integer> graph = new AmGraph<>(vertices);

        graph.addVertical("C");

        assertEquals(3, graph.getVertexs().size());
        assertEquals("C", graph.getVertexs().get(2).getValue());

    }

    /**
     * Добавляем вершину через матрицу инцидентности.
     */
    @Test
    public void testAddVerticalIm() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        ImGraph<String, Integer> graph = new ImGraph<>(vertices);

        graph.addVertical("C");

        assertEquals(3, graph.getVertexs().size());
        assertEquals("C", graph.getVertexs().get(2).getValue());

    }

    /**
     * Добавляем ребро через списки смежности.
     */
    @Test
    public void testAddEdgeAl() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        AlGraph<String, Integer> graph = new AlGraph<>(vertices);

        graph.addEdge(10, "A", "B");

        assertEquals(1, graph.getEdges().size());
        assertEquals(10, graph.getEdges().get(0).getWeight());
        assertEquals("A", graph.getEdges().get(0).getStart().getValue());
        assertEquals("B", graph.getEdges().get(0).getEnd().getValue());
    }

    /**
     * Добавляем ребро через матрицу смежности.
     */
    @Test
    public void testAddEdgeAm() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        AmGraph<String, Integer> graph = new AmGraph<>(vertices);

        graph.addEdge(10, "A", "B");

        assertEquals(1, graph.getEdges().size());
        assertEquals(10, graph.getEdges().get(0).getWeight());
        assertEquals("A", graph.getEdges().get(0).getStart().getValue());
        assertEquals("B", graph.getEdges().get(0).getEnd().getValue());
    }

    /**
     * Добавляем ребро через матрицу инцидентности.
     */
    @Test
    public void testAddEdgeIm() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        ImGraph<String, Integer> graph = new ImGraph<>(vertices);

        graph.addEdge(10, "A", "B");

        assertEquals(1, graph.getEdges().size());
        assertEquals(10, graph.getEdges().get(0).getWeight());
        assertEquals("A", graph.getEdges().get(0).getStart().getValue());
        assertEquals("B", graph.getEdges().get(0).getEnd().getValue());
    }

    /**
     * Удаляем вершину через списки смежности.
     */
    @Test
    public void testDeleteVerticalAl() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2, v3));
        AlGraph<String, Integer> graph = new AlGraph<>(vertices);
        graph.addEdge(5, "A", "B");
        graph.addEdge(5, "B", "C");
        graph.deleteVertical("B");

        assertEquals(2, graph.getVertexs().size());
        assertEquals("A", graph.getVertexs().get(0).getValue());
        assertEquals("C", graph.getVertexs().get(1).getValue());
        assertEquals(0, graph.getEdges().size());
    }

    /**
     * Удаляем вершину через матрицу смежности.
     */
    @Test
    public void testDeleteVerticalAm() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2, v3));
        AmGraph<String, Integer> graph = new AmGraph<>(vertices);
        graph.addEdge(5, "A", "B");
        graph.addEdge(5, "B", "C");
        graph.deleteVertical("B");

        assertEquals(2, graph.getVertexs().size());
        assertEquals("A", graph.getVertexs().get(0).getValue());
        assertEquals("C", graph.getVertexs().get(1).getValue());
        assertEquals(0, graph.getEdges().size());
    }

    /**
     * Удаляем вершину через матрицу инцидентности.
     */
    @Test
    public void testDeleteVerticalIm() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2, v3));
        ImGraph<String, Integer> graph = new ImGraph<>(vertices);
        graph.addEdge(5, "A", "B");
        graph.addEdge(5, "B", "C");
        graph.deleteVertical("B");

        assertEquals(2, graph.getVertexs().size());
        assertEquals("A", graph.getVertexs().get(0).getValue());
        assertEquals("C", graph.getVertexs().get(1).getValue());
        assertEquals(0, graph.getEdges().size());
    }

    /**
     * Удаляем ребро через списки смежности.
     */
    @Test
    public void testDeleteEdgeAl() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2));
        AlGraph<String, Integer> graph = new AlGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.deleteEdge(10, "A", "B");

        assertEquals(0, graph.getEdges().size());
    }

    /**
     * Удаляем ребро через матрицу смежности.
     */
    @Test
    public void testDeleteEdgeAm() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2));
        AmGraph<String, Integer> graph = new AmGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.deleteEdge(10, "A", "B");

        assertEquals(0, graph.getEdges().size());
    }

    /**
     * Удаляем ребро через матрицу инцидентности.
     */
    @Test
    public void testDeleteEdgeIm() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2));
        ImGraph<String, Integer> graph = new ImGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.deleteEdge(10, "A", "B");

        assertEquals(0, graph.getEdges().size());
    }

    /**
     * Пример из условия через матрицу смежности.
     */
    @Test
    public void testBellmanFordUsingAmatrix() throws Exception {
        ArrayList<Vertex<String>> vertexes = new ArrayList<>();
        vertexes.add(new Vertex<String>("A"));
        vertexes.add(new Vertex<String>("B"));
        vertexes.add(new Vertex<String>("C"));
        vertexes.add(new Vertex<String>("D"));
        vertexes.add(new Vertex<String>("E"));
        vertexes.add(new Vertex<String>("F"));
        vertexes.add(new Vertex<String>("G"));
        AmGraph<String, Double> graph = new AmGraph<>(vertexes);
        graph.makeGraphFromAdjacencyMatrix("1.txt");
        ArrayList<Vertex<String>> result;
        result = graph.bellManFord("C", 1000);

        assertEquals("C", result.get(0).getValue());
        assertEquals("D", result.get(1).getValue());
        assertEquals("E", result.get(2).getValue());
        assertEquals("F", result.get(3).getValue());
        assertEquals("G", result.get(4).getValue());
        assertEquals("B", result.get(5).getValue());
        assertEquals("A", result.get(6).getValue());
    }

    /**
     * Пример из условия через списки смежности.
     */
    @Test
    public void testBellmanFordUsingAlist() throws Exception {
        ArrayList<Vertex<String>> vertexes = new ArrayList<>();
        vertexes.add(new Vertex<String>("A"));
        vertexes.add(new Vertex<String>("B"));
        vertexes.add(new Vertex<String>("C"));
        vertexes.add(new Vertex<String>("D"));
        vertexes.add(new Vertex<String>("E"));
        vertexes.add(new Vertex<String>("F"));
        vertexes.add(new Vertex<String>("G"));
        AlGraph<String, Double> graph = new AlGraph<>(vertexes);
        graph.makeGraphFromAdjacencyMatrix("1.txt");
        ArrayList<Vertex<String>> result;
        result = graph.bellManFord("C", 1000);

        assertEquals("C", result.get(0).getValue());
        assertEquals("D", result.get(1).getValue());
        assertEquals("E", result.get(2).getValue());
        assertEquals("F", result.get(3).getValue());
        assertEquals("G", result.get(4).getValue());
        assertEquals("B", result.get(5).getValue());
        assertEquals("A", result.get(6).getValue());
    }

    /**
     * Пример из условия через матрицу инцидентности.
     */
    @Test
    public void testBellmanFordUsingImatrix() throws Exception {
        ArrayList<Vertex<String>> vertexes = new ArrayList<>();
        vertexes.add(new Vertex<String>("A"));
        vertexes.add(new Vertex<String>("B"));
        vertexes.add(new Vertex<String>("C"));
        vertexes.add(new Vertex<String>("D"));
        vertexes.add(new Vertex<String>("E"));
        vertexes.add(new Vertex<String>("F"));
        vertexes.add(new Vertex<String>("G"));
        ImGraph<String, Double> graph = new ImGraph<>(vertexes);
        graph.makeGraphFromAdjacencyMatrix("1.txt");
        ArrayList<Vertex<String>> result;
        result = graph.bellManFord("C", 1000);

        assertEquals("C", result.get(0).getValue());
        assertEquals("D", result.get(1).getValue());
        assertEquals("E", result.get(2).getValue());
        assertEquals("F", result.get(3).getValue());
        assertEquals("G", result.get(4).getValue());
        assertEquals("B", result.get(5).getValue());
        assertEquals("A", result.get(6).getValue());
    }

    /**
     * Меняем вершину через списки смежности.
     */
    @Test
    public void testSetVertexAl() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        AlGraph<String, Integer> graph = new AlGraph<>(vertices);

        graph.setVertex("A", "C");

        assertEquals("C", graph.getVertexs().get(0).getValue());
    }

    /**
     * Меняем вершину через матрицу смежности.
     */
    @Test
    public void testSetVertexAm() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        AmGraph<String, Integer> graph = new AmGraph<>(vertices);

        graph.setVertex("A", "C");

        assertEquals("C", graph.getVertexs().get(0).getValue());
    }

    /**
     * Меняем вершину через матрицу инцидентности.
     */
    @Test
    public void testSetVertexIm() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        ImGraph<String, Integer> graph = new ImGraph<>(vertices);

        graph.setVertex("A", "C");

        assertEquals("C", graph.getVertexs().get(0).getValue());
    }

    /**
     * Меняем ребро  через списки смежности.
     */
    @Test
    public void testSetEdgesAl() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        vertices.add(vertex3);
        AlGraph<String, Integer> graph = new AlGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.setEdge(10, "A", "B", 20, "C", "B");

        assertEquals(1, graph.getEdges().size());
        assertEquals(20, graph.getEdges().get(0).getWeight());
        assertEquals("C", graph.getEdges().get(0).getStart().getValue());
        assertEquals("B", graph.getEdges().get(0).getEnd().getValue());
    }

    /**
     * Меняем ребро через матрицу смежности.
     */
    @Test
    public void testSetEdgesAm() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        vertices.add(vertex3);
        AmGraph<String, Integer> graph = new AmGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.setEdge(10, "A", "B", 20, "C", "B");

        assertEquals(1, graph.getEdges().size());
        assertEquals(20, graph.getEdges().get(0).getWeight());
        assertEquals("C", graph.getEdges().get(0).getStart().getValue());
        assertEquals("B", graph.getEdges().get(0).getEnd().getValue());
    }

    /**
     * Меняем ребро через матрицу инцидентности.
     */
    @Test
    public void testSetEdgesIm() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        vertices.add(vertex3);
        ImGraph<String, Integer> graph = new ImGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.setEdge(10, "A", "B", 20, "C", "B");

        assertEquals(1, graph.getEdges().size());
        assertEquals(20, graph.getEdges().get(0).getWeight());
        assertEquals("C", graph.getEdges().get(0).getStart().getValue());
        assertEquals("B", graph.getEdges().get(0).getEnd().getValue());
    }

    /**
     * Заполняем из файла.
     */
    @Test
    public void testMakeGraphFromAdjacencyMatrix() throws IOException {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");

        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        vertices.add(vertex3);
        AlGraph<String, Double> graph = new AlGraph<>(vertices);

        graph.makeGraphFromAdjacencyMatrix("adjacency_matrix.txt");

        assertEquals(9, graph.getEdges().size());
        assertEquals(5, graph.getEdges().get(0).getWeight());
        assertEquals("A", graph.getEdges().get(0).getStart().getValue());
        assertEquals("A", graph.getEdges().get(0).getEnd().getValue());
        assertEquals(10, graph.getEdges().get(1).getWeight());
        assertEquals("A", graph.getEdges().get(1).getStart().getValue());
        assertEquals("B", graph.getEdges().get(1).getEnd().getValue());
        assertEquals(10, graph.getEdges().get(2).getWeight());
        assertEquals("A", graph.getEdges().get(2).getStart().getValue());
        assertEquals("C", graph.getEdges().get(2).getEnd().getValue());
    }
}


