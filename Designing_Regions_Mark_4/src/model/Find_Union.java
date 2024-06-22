package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import graph_model.Vertex;

public class Find_Union {


    private Map<Vertex, Vertex> parentMap;


    public Find_Union(ArrayList<Vertex> arrayList) {
        parentMap = new HashMap<>();
        for (Vertex vertex : arrayList) {
            parentMap.put(vertex, vertex);
        }

    }

    public Vertex find(Vertex vertex) {
        if (parentMap.get(vertex) != vertex) {
            parentMap.put(vertex, find(parentMap.get(vertex)));
        }
        return parentMap.get(vertex);
    }

    public void union(Vertex src, Vertex dest) {
        Vertex srcParent = find(src);
        Vertex destParent = find(dest);
        if (!srcParent.equals(destParent)) {
            parentMap.put(srcParent, destParent); // Ahora u está bajo el conjunto de v
        }

    }

    public boolean connected(Vertex vertex1, Vertex vertex2) {
        return find(vertex1) == find(vertex2);
    }

}
