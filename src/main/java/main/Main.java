package main;


import gameControl.GameController;
import userInreface.GUI;

public class Main {

    public static void main(String[] args) {
        GameController gameController = new GameController(new GUI());
        gameController.settleGame();
    }
}
