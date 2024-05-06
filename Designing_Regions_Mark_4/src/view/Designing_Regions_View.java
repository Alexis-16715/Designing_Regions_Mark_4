package view;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import province.Province_Argentina;

public class Designing_Regions_View extends JPanel {

    private int width;
    private int height;


    private Province_Argentina province;

    private Province_Argentina province2;

    private JPanel panelMap;

    private int widthPanel;
    private int heightPanel;


    private JMapViewer mapViewer;


    private Coordinate argentina;

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
        heightPanel = -5;
        panelMap.setBounds(widthPanel, heightPanel, width/2, height);
        panelMap.setBackground(Color.GREEN);


        generatedMap();
        panelMap.add(mapViewer);

        add(panelMap);
    }

    private void generatedMap() {
        mapViewer = new JMapViewer();
        mapViewer.setBorder(null);
        mapViewer.setZoomControlsVisible(false);
        mapViewer.setPreferredSize(new Dimension(width / 2, height));

        mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //Esta es la coordenadas de Argentina, ENJOY
        argentina = new Coordinate(-40.2, -63.616);
        mapViewer.setDisplayPosition(argentina, 5);
    }

    

}
