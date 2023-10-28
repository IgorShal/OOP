import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.example.graph.ALGraph;
import org.example.graph.AMGraph;
import org.example.graph.IMGraph;
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
    public void testAddVerticalAL() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        ALGraph<String, Integer> graph = new ALGraph<>(vertices);

        graph.addVertical("C");

        assertEquals(3, graph.vertexs.size());
        assertEquals("C", graph.vertexs.get(2).value);

    }

    /**
     * Добавляем вершину через матрицу смежности.
     */
    @Test
    public void testAddVerticalAM() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        AMGraph<String, Integer> graph = new AMGraph<>(vertices);

        graph.addVertical("C");

        assertEquals(3, graph.vertexs.size());
        assertEquals("C", graph.vertexs.get(2).value);

    }

    /**
     * Добавляем вершину через матрицу инцидентности.
     */
    @Test
    public void testAddVerticalIM() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        IMGraph<String, Integer> graph = new IMGraph<>(vertices);

        graph.addVertical("C");

        assertEquals(3, graph.vertexs.size());
        assertEquals("C", graph.vertexs.get(2).value);

    }

    /**
     * Добавляем ребро через списки смежности.
     */
    @Test
    public void testAddEdgeAL() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        ALGraph<String, Integer> graph = new ALGraph<>(vertices);

        graph.addEdge(10, "A", "B");

        assertEquals(1, graph.edges.size());
        assertEquals(10, graph.edges.get(0).weight);
        assertEquals("A", graph.edges.get(0).start.value);
        assertEquals("B", graph.edges.get(0).end.value);
    }

    /**
     * Добавляем ребро через матрицу смежности.
     */
    @Test
    public void testAddEdgeAM() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        AMGraph<String, Integer> graph = new AMGraph<>(vertices);

        graph.addEdge(10, "A", "B");

        assertEquals(1, graph.edges.size());
        assertEquals(10, graph.edges.get(0).weight);
        assertEquals("A", graph.edges.get(0).start.value);
        assertEquals("B", graph.edges.get(0).end.value);
    }

    /**
     * Добавляем ребро через матрицу инцидентности.
     */
    @Test
    public void testAddEdgeIM() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        IMGraph<String, Integer> graph = new IMGraph<>(vertices);

        graph.addEdge(10, "A", "B");

        assertEquals(1, graph.edges.size());
        assertEquals(10, graph.edges.get(0).weight);
        assertEquals("A", graph.edges.get(0).start.value);
        assertEquals("B", graph.edges.get(0).end.value);
    }

    /**
     * Удаляем вершину через списки смежности.
     */
    @Test
    public void testDeleteVerticalAL() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2, v3));
        ALGraph<String, Integer> graph = new ALGraph<>(vertices);
        graph.addEdge(5, "A", "B");
        graph.addEdge(5, "B", "C");
        graph.deleteVertical("B");

        assertEquals(2, graph.vertexs.size());
        assertEquals("A", graph.vertexs.get(0).value);
        assertEquals("C", graph.vertexs.get(1).value);
        assertEquals(0, graph.edges.size());
    }

    /**
     * Удаляем вершину через матрицу смежности.
     */
    @Test
    public void testDeleteVerticalAM() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2, v3));
        AMGraph<String, Integer> graph = new AMGraph<>(vertices);
        graph.addEdge(5, "A", "B");
        graph.addEdge(5, "B", "C");
        graph.deleteVertical("B");

        assertEquals(2, graph.vertexs.size());
        assertEquals("A", graph.vertexs.get(0).value);
        assertEquals("C", graph.vertexs.get(1).value);
        assertEquals(0, graph.edges.size());
    }

    /**
     * Удаляем вершину через матрицу инцидентности.
     */
    @Test
    public void testDeleteVerticalIM() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2, v3));
        IMGraph<String, Integer> graph = new IMGraph<>(vertices);
        graph.addEdge(5, "A", "B");
        graph.addEdge(5, "B", "C");
        graph.deleteVertical("B");

        assertEquals(2, graph.vertexs.size());
        assertEquals("A", graph.vertexs.get(0).value);
        assertEquals("C", graph.vertexs.get(1).value);
        assertEquals(0, graph.edges.size());
    }

    /**
     * Удаляем ребро через списки смежности.
     */
    @Test
    public void testDeleteEdgeAL() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2));
        ALGraph<String, Integer> graph = new ALGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.deleteEdge(10, "A", "B");

        assertEquals(0, graph.edges.size());
    }

    /**
     * Удаляем ребро через матрицу смежности.
     */
    @Test
    public void testDeleteEdgeAM() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2));
        AMGraph<String, Integer> graph = new AMGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.deleteEdge(10, "A", "B");

        assertEquals(0, graph.edges.size());
    }

    /**
     * Удаляем ребро через матрицу инцидентности.
     */
    @Test
    public void testDeleteEdgeIM() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(v1, v2));
        IMGraph<String, Integer> graph = new IMGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.deleteEdge(10, "A", "B");

        assertEquals(0, graph.edges.size());
    }

    /**
     * Пример из условия через матрицу смежности.
     */
    @Test
    public void testBellmanFordUsingAmatrix() throws IOException {
        ArrayList<Vertex<String>> vertexes = new ArrayList<>();
        vertexes.add(new Vertex<String>("A"));
        vertexes.add(new Vertex<String>("B"));
        vertexes.add(new Vertex<String>("C"));
        vertexes.add(new Vertex<String>("D"));
        vertexes.add(new Vertex<String>("E"));
        vertexes.add(new Vertex<String>("F"));
        vertexes.add(new Vertex<String>("G"));
        AMGraph<String, Double> graph = new AMGraph<>(vertexes);
        graph.makeGraphFromAdjacencyMatrix("1.txt");
        ArrayList<Vertex<String>> result;
        result = graph.bellManFord("C", 1000);

        assertEquals("C", result.get(0).value);
        assertEquals("D", result.get(1).value);
        assertEquals("E", result.get(2).value);
        assertEquals("F", result.get(3).value);
        assertEquals("G", result.get(4).value);
        assertEquals("B", result.get(5).value);
        assertEquals("A", result.get(6).value);
    }

    /**
     * Пример из условия через списки смежности.
     */
    @Test
    public void testBellmanFordUsingAlist() throws IOException {
        ArrayList<Vertex<String>> vertexes = new ArrayList<>();
        vertexes.add(new Vertex<String>("A"));
        vertexes.add(new Vertex<String>("B"));
        vertexes.add(new Vertex<String>("C"));
        vertexes.add(new Vertex<String>("D"));
        vertexes.add(new Vertex<String>("E"));
        vertexes.add(new Vertex<String>("F"));
        vertexes.add(new Vertex<String>("G"));
        ALGraph<String, Double> graph = new ALGraph<>(vertexes);
        graph.makeGraphFromAdjacencyMatrix("1.txt");
        ArrayList<Vertex<String>> result;
        result = graph.bellManFord("C", 1000);

        assertEquals("C", result.get(0).value);
        assertEquals("D", result.get(1).value);
        assertEquals("E", result.get(2).value);
        assertEquals("F", result.get(3).value);
        assertEquals("G", result.get(4).value);
        assertEquals("B", result.get(5).value);
        assertEquals("A", result.get(6).value);
    }

    /**
     * Пример из условия через матрицу инцидентности.
     */
    @Test
    public void testBellmanFordUsingImatrix() throws IOException {
        ArrayList<Vertex<String>> vertexes = new ArrayList<>();
        vertexes.add(new Vertex<String>("A"));
        vertexes.add(new Vertex<String>("B"));
        vertexes.add(new Vertex<String>("C"));
        vertexes.add(new Vertex<String>("D"));
        vertexes.add(new Vertex<String>("E"));
        vertexes.add(new Vertex<String>("F"));
        vertexes.add(new Vertex<String>("G"));
        IMGraph<String, Double> graph = new IMGraph<>(vertexes);
        graph.makeGraphFromAdjacencyMatrix("1.txt");
        ArrayList<Vertex<String>> result;
        result = graph.bellManFord("C", 1000);

        assertEquals("C", result.get(0).value);
        assertEquals("D", result.get(1).value);
        assertEquals("E", result.get(2).value);
        assertEquals("F", result.get(3).value);
        assertEquals("G", result.get(4).value);
        assertEquals("B", result.get(5).value);
        assertEquals("A", result.get(6).value);
    }

    /**
     * Меняем вершину через списки смежности.
     */
    @Test
    public void testSetVertexAL() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        ALGraph<String, Integer> graph = new ALGraph<>(vertices);

        graph.setVertex("A", "C");

        assertEquals("C", graph.vertexs.get(0).value);
    }

    /**
     * Меняем вершину через матрицу смежности.
     */
    @Test
    public void testSetVertexAM() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        AMGraph<String, Integer> graph = new AMGraph<>(vertices);

        graph.setVertex("A", "C");

        assertEquals("C", graph.vertexs.get(0).value);
    }

    /**
     * Меняем вершину через матрицу инцидентности.
     */
    @Test
    public void testSetVertexIM() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        IMGraph<String, Integer> graph = new IMGraph<>(vertices);

        graph.setVertex("A", "C");

        assertEquals("C", graph.vertexs.get(0).value);
    }

    /**
     * Меняем ребро  через списки смежности.
     */
    @Test
    public void testSetEdgesAL() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        vertices.add(vertex3);
        ALGraph<String, Integer> graph = new ALGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.setEdges(10, "A", "B", 20, "C", "B");

        assertEquals(1, graph.edges.size());
        assertEquals(20, graph.edges.get(0).weight);
        assertEquals("C", graph.edges.get(0).start.value);
        assertEquals("B", graph.edges.get(0).end.value);
    }

    /**
     * Меняем ребро через матрицу смежности.
     */
    @Test
    public void testSetEdgesAM() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        vertices.add(vertex3);
        AMGraph<String, Integer> graph = new AMGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.setEdges(10, "A", "B", 20, "C", "B");

        assertEquals(1, graph.edges.size());
        assertEquals(20, graph.edges.get(0).weight);
        assertEquals("C", graph.edges.get(0).start.value);
        assertEquals("B", graph.edges.get(0).end.value);
    }

    /**
     * Меняем ребро через матрицу инцидентности.
     */
    @Test
    public void testSetEdgesIM() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        vertices.add(vertex3);
        IMGraph<String, Integer> graph = new IMGraph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.setEdges(10, "A", "B", 20, "C", "B");

        assertEquals(1, graph.edges.size());
        assertEquals(20, graph.edges.get(0).weight);
        assertEquals("C", graph.edges.get(0).start.value);
        assertEquals("B", graph.edges.get(0).end.value);
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
        ALGraph<String, Double> graph = new ALGraph<>(vertices);

        graph.makeGraphFromAdjacencyMatrix("adjacency_matrix.txt");

        assertEquals(3, graph.edges.size());
        assertEquals(10.0, graph.edges.get(0).weight);
        assertEquals("A", graph.edges.get(0).start.value);
        assertEquals("B", graph.edges.get(0).end.value);
        assertEquals(8.5, graph.edges.get(1).weight);
        assertEquals("B", graph.edges.get(1).start.value);
        assertEquals("C", graph.edges.get(1).end.value);
        assertEquals(15.0, graph.edges.get(2).weight);
        assertEquals("C", graph.edges.get(2).start.value);
        assertEquals("A", graph.edges.get(2).end.value);
    }
}


