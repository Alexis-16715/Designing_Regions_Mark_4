package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import graph_model.Edge;
import graph_model.Graph;
import graph_model.Vertex;

public class Kruskal {

    //Esto es el grafo
    private Graph kruskalGraph;
    
    //Esto es Artistas or Edges
    private ArrayList<Edge> sortedEdges;

    //Esto es el Vertex o Vertice
    private Set<Vertex> vertices;




    public Kruskal (){
        kruskalGraph = new Graph();
        sortedEdges = new ArrayList<>();
        vertices = new HashSet<>();

    }



    public Graph minimumSpanningTree(Graph graph) {


        //Agrega todo las aristas en una lista y colecta todo los Vertice
        for (Vertex vertex : graph.getVertices()) {
            
            vertices.add(vertex);
            sortedEdges.addAll(vertex.getEdges());
        }

        Collections.sort(sortedEdges, Comparator.comparingInt(Edge::getWeight));
        
        //Inicializar conjunto disjunto para detección de ciclo
        // Kruskal_Helper kruskalHelper = new Kruskal_Helper(graph.getVertices());

        Kruskal_Helper kruskalHelper = new Kruskal_Helper(new ArrayList<>(vertices));


        //Iterar sobre Aristas ordenados
        for (Edge edge : sortedEdges) {
            Vertex src = edge.getSrc();
            Vertex dest = edge.getDest();
            int weight = edge.getWeight();

            // Comprobar si incluir esta arista forma un ciclo
            if (kruskalHelper.find(src) != kruskalHelper.find(dest)){



                kruskalGraph.addEdge(src, dest, weight);
                System.out.println(src.getData() + " --> " + dest.getData() + " == " + edge.getWeight());

                //Unir los conjuntos de inicio y destino
                kruskalHelper.union(src, dest);
            }


        }
        return kruskalGraph;

    }

}
