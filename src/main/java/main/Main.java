package main;


import gameControl.GameController;

import gameResearch.Simulations;
import userInreface.GUI;

public class Main {

    public static void main(String[] args) {

        GameController gameController = new GameController(new GUI());
        gameController.settleGame();
//        Simulations.run();

    }
}
