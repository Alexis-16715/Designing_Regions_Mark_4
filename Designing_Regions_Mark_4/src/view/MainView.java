package view;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainView {

    private int width;
    private int height;

    private JFrame frame;


    private DesigningRegionsView designingRegions;

    //Icono creado por Alexis Knack
    private URL image = getClass().getResource("/images/Icon mark 3.png");

    //Lo que hace esta clase es inicializar la ventana en general
    public MainView(){
        initialize();
    }

    private void initialize() {

        height=950;
        width=900;

        frame = new JFrame();

        frame.setTitle("Diseña Regiones Argentina");
        

        ImageIcon icon = new ImageIcon(image);
        frame.setIconImage(icon.getImage());


        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        designingRegions = new DesigningRegionsView(width, height);
        designingRegions.setVisible(true);
        frame.add(designingRegions);
        frame.pack();


        designingRegions.requestFocus();
    }

    public DesigningRegionsView getDesigningRegions() {
        return designingRegions;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
