package controller;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import graph_model.Edge;
import graph_model.Graph;
import model.Minimum_Generating_Tree;
import view.Designing_Regions_View;
import view.Main_View;

public class Controller {

    @SuppressWarnings("unused")
    private Main_View view;

    private Designing_Regions_View designingRegionsView;

    private Graph graph;
    private Minimum_Generating_Tree kruskal;

    private JButton bottonAddProvince;

    List<Edge> arbolGeneradorMinimo;

    private List<JCheckBox> checkBoxList;

    private List<String> ProvinceAddArgentina;

    private JButton bottonAddProvinceConnectionGraph;
    private JButton bottonKruskal;

    private List<JComboBox<String>> listComboBoxProvince;
    private List<JComboBox<Integer>> listComboBoxWeight;

    private List<String> ListArgentinaEdges;




    public Controller (Designing_Regions_View designingRegionsView, Main_View view,  Graph graph, Minimum_Generating_Tree kruskal){
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
            for(int i = 0; i < listComboBoxWeight.size(); i++){
                if (listComboBoxProvince.get(i).getSelectedItem().toString() != "No seleccionado"){
                    String src = ProvinceAddArgentina.get(i);
                    String dest = listComboBoxProvince.get(i).getSelectedItem().toString();
                    Integer weight = listComboBoxWeight.get(i).getSelectedIndex()+1;
                    graph.addEdge(graph.getVertex(src), graph.getVertex(dest), weight);
                }
            }

            ListArgentinaEdges = graph.getAllTheEdgesInStrings();
            bottonKruskal.setEnabled(true);
            designingRegionsView.createMapPoligon(ListArgentinaEdges);
        });

        bottonKruskal.addActionListener(e -> {
            arbolGeneradorMinimo = kruskal.minimumSpanningTree(graph);
            ListArgentinaEdges = new ArrayList<>();
            if(arbolGeneradorMinimo !=null ){
                List<String> listaDeProvinciaArgentina = new ArrayList<>();

                for (Edge edge : arbolGeneradorMinimo) {
                    listaDeProvinciaArgentina.add(edge.getSrc().getLabel() + " --> " + edge.getDest().getLabel() +  " ("+ edge.getWeight() + ") ");
                    ListArgentinaEdges.add(edge.getSrc().getLabel());
                    ListArgentinaEdges.add(edge.getDest().getLabel());
                }

                designingRegionsView.createStringOfTheGraph(listaDeProvinciaArgentina, graph.generateAdjacencyMap());
                designingRegionsView.createMapPoligon(ListArgentinaEdges);

            } else{
                JOptionPane.showMessageDialog(null, "El grafo no es Conexo: ", "Recuerde que el grafo tiene que estar conectado", JOptionPane.ERROR_MESSAGE);
            }
        });



    }

}
