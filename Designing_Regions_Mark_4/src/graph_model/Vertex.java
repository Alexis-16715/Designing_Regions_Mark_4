package graph_model;

public class Vertex {
    private String label;
    private int index;

    public Vertex(String label, int index) {
        this.label = label;
        this.index = index;
    }

    public String getLabel() {
        return label;
    }

    public int getIndex() {
        return index;
    }
}
