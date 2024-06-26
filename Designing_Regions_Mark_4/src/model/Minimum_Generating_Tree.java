package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import graph_model.Edge;
import graph_model.Graph;
import graph_model.Vertex;



public class Minimum_Generating_Tree {
    
    //Esto es Artistas or Edges
    private ArrayList<Edge> sortedEdges;





    public Minimum_Generating_Tree (){
    }



    public List<Edge> minimumSpanningTree(Graph graphOriginal) {
        sortedEdges = new ArrayList<>();
        List<Edge> mst = new ArrayList<>();


        //Agrega todo las aristas en una lista y colecta todo los Vertice
        for (List<Edge> aristas : graphOriginal.getAdjacencyList().values()) {
            sortedEdges.addAll(aristas);
        }
        Collections.sort(sortedEdges, Comparator.comparingInt(Edge::getWeight));
        int numEdges = 0;
        
       //Inicializar conjunto disjunto para detección de ciclo
       Set<Vertex> allVertices = graphOriginal.getAdjacencyList().keySet();


        Find_Union findUnion = new Find_Union(allVertices);


        //Iterar sobre Aristas ordenados
        for (Edge edge : sortedEdges) {
            Vertex src = edge.getSrc();;
            Vertex dest = edge.getDest();
            // int weight = edge.getWeight();

            // Comprobar si incluir esta arista forma un ciclo
            if (!findUnion.connected(src, dest)){

                //Se agrega el caso arista con su peso
                mst.add(edge);

                // System.out.println(src.getLabel() + " --> " + dest.getLabel() + " == " + edge.getWeight());
                numEdges++;

                //Unir los conjuntos de inicio y destino
                findUnion.union(src, dest);

            }
        }

        // Verificar si el Arbol tiene exactamente V-1 aristas
        // if (graphOriginal.getNumVertices() - 1 != numAristas) {
        //     return null;
        // }

        if (graphOriginal.getNumVertices() - 1 > numEdges) {
            return null;
        }

        //Retornamos el grafo todo armado con su arista corresponientes 
        return mst;

    }

}