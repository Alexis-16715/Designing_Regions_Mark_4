package view;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main_View {

    private int width;
    private int height;
    private JFrame frame;


    private DesigningRegigions_View designingRegions;

    private URL image = getClass().getResource("/images/Icon mark 2.png");

    public Main_View(){
        initialize();
    }

    private void initialize() {

        height=900;
        width=900;

        frame = new JFrame();

        frame.setTitle("Designing Regions Argentina");

        ImageIcon icon = new ImageIcon(image);
        frame.setIconImage(icon.getImage());


        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        designingRegions = new DesigningRegigions_View(width, height);
        designingRegions.setVisible(true);
        frame.add(designingRegions);
        frame.pack();


        designingRegions.requestFocus();
    }

    public DesigningRegigions_View getDesigningRegions() {
        return designingRegions;
    }

}
