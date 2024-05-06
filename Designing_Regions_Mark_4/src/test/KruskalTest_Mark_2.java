package test;

import graph_model.Graph;
import graph_model.Vertex;
import model.Kruskal;
import model.Kruskal_Mark_2;

public class KruskalTest_Mark_2 {

    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph();

        Vertex v0 = graph.addVertex("0");
        Vertex v1 = graph.addVertex("1");
        Vertex v2 = graph.addVertex("2");
        Vertex v3 = graph.addVertex("3");
        Vertex v4 = graph.addVertex("4");
        Vertex v5 = graph.addVertex("5");
        Vertex v6 = graph.addVertex("6");
        Vertex v7 = graph.addVertex("7");
        Vertex v8 = graph.addVertex("8");
        Vertex v9 = graph.addVertex("9");


        graph.addEdge(v0, v1, 2);
        graph.addEdge(v0, v3, 3);
        graph.addEdge(v0, v6, 6);
        graph.addEdge(v1, v2, 1);
        graph.addEdge(v1, v6, 4);
        graph.addEdge(v3, v5, 9);
        graph.addEdge(v3, v9, 16);
        graph.addEdge(v2, v4, 11);
        graph.addEdge(v2, v8, 17);
        graph.addEdge(v4, v7, 7);
        graph.addEdge(v4, v8, 8);
        graph.addEdge(v4, v6, 12);
        graph.addEdge(v5, v7, 5);
        graph.addEdge(v5, v9, 10);
        graph.addEdge(v5, v6, 13);
        graph.addEdge(v6, v8, 18);
        graph.addEdge(v6, v9, 19);
        graph.addEdge(v7, v9, 14);
        graph.addEdge(v7, v8, 15);
        graph.addEdge(v5, v4, 20);



        // crea una instacia
        Kruskal_Mark_2 kruskal = new Kruskal_Mark_2();

        // Calculo el Kruskal
        Graph mst = kruskal.minimumSpanningTree(graph);


        System.out.println("Grafo Original");
        graph.print();

        // Imprime
        System.out.println("Kruskal:");
        mst.print();
        
    }
}

