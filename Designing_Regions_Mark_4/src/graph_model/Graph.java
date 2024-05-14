package graph_model;

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
        for (Vertex existingVertex : vertices) {
            if (existingVertex.getLabel().equals(label)) {
                throw new IllegalArgumentException("Vertex with label '" + label + "' already exists");
            }
        }
        Vertex vertex = new Vertex(label, numVertices);
        vertices.add(vertex);
        adjacencyList.add(new ArrayList<>());
        numVertices++;
        return vertex;
    }

    public void addEdge(Vertex source, Vertex destination, Integer weight) {
        int sourceIndex = source.getIndex();

        if(weight <= 0){
            throw new IllegalArgumentException("La gráfica no puede ser igual o inferior a 0");
        }

        for (Edge edge : adjacencyList.get(sourceIndex)) {
            if (edge.getDest().equals(destination)) {
                // Edge already exists, no need to add it again
                throw new IllegalArgumentException("Este Arista ya fue agregado al gráfico.");
            }
        }

        if(source.equals(destination)){
            throw new IllegalArgumentException("El gráfico no aceptará que el Origen y el Destino sean iguales");
        }

        if (!vertices.contains(source) || !vertices.contains(destination)) {
            throw new IllegalArgumentException("El vertice de origen o destino no existe en el gráfico");
        }

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

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
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

    // public Map<String, List<String>> getAllTheEdgesInStrings() {
    //     Map<String, List<String>> mapArgentinaEdges = new HashMap<>();
        
    //     for (int i = 0; i < numVertices; i++) {
    //         List<String> edgesProvinces = new ArrayList<>();
            
    //         if (!adjacencyList.get(i).isEmpty()) {
    //             List<Edge> edges = adjacencyList.get(i);
                
    //             for (Edge edge : edges) {
    //                 int srcIndex = edge.getSrc().getIndex();
    //                 int destIndex = edge.getDest().getIndex();
                    
    //                 String srcLabel = vertices.get(srcIndex).getLabel();
    //                 String destLabel = vertices.get(destIndex).getLabel();
                    
    //                 // Arega la destinacion a la lista
    //                 edgesProvinces.add(srcLabel);
    //                 edgesProvinces.add(destLabel);
    //             }
                
    //             // Agrega las arista al mapa
    //             mapArgentinaEdges.put(vertices.get(i).getLabel(), edgesProvinces);
    //         }
    //     }
    //     return mapArgentinaEdges;
    // }

    public List<String> getAllTheEdgesInStrings() {
        List<String> representation = new ArrayList<>();
    
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyList.get(i).size() != 0) {
                List<Edge> edges = adjacencyList.get(i);
                for (Edge edge : edges) {
                    representation.add(vertices.get(edge.getDest().getIndex()).getLabel());
                    representation.add(vertices.get(edge.getSrc().getIndex()).getLabel());
                }
            }
        }
        return representation;
    }

    public List<String> generateAdjacencyMap() {
        List<String> representation = new ArrayList<>();
    
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyList.get(i).size() != 0) {
                StringBuilder line = new StringBuilder();
                line.append(vertices.get(i).getLabel()).append(" --> ");
                List<Edge> edges = adjacencyList.get(i);
                for (Edge edge : edges) {
                    line.append(vertices.get(edge.getDest().getIndex()).getLabel())
                        .append("(").append(edge.getWeight()).append(") ");
                }
                representation.add(line.toString());
            }
        }
        return representation;
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