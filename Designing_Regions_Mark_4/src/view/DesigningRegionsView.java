package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.geom.Path2D;
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
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

import province.Coordinates;
import province.ProvinceArgentina;

public class DesigningRegionsView extends JPanel {

    private int width;
    private int height;


    private JMapViewer mapViewer;
    private JPanel panelMap;


    private JPanel panelElementsLeft;
    private int positionPanelX;
    private int postionPanelY;

    private JPanel title;
    private JTextField JTextTitulo;



    private ProvinceArgentina province;
    private Coordinate argentina;



    private JPanel panelCheckBox;
    private JButton bottonAddProvince;
    private JPanel panelConectionEdges;
    private static List<JCheckBox> checkBoxList;
    private Map<String, Coordinates> provinceNameLocations;

    private JPanel panelBottons;
    private JButton bottonAddProvinceConnectionGraph;
    private JButton bottonKruskal;


    private List<JComboBox<String>> listComboBoxProvince;
    private List<JComboBox<Integer>> listComboBoxWeight;


    private Panel panelDivideCountry;
    private JButton bottonDivideCountry;

    private JComboBox<Integer> comboBoxDivideCountry;

    //Esta es la clase en la que se genera los botones y mapa

    public DesigningRegionsView(int width, int height){
        this.width = width;
        this.height = height;

        province = new ProvinceArgentina();

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
		JTextTitulo.setText("Diseño de regiones");
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

        bottonAddProvince = new JButton("Generar Provincia");
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

        listComboBoxProvince = new ArrayList<>();
        listComboBoxWeight = new ArrayList<>();

        for (String nameProvince : selectPorvince) {
            double latitude = provinceNameLocations.get(nameProvince).getLatitude();
            double longitude = provinceNameLocations.get(nameProvince).getLongitude();

            MapMarkerDot marker = new MapMarkerDot(nameProvince, new Coordinate(latitude, longitude));

            marker.getStyle().setBackColor(Color.RED);
            marker.getStyle().setColor(Color.BLUE);

            mapViewer.addMapMarker(marker);
            JPanel rowPanel = new JPanel(new GridLayout(1, 1));
            rowPanel.setBounds(0,positonX,400,20);
            rowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            positonX = positonX + 22;
            JLabel label = new JLabel(nameProvince);
            rowPanel.add(label);
        
            JComboBox<String> comboBox1 = new JComboBox<>(createComboBoxProvince(selectPorvince, nameProvince));
            rowPanel.add(comboBox1);
        
            JComboBox<Integer> comboBox2 = new JComboBox<>(createComboBoxModel(10)); 
            rowPanel.add(comboBox2); 

            listComboBoxProvince.add(comboBox1);
            listComboBoxWeight.add(comboBox2);

            panelConectionEdges.add(rowPanel);
            
        }
        generatedBottonsGraph();
        panelConectionEdges.revalidate();
        panelConectionEdges.repaint();

        panelElementsLeft.revalidate();
        panelElementsLeft.repaint();

    }

    private DefaultComboBoxModel<String> createComboBoxProvince(List<String> selectPorvince, String nameProvince) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("No seleccionado");
        for (String province : selectPorvince ) {
            if(nameProvince != province){
                model.addElement(province);
            }
        }
        return model;
    }

    private DefaultComboBoxModel<Integer> createComboBoxModel(int limited) {
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        for (int i = 1; i <= limited; i++) {
            model.addElement(i);
        }
        return model;
    }

    private void generatedBottonsGraph() {
        panelBottons = new JPanel();
        panelBottons.setBounds(0,900,400,40);
        panelElementsLeft.add(panelBottons);

        bottonAddProvinceConnectionGraph = new JButton("Agregar la conexión");
        panelBottons.add(bottonAddProvinceConnectionGraph);

        bottonKruskal = new JButton("Ejecutar algoritmo");
        bottonKruskal.setEnabled(false);
        panelBottons.add(bottonKruskal);

    }

    public void removeCheckBoxElements(){
        panelCheckBox.removeAll();
        revalidate();
        repaint();
    }

    public void createCheckboxDivideCountry(int limited){

        panelCheckBox.setLayout(null); 
        panelCheckBox.setBounds(0,60,400,300);


        panelDivideCountry = new Panel();
        panelDivideCountry.setBounds(0,10,400,70);
        panelCheckBox.add(panelDivideCountry);

        JLabel headerLabel = new JLabel("Cuanto veces quieres dividir el grafo: ");
        panelDivideCountry.add(headerLabel);


        comboBoxDivideCountry = new JComboBox<>(createComboBoxModel(limited)); 

        panelDivideCountry.add(comboBoxDivideCountry); 


        bottonDivideCountry = new JButton("Generar el grafo y texto");
        panelDivideCountry.add(bottonDivideCountry);

        

    }


    public void removePreviewsMappolygons(){
        mapViewer.removeAllMapPolygons();
        mapViewer.revalidate();
        mapViewer.repaint();
    }

    public void createMapPoligonMark2(List<Coordinate> route ){
        mapViewer.addMapPolygon(new MapPolyLine(route));
        mapViewer.addMapPolygon(new MapPolyLine(route));
        mapViewer.revalidate();
        mapViewer.repaint();
    }

    public class MapPolyLine extends MapPolygonImpl {
        public MapPolyLine(List<? extends ICoordinate> points) {
            super(null, null, points);
        }
    
        @Override
        public void paint(Graphics g, List<Point> points) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(getColor());
            g2d.setStroke(getStroke());
            Path2D path = buildPath(points);
            g2d.draw(path);
            g2d.dispose();
        }
    
        private Path2D buildPath(List<Point> points) {
            Path2D path = new Path2D.Double();
            if (points != null && points.size() >= 1) {
                Point firstPoint = points.get(0);
                path.moveTo(firstPoint.getX(), firstPoint.getY());
                for (int i = 0; i < points.size(); i++) { 
                    Point p = points.get(i);
                    path.lineTo(p.getX(), p.getY());
                }
            }
            return path;
        }
    }

    public void createStringOfTheGraph(List<String> kruskal, List<String> originalGraph) {
        panelConectionEdges.removeAll();
        panelConectionEdges.revalidate();
        panelConectionEdges.repaint();
        // Esto es para mostrar el grafo Original
        addGraphToPanel(originalGraph, "Este es el gráfico original.");

        // Esto es para mostrar el grafo de Kruskal
        addGraphToPanel(kruskal, "Este es el gráfico de Kruskal.");


        panelConectionEdges.revalidate();
        panelConectionEdges.repaint();
    }

    
    private void addGraphToPanel(List<String> graphRepresentation, String header) {
    
        int positionY = panelConectionEdges.getComponentCount() * 20;; // Posición inicial para la primera fila.
    
        JPanel headerPanel = new JPanel(new GridLayout(1, 1));
        JLabel headerLabel = new JLabel(header);
        headerPanel.add(headerLabel);
        headerPanel.setVisible(true);
        headerPanel.setBounds(0, positionY, 400, 20);
        panelConectionEdges.add(headerPanel);
    
        positionY += 20; // Se incrementa para que el texto quede bien
    
        for (String line : graphRepresentation) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 1));
            JLabel label = new JLabel(line.toString());
            rowPanel.setBounds(0, positionY, 400, 20);
            rowPanel.add(label);
            panelConectionEdges.add(rowPanel);
            positionY += 20; // Incrementa la position para la siguiente fila
        }
    
        panelConectionEdges.revalidate(); // RRevalidamos los componetes para que se vean
        panelConectionEdges.repaint(); // Repintamos del panel para que se vea
    }


    public JButton getBottonAddProvince() {
        return bottonAddProvince;
    }

    public List<JCheckBox> getCheckBoxList() {
        return checkBoxList;
    }


    public List<JComboBox<String>> getListComboBoxProvince() {
        return listComboBoxProvince;
    }

    public List<JComboBox<Integer>> getListComboBoxWeight() {
        return listComboBoxWeight;
    }

    public JButton getBottonAddProvinceConnectionGraph() {
        return bottonAddProvinceConnectionGraph;
    }

    public JButton getBottonKruskal() {
        return bottonKruskal;
    }

    public Map<String, Coordinates> getProvinceNameLocations() {
        return provinceNameLocations;
    }

    public JButton getBottonDivideCountry() {
        return bottonDivideCountry;
    }

    public JComboBox<Integer> getComboBoxDivideCountry() {
        return comboBoxDivideCountry;
    }

    

}