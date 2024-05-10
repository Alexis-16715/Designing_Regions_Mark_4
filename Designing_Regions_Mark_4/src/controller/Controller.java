package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import view.Designing_Regions_View;

public class Controller {

    Designing_Regions_View view;

    private JButton bottonAddProvince;

    private List<JCheckBox> checkBoxList;

    private List<String> ProvinceAddArgentina;

    private Map<String,List<String>> test;

    public Controller (Designing_Regions_View view){
        this.view = view;
        attachListenersButton();
    }

    private void attachListenersButton() {
        bottonAddProvince = view.getBottonAddProvince();

        checkBoxList = view.getCheckBoxList();
        bottonAddProvince.addActionListener(e -> {
            ProvinceAddArgentina = new ArrayList<>();
            // Se encarga del evento del botton
            // por ahora lo imprime un mensaje
            for (JCheckBox checkBox : checkBoxList) {
                if (checkBox.isSelected()) {
                    ProvinceAddArgentina.add(checkBox.getText());
                    // System.out.println("Selected Province: " + checkBox.getText());
                }
                checkBox.setEnabled(false);
                bottonAddProvince.setEnabled(false);
            }
            view.generProvinceEdge(ProvinceAddArgentina);
        });
    }

}
