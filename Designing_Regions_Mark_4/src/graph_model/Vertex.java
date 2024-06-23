package graph_model;

import java.util.Objects;

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

    @Override
	public int hashCode() {
		return Objects.hash(label);
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
            Vertex other = (Vertex) obj;
		return Objects.equals(label, other.label);
	}
}
