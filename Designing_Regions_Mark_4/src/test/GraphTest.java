package test;

import org.junit.jupiter.api.Test;

import graph_model.Graph;
import graph_model.Vertex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class GraphTest {

    @Test
    public void testAddVertex() {
        Graph graph = new Graph();
        Vertex vertexA = graph.addVertex("A");

        assertNotNull(vertexA);
        assertEquals("A", vertexA.getLabel());

        // Intentar agregar un otra vez un vertice tendria que mandar un error
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex("A"));
    }

    @Test
    public void testAddEdge() {
        Graph graph = new Graph();
        Vertex vertexA = graph.addVertex("A");
        Vertex vertexB = graph.addVertex("B");

        // Intentar agregar una Arista con un peso menor o igual a cero tendria que mandar un error
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(vertexA, vertexB, -1));

        // Intentar agregar una Arista que ya fue agregada tendria que mandar un error
        graph.addEdge(vertexA, vertexB, 10);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(vertexA, vertexB, 5));

        // Intentar agregar una Arista que tiene la misma destinacion tendria que mandar un error
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(vertexA, vertexA, 5));
    }

    @Test
    public void testAddEdgeWithNonexistentVertices() {
        Graph graph = new Graph();
        Vertex vertexA = graph.addVertex("A");
        Vertex vertexB = new Vertex("B", 1); // Not added to the graph

        // Intentar agregar una Arista con Vertice que no existen en el grafo tendria que mandar un erro
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(vertexA, vertexB, 10));
    }

    @Test
    public void testGetAllTheEdgesInStrings() {
        Graph graph = new Graph();
        Vertex vertexA = graph.addVertex("A");
        Vertex vertexB = graph.addVertex("B");

        graph.addEdge(vertexA, vertexB, 10);
        Map<String, List<String>> edgesMap = graph.getAllTheEdgesInStrings();

        assertTrue(edgesMap.containsKey("A"));
        assertTrue(edgesMap.get("A").contains("B"));
    }

    @Test
    public void testGenerateAdjacencyMap() {
        Graph graph = new Graph();
        Vertex vertexA = graph.addVertex("A");
        Vertex vertexB = graph.addVertex("B");

        graph.addEdge(vertexA, vertexB, 10);
        List<String> adjacencyMap = graph.generateAdjacencyMap();

        assertTrue(adjacencyMap.contains("A --> B(10) "));
    }
}

