package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import graph_model.Vertex;

public class Kruskal_Helper {


    private Map<Vertex, Vertex> parentMap;


    public Kruskal_Helper(ArrayList<Vertex> arrayList) {
        parentMap = new HashMap<>();
        for (Vertex vertex : arrayList) {
            parentMap.put(vertex, vertex);
        }

    }

    public Vertex find(Vertex vertex) {
        Vertex parent = parentMap.get(vertex);
        if (parent == vertex) {
            return vertex;
        }
        // Compresión de ruta
        parentMap.put(vertex, find(parent));
        return parentMap.get(vertex);
    }

    public void union(Vertex src, Vertex dest) {
        Vertex srcParent = find(src);
        Vertex destParent = find(dest);
        parentMap.put(srcParent, destParent);

    }

}
