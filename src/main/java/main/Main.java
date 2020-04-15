package main;


import gameControl.GameController;
import userInreface.ConsoleUserInterfaceImpl;
import userInreface.GUI;

import java.net.URL;

public class Main {

    public static void main(String[] args) {

        //GUI gui = new GUI();
        GameController gameController = new GameController(new GUI());
        gameController.startGame();
    }
}
