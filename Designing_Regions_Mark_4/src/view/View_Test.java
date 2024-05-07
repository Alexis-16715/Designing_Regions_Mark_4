package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class View_Test extends JFrame {

    private JMapViewer mapViewer;

    public View_Test() {
        initialization();
    }

    private class ProvinceMarker extends MapMarkerDot {
        private final Province province;

        public ProvinceMarker(Province province) {
            super(province.getLatitude(), province.getLongitude());
            this.province = province;
        }
    }

    class MapPolyLine extends MapPolygonImpl {
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
            if (points != null && points.size() > 0) {
                Point firstPoint = points.get(0);
                path.moveTo(firstPoint.getX(), firstPoint.getY());
                for (Point p : points) {
                    path.lineTo(p.getX(), p.getY());
                }
            }
            return path;
        }
    }


    private void initialization() {
        setSize(800, 600);
        setTitle("Designing Regions");
        mapViewer = new JMapViewer();

        // Add markers for each province
        for (Province province : Province.values()) {
            ProvinceMarker marker = new ProvinceMarker(province);
            mapViewer.addMapMarker(marker);
        }

        mapViewer.setDisplayPosition(new Coordinate(-30, -60), 5);

        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(50, 10));
        coordinates.add(new Coordinate(52, 15));
        coordinates.add(new Coordinate(55, 15));
        coordinates.add(new Coordinate(-28.4696, -65.7852));

        MapPolyLine polyLine = new MapPolyLine(coordinates);
        mapViewer.addMapPolygon(polyLine);
        add(mapViewer);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setVisible(true);
    }


    private enum Province {
        BUENOS_AIRES("Buenos Aires", 55, 15),
        CIUDAD_AUTONOMA_BUENOS_AIRES("Ciudad Autónoma de Buenos Aires", -34.615, -58.433),
        CATAMARCA("Catamarca", -28.4696, -65.7852),
        CHACO("Chaco", -27.4512, -58.9866),
        CHUBUT("Chubut", -43.3002, -65.1023),
        CORDOBA("Córdoba", 52, 15),
        CORRIENTES("Corrientes", -27.4692, -58.8302),
        ENTRE_RIOS("Entre Ríos", -31.6222, -60.7299),
        FORMOSA("Formosa", -26.1852, -58.1761),
        JUJUY("Jujuy", -24.1858, -65.2995),
        LA_PAMPA("La Pampa", -36.6167, -64.2833),
        LA_RIOJA("La Rioja", -29.4131, -66.8558),
        MENDOZA("Mendoza", -32.8908, -68.8272),
        MISIONES("Misiones", -26.5003, -54.5837),
        NEUQUEN("Neuquén", -38.9516, -68.0591),
        RIO_NEGRO("Río Negro", -40.8128, -63.0012),
        SALTA("Salta", -24.7859, -65.4117),
        SAN_JUAN("San Juan", -31.5375, -68.5364),
        SAN_LUIS("San Luis", -33.2994, -66.3356),
        SANTA_CRUZ("Santa Cruz", -50.0000, -69.0000),
        SANTA_FE("Santa Fe", -31.6333, -60.7000),
        SANTIAGO_DEL_ESTERO("Santiago del Estero", -27.7834, -64.2649),
        TIERRA_DEL_FUEGO("Tierra del Fuego", -54.8000, -68.3000),
        TUCUMAN("Tucumán", -26.8241, -65.2226);

        private final String name;
        private final double latitude;
        private final double longitude;

        Province(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }

    

    public static void main(String[] args) {
        new View_Test();
    }
}
