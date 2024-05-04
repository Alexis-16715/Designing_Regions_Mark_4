package view;

import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JPanel;

public class DesigningRegigions_View extends JPanel {

    private int width;
    private int height;

    public DesigningRegigions_View(int width, int height){

        this.width = width;
        this.height = height;
        initialize();
    }

    private void initialize() {
        setPreferredSize(new Dimension (width, height));
        setBackground(Color.BLACK);
    }

}
