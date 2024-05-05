package graph_model;

import java.util.ArrayList;

public class Graph {


    private ArrayList<Vertex> vertices;

    
    public Graph(){
        this.vertices = new ArrayList<Vertex>();
    }

    public Vertex addVertex(String data){
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge(Vertex initialVertex, Vertex finalVertex, Integer weight){
        initialVertex.addEdge(finalVertex, weight);
        finalVertex.addEdge(initialVertex, weight);
    }

    public void removeEdge(Vertex initialVertex, Vertex finalVerte){
        initialVertex.removeEdge(finalVerte);
        finalVerte.removeEdge(initialVertex);
    }

    public void removeVertex(Vertex vertex){
        vertices.remove(vertex);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void print(){
        for(Vertex v: this.vertices){
            v.print();
        }
    }

}