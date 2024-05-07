package main;

import controller.Controller;
import view.Main_View;

public class Main {

    //Esta clase es solo encargada de hacer un main de todo
    public static void main(String[] args) {
        Main_View view = new Main_View();
        Controller controller = new Controller(view.getDesigningRegions());
    }

}
