package gameControl;

import game.Board;
import game.Player;
import game.Token;

import javax.swing.*;

public class GameStartObject {

    private Board board;
    private Player player1;
    private Player player2;
    private Player nextPlayer;

    public GameStartObject(Board board, Player player1, Player player2, Player nextPlayer) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.nextPlayer = nextPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }
}
