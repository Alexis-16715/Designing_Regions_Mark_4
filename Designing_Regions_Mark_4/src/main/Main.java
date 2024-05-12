package main;

import controller.Controller;
import graph_mode.Graph;
import model.Kruskal_Mark_2;
import view.Main_View;

public class Main {

    //Esta clase es solo encargada de hacer un main de todo
    public static void main(String[] args) {
        Main_View view = new Main_View();
        Graph graph = new Graph();
        Kruskal_Mark_2 Kruskal = new Kruskal_Mark_2();
        @SuppressWarnings("unused")
        Controller controller = new Controller(view.getDesigningRegions(), view, graph, Kruskal);
    }

}
