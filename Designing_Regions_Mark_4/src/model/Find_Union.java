package model;

import java.util.List;

import graph_model.Vertex;

public class Find_Union {
    private int[] parent; 
    private int[] rank;  

    public Find_Union(List<Vertex> vertices) {
        parent = new int[vertices.size()];
        rank = new int[vertices.size()];

        // Inicialización: Cada vértice es su propio padre al principio, y rango inicial 0
        for (int i = 0; i < vertices.size(); i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            int result = find(parent[i]);
            parent[i] = result; // Compresión de ruta
            return result;
        }
    }

    public void union(int i, int j) {
        int irep = find(i);
        int jrep = find(j);

        if (irep == jrep) {
            return;
        }

        if (rank[irep] < rank[jrep]) {
            parent[irep] = jrep;
        } else if (rank[jrep] < rank[irep]) {
            parent[jrep] = irep;
        } else {
            parent[jrep] = irep;
            rank[irep]++;
        }
    }

    public boolean connected(Vertex vertex1, Vertex vertex2) {
        return find(vertex1.getIndex()) == find(vertex2.getIndex());
    }
}
