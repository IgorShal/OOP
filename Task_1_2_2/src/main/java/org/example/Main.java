package org.example;

import java.io.IOException;
import java.util.ArrayList;

import org.example.graph.Graph;
import org.example.graph.Vertex;


public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Vertex<String, Double>> vertexes = new ArrayList<>();
        vertexes.add(new Vertex<String, Double>("A"));
        vertexes.add(new Vertex<String, Double>("B"));
        vertexes.add(new Vertex<String, Double>("C"));
        Graph<String, Double> graph = new Graph<>(vertexes);
        graph.makeGraphFromAdjacencyMatrix("/1.txt");
        graph.addEdge(5.0, "A", "A");
        graph.addVertical("5");
        graph.addEdge(5.0, "5", "A");
        graph.deleteVertical("B");
        graph.deleteEdge(1.0, "A", "A");
        graph.setVertex("A", "a");
        graph.setEdges(1.0, "C", "a", 1.0, "5", "C");
/*        for (int i = 0;i<graph.edges.size();i++){
            System.out.println(graph.edges.get(i).start.value + " " + graph.edges.get(i).end.value + " "
                    +  graph.edges.get(i).weight);
        }*/
        graph.printAdjacencyMatrix();
        graph.printAdjacencyList();
        graph.printIncidenceList();

    }
}