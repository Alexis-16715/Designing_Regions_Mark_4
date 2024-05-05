package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

import graph_model.Edge;
import graph_model.Graph;
import graph_model.Vertex;

public class Kruskal_Mark_2 {

    private Graph kruskalGraph;

    

    private ArrayList<Edge> edges = new ArrayList<>();

    private ArrayList<Vertex> vertices = new ArrayList<>();

    private ArrayList<HashSet<Vertex>> subsets = new ArrayList<>();


    public Kruskal_Mark_2(){
        kruskalGraph = new Graph();
    }

    class WeightComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.getWeight() - e2.getWeight();
        }
    }   



    public Graph minimumSpanningTree(Graph graph) {
        edges = getAllEdges(graph);
        vertices.addAll(graph.getVertices());



        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Vertex> set = new HashSet<>();
            set.add(vertices.get(i));
            subsets.add(set);
        }


        Collections.sort(edges, new WeightComparator());

        for (Edge edge : edges){
            Vertex src = edge.getSrc();
            Vertex dest = edge.getDest();
            int weight = edge.getWeight();

            if (find(src) != find(dest)){
                kruskalGraph.addEdge(src, dest, weight);
                System.out.println(src.getData() + " --> " + dest.getData() + " == " + edge.getWeight());
                union(find(src), find(dest));
            }

        }

        return kruskalGraph;
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
        //adding all elements of subsetB in subsetA and deleting subsetB
        Iterator<Vertex> iter = bSet.iterator();
        while (iter.hasNext()) {
            Vertex b = iter.next();
            aSet.add(b);
        }
        subsets.remove(bSubset);

    }



    private int find(Vertex src) {
        for (int i = 0; i < subsets.size(); i++) {
            HashSet<Vertex> set = subsets.get(i);
            if (set.contains(src)) {
                return i;
            }
        }
        return -1;

    }

}
