package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

    private JPanel panelElementsLeft;

    private int positionPanelX;
    private int postionPanelY;


    private JMapViewer mapViewer;


    private Coordinate argentina;

    private JTextField JTextTitulo;


    private JPanel panelCheckBox;
    private JButton bottonAddProvince;
    private JPanel panelConectionEdges;
    private static List<JCheckBox> checkBoxList;

    private Map<String, Coordinates> provinceNameLocations;
    private JPanel panelBottons;
    private JButton bottonAddProvinceConnectionGraph;
    private JButton bottonKruskal;
    private JButton bottonReset;

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
        
        //Esto genera el panel y el mapa
        generatedMapPanel();

        generatedPanel();
        generatedTitle();
        generatedProvinceCheckBox();
        // generatedProviceEdges();

        generatedBottonsGraph();
    }

    private void generatedPanel() {
        panelElementsLeft = new JPanel();
        panelElementsLeft.setBackground(Color.black);
        panelElementsLeft.setLayout(null);
        panelElementsLeft.setBounds(width-415,0,500,height);
        add(panelElementsLeft);
    }

    private void generatedMapPanel() {
        panelMap = new JPanel();
        positionPanelX = 20;
        postionPanelY = -5;
        panelMap.setBounds(positionPanelX, postionPanelY, width/2, height);
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
        title.setBounds(0,5,400,50);
        JTextTitulo = new JTextField();
		JTextTitulo.setFont(new Font("Unispace", Font.BOLD, 27));
		JTextTitulo.setText("Designing Regions");
		JTextTitulo.setEditable(false);
        JTextTitulo.setBorder(null);
		title.add(JTextTitulo);
        panelElementsLeft.add(title);
    }


    private void generatedProvinceCheckBox() {
        checkBoxList = new ArrayList<>();


        panelCheckBox = new JPanel();
        panelCheckBox.setBounds(0,60,400,300);
        panelCheckBox.setLayout(new GridLayout(0, 2)); // Dos Columnas
        provinceNameLocations = province.getLocations();

        for (String province : provinceNameLocations.keySet() ) {
            JCheckBox checkBox = new JCheckBox(province);
            checkBoxList.add(checkBox);
            panelCheckBox.add(checkBox);
        }

        bottonAddProvince = new JButton("generated Province");
        bottonAddProvince.setSize(100,100);

        panelCheckBox.add(bottonAddProvince);


        panelElementsLeft.add(panelCheckBox);
        
    }

    public void generProvinceEdge(List<String> selectPorvince){
        usedListForProvinceEdges(selectPorvince);
    }

    private void usedListForProvinceEdges(List<String> selectPorvince){
        panelConectionEdges = new JPanel();
        panelConectionEdges.setBounds(0,365,400,528);
        panelElementsLeft.add(panelConectionEdges);
        panelConectionEdges.setLayout(null); 

        //Esto para que la position del panel quede bien

        int positonX = 0;

        for (String nameProvince : selectPorvince) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 1));
            rowPanel.setBounds(0,positonX,400,20);
            rowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            positonX = positonX + 22;
            JLabel label = new JLabel(nameProvince); // Create label with province name
            rowPanel.add(label);
        
            JComboBox<String> comboBox1 = new JComboBox<>(createComboBoxProvince(selectPorvince, nameProvince)); // Create first JComboBox
            rowPanel.add(comboBox1);
        
            JComboBox<Integer> comboBox2 = new JComboBox<>(createComboBoxModel()); // Create second JComboBox
            rowPanel.add(comboBox2); 

            panelConectionEdges.add(rowPanel);
            
        }
        panelConectionEdges.revalidate();
        panelConectionEdges.repaint();

        panelElementsLeft.revalidate();
        panelElementsLeft.repaint();

    }

    private DefaultComboBoxModel<String> createComboBoxProvince(List<String> selectPorvince, String nameProvince) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String province : selectPorvince ) {
            if(nameProvince != province){
                model.addElement(province);
            }
        }
        return model;
    }

    private DefaultComboBoxModel<Integer> createComboBoxModel() {
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        for (int i = 1; i <= 10; i++) {
            model.addElement(i);
        }
        return model;
    }

    private void generatedBottonsGraph() {
        panelBottons = new JPanel();
        panelBottons.setBounds(0,900,400,40);
        panelElementsLeft.add(panelBottons);

        bottonAddProvinceConnectionGraph = new JButton("Add the Connection");
        panelBottons.add(bottonAddProvinceConnectionGraph);

        bottonKruskal = new JButton("Run algorithm");
        panelBottons.add(bottonKruskal);

        bottonReset = new JButton("Reset");
        panelBottons.add(bottonReset);

    }


    public JButton getBottonAddProvince() {
        return bottonAddProvince;
    }

    public List<JCheckBox> getCheckBoxList() {
        return checkBoxList;
    }

    

    

}
