package graph_mode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Graph {
    private int numVertices;
    private List<Vertex> vertices;

    private List<List<Edge>> adjacencyList;

    public Graph() {
        this.numVertices = 0;
        this.vertices = new ArrayList<>();
        this.adjacencyList = new ArrayList<>();
    }

    public Vertex addVertex(String label) {
        Vertex vertex = new Vertex(label, numVertices);
        vertices.add(vertex);
        adjacencyList.add(new ArrayList<>());
        numVertices++;
        return vertex;
    }

    public void addEdge(Vertex source, Vertex destination, Integer weight) {
        int sourceIndex = source.getIndex();

        //Esto es para ver si todo esta inciado correctamente
        if (adjacencyList.size() <= sourceIndex) {
            adjacencyList.add(new ArrayList<>());
        }
        //Todo listo y se agrega el grafo
        adjacencyList.get(sourceIndex).add(new Edge(source,destination, weight));
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex (String nameProvince){
        for (Vertex list : vertices) {
            if(list.getLabel() == nameProvince){
                return list;
            }
        }
        return null;
    }

    public List<List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getNumVertices() {
        return numVertices;
    }
    public Map <String, List<String>> getAllTheEdgesInStrings(){
        Map <String, List<String>> mapArgentinaEdges = new HashMap<String, List<String>>();
        for (int i = 0; i < numVertices; i++) {
            List<String> edgesProvinces = new ArrayList<>();
            if (adjacencyList.get(i).size() != 0) {
                List<Edge> edges = adjacencyList.get(i);
                for (Edge edge : edges) {
                    edgesProvinces.add(vertices.get(edge.getSrc().getIndex()).getLabel());
                    edgesProvinces.add(vertices.get(edge.getDest().getIndex()).getLabel());
                }
                mapArgentinaEdges.put(vertices.get(i).getLabel(), edgesProvinces);
            }
        }
        return mapArgentinaEdges;
    }


    //Esto imprime el grafo de una manera ordenada, solo muestra los vertice con edges, si uno quedo solo no se imprime
    public void print() {
        for (int i = 0; i < numVertices; i++) {
            if(adjacencyList.get(i).size() != 0){
                System.out.print(vertices.get(i).getLabel() + " --> ");
                List<Edge> edges = adjacencyList.get(i);
                for (Edge edge : edges) {
                    System.out.print(vertices.get(edge.getDest().getIndex()).getLabel() + "(" + edge.getWeight() + ") ");
                }
                System.out.println();
            }
        }
    }
}
