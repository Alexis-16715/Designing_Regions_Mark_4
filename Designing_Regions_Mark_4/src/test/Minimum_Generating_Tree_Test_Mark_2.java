package test;

import model.Minimum_Generating_Tree;
import org.junit.jupiter.api.Test;

import graph_model.Graph;
import graph_model.Vertex;

import static org.junit.jupiter.api.Assertions.*;

public class Minimum_Generating_Tree_Test_Mark_2 {

    @Test
    public void testMinimumSpanningTree() {
        Graph graphTest = createGraph();
        Minimum_Generating_Tree kruskal = new Minimum_Generating_Tree();
        

        Graph mst = kruskal.minimumSpanningTree(graphTest);

        assertNotNull(mst);

        //Verifcamos que la cantidad de Aristas de tanto del grafo original como el grafo kruskal sean diferentes, si son iguales entocnes habira algo mal
        assertNotEquals(countEdges(graphTest), countEdges(mst));

        // Verifcamos que el numero de Arista sea correcta
        assertEquals(9, countEdges(mst));
    }

    private int countEdges(Graph graph) {
        int totalEdges = 0;
        for (int i = 0; i < graph.getNumVertices(); i++) {
            totalEdges += graph.getAdjacencyList().get(i).size();
        }
        // Simple contador, "Nothing more, nothing else, just perfect"
        return totalEdges;
    }

    // Aqui se crea el grafo con las arista que tiene que tener
    private Graph createGraph() {
        Graph graphTest = new Graph();
        Vertex v0 = graphTest.addVertex("0");
        Vertex v1 = graphTest.addVertex("1");
        Vertex v2 = graphTest.addVertex("2");
        Vertex v3 = graphTest.addVertex("3");
        Vertex v4 = graphTest.addVertex("4");
        Vertex v5 = graphTest.addVertex("5");
        Vertex v6 = graphTest.addVertex("6");
        Vertex v7 = graphTest.addVertex("7");
        Vertex v8 = graphTest.addVertex("8");
        Vertex v9 = graphTest.addVertex("9");

        graphTest.addEdge(v0, v1, 2);
        graphTest.addEdge(v0, v3, 3);
        graphTest.addEdge(v0, v6, 6);
        graphTest.addEdge(v1, v2, 1);
        graphTest.addEdge(v1, v6, 4);
        graphTest.addEdge(v3, v5, 9);
        graphTest.addEdge(v3, v9, 16);
        graphTest.addEdge(v2, v4, 11);
        graphTest.addEdge(v2, v8, 17);
        graphTest.addEdge(v4, v7, 7);
        graphTest.addEdge(v4, v8, 8);
        graphTest.addEdge(v4, v6, 12);
        graphTest.addEdge(v5, v7, 5);
        graphTest.addEdge(v5, v9, 10);
        graphTest.addEdge(v5, v6, 13);
        graphTest.addEdge(v6, v8, 18);
        graphTest.addEdge(v6, v9, 19);
        graphTest.addEdge(v7, v9, 14);
        graphTest.addEdge(v7, v8, 15);
        graphTest.addEdge(v5, v4, 20);

        return graphTest;
    }
}

