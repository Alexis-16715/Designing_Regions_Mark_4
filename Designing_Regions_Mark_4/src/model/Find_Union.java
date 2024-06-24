package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import graph_model.Vertex;


public class Find_Union {
    private Map<Vertex, Vertex> parent;
    private Map<Vertex, Integer> rank;

    public Find_Union(Set<Vertex> vertices) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (Vertex vertex : vertices) {
            parent.put(vertex, vertex);
            rank.put(vertex, 0);
        }
    }

    public Vertex find(Vertex vertex) {
        if (parent.get(vertex) != vertex) {
            parent.put(vertex, find(parent.get(vertex))); // Path compression
        }
        return parent.get(vertex);
    }

    public void union(Vertex root1, Vertex root2) {
        if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
        }
    }

    public boolean connected(Vertex vertex1, Vertex vertex2) {
        Vertex fatherSrc = find(vertex1);
        Vertex fatherDest = find(vertex2);

        // Verifica si los padres son iguales
        return fatherSrc.equals(fatherDest);
    }
}
