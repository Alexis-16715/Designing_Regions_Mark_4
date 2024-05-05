package graph_model;

import java.util.ArrayList;

public class Vertex {

    private String data;

    private ArrayList<Edge> edges;

    public Vertex(String data){
        this.data = data;
        this.edges = new ArrayList<Edge>();

    }


    public void addEdge(Vertex dest, Integer weight){
        this.edges.add(new Edge(this, dest, weight));
        //Esto es si se quiere un grafo de doble sentido
        // this.edges.add(new Edge(dest, this,weight));
    }

    public void removeEdge(Vertex dest){
        edges.removeIf(edge -> edge.getDest().equals(dest));
    }

    public String getData() {
        return data;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void print() {
		String message = "";
		
		if (this.edges.size() == 0) {
			System.out.println(this.data + " -->");
			return;
		}
		
		for(int i = 0; i < this.edges.size(); i++) {
			if (i == 0) {
				message += this.edges.get(i).getSrc().data + " -->  ";
			}

			message += this.edges.get(i).getDest().data;

			message += " (" + this.edges.get(i).getWeight() + ")";

			if (i != this.edges.size() - 1) {
				message += ", ";
			}
		}
		System.out.println(message);
	}


}
