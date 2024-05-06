package test;

import graph_mode.Graph;
import graph_mode.Vertex;
import model.Kruskal_Mark_2;


public class KrusklaTest_Mark_2 {
    private Graph graphTest;

    private Graph mst;

    public KrusklaTest_Mark_2(){
        makeTheGraph();
        printTheGraph();
    }
    
    public void makeTheGraph(){
        graphTest = new Graph();
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
    }


    public void printTheGraph(){
        System.out.println("Grafo Original");
        graphTest.print();

        // Crea una instacia para la clase de Algoritmo
        Kruskal_Mark_2 kruskal = new Kruskal_Mark_2();

        System.out.println("--------------");

        mst= new Graph();

        // Calcula el algortimo del Arbol
        mst = kruskal.minimumSpanningTree(graphTest);

        System.out.println("--------------");

        // Imprimi el resultado
        System.out.println("Kruskal:");
        mst.print();
    }

    public static void main(String[] args) {
        KrusklaTest_Mark_2 test = new KrusklaTest_Mark_2();

    }
}

