package controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import graph_model.Edge;
import graph_model.Graph;
import model.Minimum_Generating_Tree;
import province.Coordinates;
import province.Province_Argentina;
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

    @SuppressWarnings("unused")
    private List<String> ListArgentinaEdges;


    private Map<String, Coordinates> provinceNameLocations;


    private JButton bottonDivideCountry;


    private JComboBox<Integer> comboBoxDivideCountry;




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

            List<Edge> mst = graph.getAllEdges();

            ListArgentinaEdges = graph.getAllTheEdgesInStrings();
            bottonKruskal.setEnabled(true);
            designingRegionsView.removePreviewsMappolygons();
            provinceNameLocations = new Province_Argentina().getLocations();
            for (Edge edge : mst) {
                Coordinates src = provinceNameLocations.get(edge.getSrc().getLabel());
                Coordinates dest = provinceNameLocations.get(edge.getDest().getLabel());

                Coordinate srcCoordiante = new Coordinate(src.getLatitude(), src.getLongitude());
                Coordinate destCoordiante = new Coordinate(dest.getLatitude(), dest.getLongitude());

                List<Coordinate> route = Arrays.asList(srcCoordiante, destCoordiante, destCoordiante, srcCoordiante);
                designingRegionsView.createMapPoligonMark2(route);
            }

        });

        bottonKruskal.addActionListener(e -> {
            provinceNameLocations = designingRegionsView.getProvinceNameLocations();
            
            arbolGeneradorMinimo = kruskal.minimumSpanningTree(graph);
            provinceNameLocations = new Province_Argentina().getLocations();
            if(arbolGeneradorMinimo !=null ){
                designingRegionsView.removeCheckBoxElements();
                designingRegionsView.createCheckboxDivideCountry(arbolGeneradorMinimo.size());
                bottonKruskal.setEnabled(false);
                bottonAddProvinceConnectionGraph.setEnabled(false);
                attachListenersButtonDivdeCountry();
            } else{
                JOptionPane.showMessageDialog(null, "Recuerde que el grafo tiene que estar conectado", "El grafo no es Conexo: ", JOptionPane.ERROR_MESSAGE);
            }
            
        });

    }

    private void attachListenersButtonDivdeCountry(){
        bottonDivideCountry = designingRegionsView.getBottonDivideCountry();
        comboBoxDivideCountry = designingRegionsView.getComboBoxDivideCountry();

        bottonDivideCountry.addActionListener(e -> {
            designingRegionsView.removePreviewsMappolygons();
            List<String> listaDeProvinciaArgentina = new ArrayList<>();
            if(comboBoxDivideCountry.getSelectedItem().hashCode() > 1){
                arbolGeneradorMinimo = graph.deletedHeavieEdge(arbolGeneradorMinimo, comboBoxDivideCountry.getSelectedItem().hashCode() - 1);
            }
            for (Edge edge : arbolGeneradorMinimo) {
                listaDeProvinciaArgentina.add(edge.getSrc().getLabel() + " --> " + edge.getDest().getLabel() +  " ("+ edge.getWeight() + ") ");

                Coordinates src = provinceNameLocations.get(edge.getSrc().getLabel());
                Coordinates dest = provinceNameLocations.get(edge.getDest().getLabel());

                Coordinate srcCoordiante = new Coordinate(src.getLatitude(), src.getLongitude());
                Coordinate destCoordiante = new Coordinate(dest.getLatitude(), dest.getLongitude());


                List<Coordinate> route = Arrays.asList(srcCoordiante, destCoordiante, destCoordiante, srcCoordiante);
                designingRegionsView.createMapPoligonMark2(route);

                }

                designingRegionsView.createStringOfTheGraph(listaDeProvinciaArgentina, graph.generateAdjacencyMap());
                bottonDivideCountry.setEnabled(false);
                comboBoxDivideCountry.setEnabled(false);
        });
        
    }


}
