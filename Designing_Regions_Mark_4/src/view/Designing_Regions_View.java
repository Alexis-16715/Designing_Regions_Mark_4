package view;

import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import province.Province_Argentina;

public class Designing_Regions_View extends JPanel {

    private int width;
    private int height;


    private Province_Argentina province;

    private JPanel panelMap;

    private int widthPanel;
    private int heightPanel;


    private JMapViewer map;

    //Esta es la clase en la que se genera los botones y mapa

    public Designing_Regions_View(int width, int height){
        this.width = width;
        this.height = height;

        initialize();
    }

    private void initialize() {
        setLayout(null);
        setPreferredSize(new Dimension (width, height));
        setBackground(Color.BLACK);

        generatedMapPanel();
    }

    private void generatedMapPanel() {
        panelMap = new JPanel();
        widthPanel = 50;
        heightPanel = 20;
        panelMap.setBounds(widthPanel, heightPanel, width/2, height);
        panelMap.setBackground(Color.GREEN);


        generatedMap();
        // panelMap.add(map);



        add(panelMap);
    }

    private void generatedMap() {
        map = new JMapViewer();
        map.setBorder(null);
        map.setZoomControlsVisible(false);
        map.setPreferredSize(new Dimension(width / 2, height));
    }

    

}
