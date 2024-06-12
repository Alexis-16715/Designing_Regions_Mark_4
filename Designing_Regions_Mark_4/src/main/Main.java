package main;

import controller.Controller;
import graph_model.Graph;
import model.Minimum_Generating_Tree;
import view.Main_View;

public class Main {

    //Esta clase es solo encargada de hacer un main de todo
    public static void main(String[] args) {
        Main_View view = new Main_View();
        Graph graph = new Graph();
        Minimum_Generating_Tree Kruskal = new Minimum_Generating_Tree();
        @SuppressWarnings("unused")
        Controller controller = new Controller(view.getDesigningRegions(), view, graph, Kruskal);
    }

}
