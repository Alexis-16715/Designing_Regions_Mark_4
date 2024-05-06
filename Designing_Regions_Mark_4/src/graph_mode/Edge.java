package graph_mode;


public class Edge {
    private Vertex src;
    private Vertex dest;
    private int weight;

    public Edge(Vertex source,Vertex destination, int weight) {
        this.src = source;
        this.dest = destination;
        this.weight = weight;
    }

    public Vertex getSrc() {
        return src;
    }

    public Vertex getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }
}

