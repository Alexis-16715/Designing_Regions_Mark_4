package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import graph_mode.Graph;
import model.Kruskal_Mark_2;
import view.Designing_Regions_View;
import view.Main_View;

public class Controller {

    private Main_View view;

    private Designing_Regions_View designingRegionsView;

    private Graph graph;
    private Kruskal_Mark_2 kruskal;

    private JButton bottonAddProvince;

    private Graph krukGraph;

    private List<JCheckBox> checkBoxList;

    private List<String> ProvinceAddArgentina;

    private Map<String,List<String>> test;

    private JButton bottonAddProvinceConnectionGraph;
    private JButton bottonKruskal;
    private JButton bottonReset;

    private List<JComboBox<String>> listComboBoxProvince;
    private List<JComboBox<Integer>> listComboBoxWeight;

    private Map <String, List<String>> mapArgentinaEdges;


    private List<String> ProvinceToAddToMapViewer;

    public Controller (Designing_Regions_View designingRegionsView, Main_View view,  Graph graph, Kruskal_Mark_2 kruskal){
        this.view = view;
        this.designingRegionsView = designingRegionsView;
        this.graph = graph;
        this.kruskal = kruskal;
        attachListenersButton();
    }

    private void attachListenersButton() {
        bottonAddProvince = designingRegionsView.getBottonAddProvince();

        checkBoxList = designingRegionsView.getCheckBoxList();
        bottonAddProvince.addActionListener(e -> {
            ProvinceAddArgentina = new ArrayList<>();
            // Se encarga del evento del botton
            for (JCheckBox checkBox : checkBoxList) {
                if (checkBox.isSelected()) {
                    graph.addVertex(checkBox.getText());
                    ProvinceAddArgentina.add(checkBox.getText());
                }
                checkBox.setEnabled(false);
                bottonAddProvince.setEnabled(false);
            }
            //Esto le da informacion para rellenar
            designingRegionsView.generProvinceEdge(ProvinceAddArgentina);
            attachListenersButtonGraph();
        });
        


    }

    private void attachListenersButtonGraph() {
        listComboBoxProvince = designingRegionsView.getListComboBoxProvince();
        listComboBoxWeight = designingRegionsView.getListComboBoxWeight();


        bottonKruskal = designingRegionsView.getBottonKruskal();

        bottonAddProvinceConnectionGraph = designingRegionsView.getBottonAddProvinceConnectionGraph();
        bottonAddProvinceConnectionGraph.addActionListener(e -> {
            ProvinceToAddToMapViewer = new ArrayList<>();
            for(int i = 0; i < listComboBoxWeight.size(); i++){
                if (listComboBoxProvince.get(i).getSelectedItem().toString() != "No selected"){
                    String src = ProvinceAddArgentina.get(i);
                    String dest = listComboBoxProvince.get(i).getSelectedItem().toString();
                    Integer weight = listComboBoxWeight.get(i).getSelectedIndex()+1;
                    graph.addEdge(graph.getVertex(src), graph.getVertex(dest), weight);
                    // ProvinceToAddToMapViewer.add(src);
                    // ProvinceToAddToMapViewer.add(dest);
                }
            }

            mapArgentinaEdges = graph.getAllTheEdgesInStrings();
            for (Map.Entry<String, List<String>> entry : mapArgentinaEdges.entrySet()) {
                List<String> values = entry.getValue();
                for (String value : values) {
                    ProvinceToAddToMapViewer.add(value);
                }
            }

            bottonKruskal.setEnabled(true);
            designingRegionsView.createMapPoligon(ProvinceToAddToMapViewer);
            graph.print();
        });

        bottonKruskal.addActionListener(e -> {
            krukGraph = kruskal.minimumSpanningTree(graph);
            System.err.println("---------------------");
            krukGraph.print();
            ProvinceToAddToMapViewer = new ArrayList<>();
            mapArgentinaEdges = krukGraph.getAllTheEdgesInStrings();
            for (Map.Entry<String, List<String>> entry : mapArgentinaEdges.entrySet()) {
                List<String> values = entry.getValue();
                for (String value : values) {
                    ProvinceToAddToMapViewer.add(value);
                }
            }

            
            designingRegionsView.createStringOfTheGraph(krukGraph.generateAdjacencyMap(), graph.generateAdjacencyMap());
            designingRegionsView.createMapPoligon(ProvinceToAddToMapViewer);
        });


        bottonReset = designingRegionsView.getBottonReset();

        bottonReset.addActionListener(e -> {
        });



    }

}
