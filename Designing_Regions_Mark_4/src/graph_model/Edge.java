package graph_model;

import java.util.Comparator;

public class Edge implements Comparator<Edge> {

    private Vertex src;

    private Vertex dest;

    private Integer weight;

    public Edge(Vertex src,Vertex dest, Integer weight){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }



    public Vertex getSrc() {
        return src;
    }

    public Vertex getDest() {
        return dest;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compare(Edge e1, Edge e2) {
        return e1.weight - e2.weight;
    }

}
