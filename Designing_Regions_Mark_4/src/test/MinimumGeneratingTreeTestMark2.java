package test;

import model.MinimumGeneratingTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import graph_model.Edge;
import graph_model.Graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MinimumGeneratingTreeTestMark2 {
    private Graph graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph();


        // Agrega a los Vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Agrega a los Aristas
        graph.addEdge(graph.getVertex("A"), graph.getVertex("B"), 1);
        graph.addEdge(graph.getVertex("A"), graph.getVertex("C"), 3);
        graph.addEdge(graph.getVertex("A"), graph.getVertex("D"), 4);
        graph.addEdge(graph.getVertex("B"), graph.getVertex("D"), 2);
        graph.addEdge(graph.getVertex("B"), graph.getVertex("E"), 5);
        graph.addEdge(graph.getVertex("C"), graph.getVertex("D"), 6);
        graph.addEdge(graph.getVertex("D"), graph.getVertex("E"), 7);
    }


    @Test
    public void testMinimumSpanningTree() {
        MinimumGeneratingTree mstFinder = new MinimumGeneratingTree();
        List<Edge> mst = mstFinder.minimumSpanningTree(graph);

        assertNotNull(mst, "Minimum Spanning Tree should not be null");
        assertEquals(4, mst.size(), "Tiene que tener al menso 4 Aristas");

        int totalWeight = mst.stream().mapToInt(Edge::getWeight).sum();
        assertEquals(11, totalWeight, "El peso total tendria que ser 11");
    }

    @Test
    public void testDisconnectedGraph() {
        Graph disconnectedGraph = new Graph();
        disconnectedGraph.addVertex("A");
        disconnectedGraph.addVertex("B");
        disconnectedGraph.addVertex("C");

        disconnectedGraph.addEdge(disconnectedGraph.getVertex("A"), disconnectedGraph.getVertex("B"), 1);

        MinimumGeneratingTree mstFinder = new MinimumGeneratingTree();
        List<Edge> mst = mstFinder.minimumSpanningTree(disconnectedGraph);

        assertNull(mst, "MST should be null for a disconnected graph");
    }
}

