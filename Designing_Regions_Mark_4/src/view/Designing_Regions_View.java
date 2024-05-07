package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import province.Coordinates;
import province.Province_Argentina;

public class Designing_Regions_View extends JPanel {

    private int width;
    private int height;


    private Province_Argentina province;


    private JPanel panelMap;

    private JPanel title;

    private int widthPanel;
    private int heightPanel;


    private JMapViewer mapViewer;


    private Coordinate argentina;

    private JTextField JTextTitulo;


    private JPanel panelCheckBox;
    private JButton bottonAddProvince;

    //Esta es la clase en la que se genera los botones y mapa

    public Designing_Regions_View(int width, int height){
        this.width = width;
        this.height = height;

        province = new Province_Argentina();

        initialize();
    }

    private void initialize() {
        setLayout(null);
        setPreferredSize(new Dimension (width, height));
        setBackground(Color.BLACK);

        generatedMapPanel();
        generatedTitle();
        generatedProvinceCheckBox();
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


    private void generatedTitle() {
        title = new JPanel();
        title.setBounds(width-375,20,350,50);
        JTextTitulo = new JTextField();
		JTextTitulo.setFont(new Font("Unispace", Font.BOLD, 27));
		JTextTitulo.setText("Designing Regions");
		JTextTitulo.setEditable(false);
		title.add(JTextTitulo);
        add(title);
    }


    private void generatedProvinceCheckBox() {
        panelCheckBox = new JPanel();
        panelCheckBox.setBounds(width-375,80,350,300);
        panelCheckBox.setLayout(new GridLayout(0, 2)); // One column, as many rows as needed
        Map<String, Coordinates> test = province.getLocations();

        for (String province : test.keySet() ) {
            JCheckBox checkBox = new JCheckBox(province);
            panelCheckBox.add(checkBox);
        }

        bottonAddProvince = new JButton("generated Province");
        bottonAddProvince.setSize(100,100);

        panelCheckBox.add(bottonAddProvince);


        add(panelCheckBox);
        
    }

    public JButton getBottonAddProvince() {
        return bottonAddProvince;
    }

    

    

}
