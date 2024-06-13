package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import graph_model.Edge;
import graph_model.Graph;
import graph_model.Vertex;



public class Minimum_Generating_Tree {

    //Esto es el grafo
    private Graph kruskalGraph;
    
    //Esto es Artistas or Edges
    private ArrayList<Edge> sortedEdges;

    //Esto es el Vertex o Vertice
    private List<Vertex> vertices;




    public Minimum_Generating_Tree (){
        sortedEdges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    //Esto es para comparar el peso de las aristas o Edges, se podria implementar en edges pero es mejor tenerlo que para referencia
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
            
            //Esto es para agregar al grafo nuevo todo los Vertice que nesesita
            kruskalGraph.addVertex(vertex.getLabel());
            
            vertices.add(vertex);


            sortedEdges.addAll(graphOriginal.getAdjacencyList().get(vertex.getIndex()));

            List<Edge> edges = graphOriginal.getAdjacencyList().get(vertex.getIndex());

            //Checkea si la liesta de Aristas (Edges) no esta vacia o Null
            if (edges != null) { 
                sortedEdges.addAll(edges);
            }
        }
        kruskalGraph.setVertices(vertices);
        Collections.sort(sortedEdges, new weightComparator());
        
       //Inicializar conjunto disjunto para detección de ciclo

        Find_Union findUnion = new Find_Union(new ArrayList<>(vertices));


        //Iterar sobre Aristas ordenados
        for (Edge edge : sortedEdges) {
            Vertex src = edge.getSrc();;
            Vertex dest = edge.getDest();
            int weight = edge.getWeight();

            // Comprobar si incluir esta arista forma un ciclo
            if (findUnion.find(src) != findUnion.find(dest)){

                //Se agrega el caso arista con su peso
                kruskalGraph.addEdge(src, dest, weight);

                // System.out.println(src.getLabel() + " --> " + dest.getLabel() + " == " + edge.getWeight());

                //Unir los conjuntos de inicio y destino
                findUnion.union(src, dest);
            }
        }

        //Retornamos el grafo todo armado con su arista corresponientes 
        return kruskalGraph;

    }

}