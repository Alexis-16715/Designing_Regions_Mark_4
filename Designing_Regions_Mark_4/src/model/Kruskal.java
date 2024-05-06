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
        sortedEdges = new ArrayList<>();
        vertices = new HashSet<>();
    }

    private class weightComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.getWeight() - e2.getWeight();
        }
    }



    public Graph minimumSpanningTree(Graph graphOriginal) {
        kruskalGraph = new Graph();


        //Agrega todo las aristas en una lista y colecta todo los Vertice
        for (Vertex vertex : graphOriginal.getVertices()) {
            
            vertices.add(vertex);

            sortedEdges.addAll(vertex.getEdges());
        }

        // Collections.sort(sortedEdges, Comparator.comparingInt(Edge::getWeight));
        Collections.sort(sortedEdges, new weightComparator());
        
        //Inicializar conjunto disjunto para detección de ciclo

        Kruskal_Helper kruskalHelper = new Kruskal_Helper(new ArrayList<>(vertices));


        //Iterar sobre Aristas ordenados
        for (Edge edge : sortedEdges) {
            Vertex src = edge.getSrc();
            Vertex dest = edge.getDest();
            int weight = edge.getWeight();

            // Comprobar si incluir esta arista forma un ciclo
            if (kruskalHelper.find(src) != kruskalHelper.find(dest)){

                //Se agrega el caso arista con su peso
                kruskalGraph.addEdge(src, dest, weight);

                System.out.println(src.getData() + " --> " + dest.getData() + " == " + edge.getWeight());

                //Unir los conjuntos de inicio y destino
                kruskalHelper.union(src, dest);
            } else{
                kruskalGraph.removeEdge(src,dest);
            }
        }
        return kruskalGraph;

    }

}
