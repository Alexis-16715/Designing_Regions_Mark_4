package controller;

import javax.swing.JButton;

import view.Designing_Regions_View;

public class Controller {

    Designing_Regions_View view;

    private JButton bottonAddProvince;

    public Controller (Designing_Regions_View view){
        this.view = view;
        attachListenersButton();
    }

    private void attachListenersButton() {
        bottonAddProvince = view.getBottonAddProvince();
        bottonAddProvince.addActionListener(e -> {
            // Handle button click event
            // For now, let's just print a message
            System.out.println("Button 'Generate Province' clicked.");
        });
    }

}
