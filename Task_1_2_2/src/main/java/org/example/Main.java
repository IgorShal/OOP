package org.example;

import java.io.IOException;
import java.util.ArrayList;
import org.example.graph.AlGraph;
import org.example.graph.Vertex;

/**
 * ТЕСТИМ.
 */
public class Main {
    /**
     * ТЕСТИМ.
     */
    public static void main(String[] args) throws IOException {
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
        ArrayList<Vertex<String>> result = graph.bellManFord("C", 1000);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getValue());
        }


    }
}