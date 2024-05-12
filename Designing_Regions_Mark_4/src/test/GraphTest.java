package test;

import org.junit.jupiter.api.Test;

import graph_mode.Graph;
import graph_mode.Vertex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class GraphTest {

    @Test
    public void testAddVertex() {
        Graph graph = new Graph();
        Vertex vertex = graph.addVertex("A");
        
        assertNotNull(vertex);
        assertEquals("A", vertex.getLabel());
    }

    @Test
    public void testAddEdge() {
        Graph graph = new Graph();
        Vertex vertexA = graph.addVertex("A");
        Vertex vertexB = graph.addVertex("B");
        
        graph.addEdge(vertexA, vertexB, 10);
        assertEquals(1, graph.getAdjacencyList().get(vertexA.getIndex()).size());
        assertEquals("B", graph.getAdjacencyList().get(vertexA.getIndex()).get(0).getDest().getLabel());
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
}

