import org.example.graph.Graph;
import org.example.graph.Vertex;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @Test
    public void testAddVertical() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        Graph<String, Integer> graph = new Graph<>(vertices);

        graph.addVertical("C");

        assertEquals(3, graph.vertexs.size());
        assertEquals("C", graph.vertexs.get(2).value);
    }

    @Test
    public void testAddEdge() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        Graph<String, Integer> graph = new Graph<>(vertices);

        graph.addEdge(10, "A", "B");

        assertEquals(1, graph.edges.size());
        assertEquals(10, graph.edges.get(0).weight);
        assertEquals("A", graph.edges.get(0).start.value);
        assertEquals("B", graph.edges.get(0).end.value);
    }

    @Test
    public void testDeleteVertical() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2, vertex3));
        Graph<String, Integer> graph = new Graph<>(vertices);

        graph.deleteVertical("B");

        assertEquals(2, graph.vertexs.size());
        assertEquals("A", graph.vertexs.get(0).value);
        assertEquals("C", graph.vertexs.get(1).value);
        assertEquals(0, graph.edges.size());
    }

    @Test
    public void testDeleteEdge() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        Graph<String, Integer> graph = new Graph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.deleteEdge(10, "A", "B");

        assertEquals(0, graph.edges.size());
    }

    @Test
    public void testBellmanFordUsingAmatrix() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2, vertex3));
        Graph<String, Double> graph = new Graph<>(vertices);
        graph.addEdge(10.5, "A", "B");
        graph.addEdge(8.7, "B", "C");

        ArrayList<Vertex<String>> result = graph.bellManFordUsingAmatrix("A", 100.0);

        assertEquals("A", result.get(0).value);
        assertEquals("B", result.get(1).value);
        assertEquals("C", result.get(2).value);
    }

    @Test
    public void testBellmanFordUsingAlist() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2, vertex3));
        Graph<String, Double> graph = new Graph<>(vertices);
        graph.addEdge(10.5, "A", "B");
        graph.addEdge(8.7, "B", "C");

        ArrayList<Vertex<String>> result = graph.bellManFordUsingAlist("A", 100.0);

        assertEquals("A", result.get(0).value);
        assertEquals("B", result.get(1).value);
        assertEquals("C", result.get(2).value);
    }

    @Test
    public void testBellmanFordUsingImatrix() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2, vertex3));
        Graph<String, Double> graph = new Graph<>(vertices);
        graph.addEdge(10.5, "A", "B");
        graph.addEdge(8.7, "B", "C");

        ArrayList<Vertex<String>> result = graph.bellManFordUsingImatrix("A", 100.0);

        assertEquals("A", result.get(0).value);
        assertEquals("B", result.get(1).value);
        assertEquals("C", result.get(2).value);
    }

    @Test
    public void testSetVertex() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2));
        Graph<String, Integer> graph = new Graph<>(vertices);

        graph.setVertex("A", "C");

        assertEquals("C", graph.vertexs.get(0).value);
    }

    @Test
    public void testSetEdges() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2, vertex3));
        Graph<String, Integer> graph = new Graph<>(vertices);
        graph.addEdge(10, "A", "B");

        graph.setEdges(10, "A", "B", 20, "C", "B");

        assertEquals(1, graph.edges.size());
        assertEquals(20, graph.edges.get(0).weight);
        assertEquals("C", graph.edges.get(0).start.value);
        assertEquals("B", graph.edges.get(0).end.value);
    }

    @Test
    public void testMakeGraphFromAdjacencyMatrix() throws IOException {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        ArrayList<Vertex<String>> vertices = new ArrayList<>(Arrays.asList(vertex1, vertex2, vertex3));
        Graph<String, Double> graph = new Graph<>(vertices);

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


