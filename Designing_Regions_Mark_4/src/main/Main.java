package main;

import controller.Controller;
import graph_model.Graph;
import model.MinimumGeneratingTree;
import view.MainView;

public class Main {

    //Esta clase es solo encargada de hacer un main de todo
    public static void main(String[] args) {
        MainView view = new MainView();
        Graph graph = new Graph();
        MinimumGeneratingTree Kruskal = new MinimumGeneratingTree();
        @SuppressWarnings("unused")
        Controller controller = new Controller(view.getDesigningRegions(), view, graph, Kruskal);
    }

}
