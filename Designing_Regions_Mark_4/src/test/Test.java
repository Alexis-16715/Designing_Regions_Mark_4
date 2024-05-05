package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import graph_model.Graph;
import graph_model.Vertex;
import graph_model.Edge;

public class Test {
    private Graph graph = new Graph();
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<HashSet<Vertex>> subsets = new ArrayList<>();

    public static void main(String args[]) {
        Test kruskal = new Test();
        kruskal.init();

    }

    private void init() {
        Graph graph = createGraph();
        vertices.addAll(graph.getVertices());
        edges = getAllEdges(graph);

        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Vertex> set = new HashSet<>();
            set.add(vertices.get(i));
            subsets.add(set);
        }

        System.out.println("The input Graph with vertices and following edges");
        graph.print();

        Collections.sort(edges, new WeightComparator());
        System.out.println("The MST after running Kruskal's Algorithm");
        System.out.println("Src --> Dst == Wt");

        for (Edge edge : edges) {
            Vertex srcNode = edge.getSrc();
            Vertex destNode = edge.getDest();

            if (find(srcNode) != find(destNode)) {
                System.out.println(srcNode.getData() + " --> " + destNode.getData() + " == " + edge.getWeight());
                union(find(srcNode), find(destNode));
            }
        }

        // System.out.println();
        // System.out.println();
        // graph.print();
    }

    private Graph createGraph() {
        Vertex v0 = graph.addVertex("0");
        Vertex v1 = graph.addVertex("1");
        Vertex v2 = graph.addVertex("2");
        Vertex v3 = graph.addVertex("3");
        Vertex v4 = graph.addVertex("4");
        Vertex v5 = graph.addVertex("5");
        Vertex v6 = graph.addVertex("6");
        Vertex v7 = graph.addVertex("7");
        Vertex v8 = graph.addVertex("8");
        Vertex v9 = graph.addVertex("9");

        graph.addEdge(v0, v1, 2);
        graph.addEdge(v0, v3, 3);
        graph.addEdge(v0, v6, 6);
        graph.addEdge(v1, v2, 1);
        graph.addEdge(v1, v6, 4);
        graph.addEdge(v3, v5, 9);
        graph.addEdge(v3, v9, 16);
        graph.addEdge(v2, v4, 11);
        graph.addEdge(v2, v8, 17);
        graph.addEdge(v4, v7, 7);
        graph.addEdge(v4, v8, 8);
        graph.addEdge(v4, v6, 12);
        graph.addEdge(v5, v7, 5);
        graph.addEdge(v5, v9, 10);
        graph.addEdge(v5, v6, 13);
        graph.addEdge(v6, v8, 18);
        graph.addEdge(v6, v9, 19);
        graph.addEdge(v7, v9, 14);
        graph.addEdge(v7, v8, 15);
        graph.addEdge(v5, v4, 20);

        return graph;
    }

    private ArrayList<Edge> getAllEdges(Graph graph) {
        ArrayList<Edge> allEdges = new ArrayList<>();
        for (Vertex vertex : graph.getVertices()) {
            allEdges.addAll(vertex.getEdges());
        }
        return allEdges;
    }

    private void union(int aSubset, int bSubset) {
        HashSet<Vertex> aSet = subsets.get(aSubset);
        HashSet<Vertex> bSet = subsets.get(bSubset);

        aSet.addAll(bSet);
        subsets.remove(bSubset);
    }

    private int find(Vertex node) {
        for (int i = 0; i < subsets.size(); i++) {
            HashSet<Vertex> set = subsets.get(i);
            if (set.contains(node)) {
                return i;
            }
        }
        return -1;
    }

    static class WeightComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.getWeight() - e2.getWeight();
        }
    }
}

