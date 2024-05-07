package controller;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import view.Designing_Regions_View;

public class Controller {

    Designing_Regions_View view;

    private JButton bottonAddProvince;

    private List<JCheckBox> checkBoxList;

    public Controller (Designing_Regions_View view){
        this.view = view;
        attachListenersButton();
    }

    private void attachListenersButton() {
        bottonAddProvince = view.getBottonAddProvince();

        checkBoxList = view.getCheckBoxList();
        
        bottonAddProvince.addActionListener(e -> {
            // Se encarga del evento del botton
            // por ahora lo imprime un mensaje
            for (JCheckBox checkBox : checkBoxList) {
                if (checkBox.isSelected()) {
                    System.out.println("Selected Province: " + checkBox.getText());
                }
                checkBox.setEnabled(false);
                bottonAddProvince.setEnabled(false);
            }
        });
    }

}
