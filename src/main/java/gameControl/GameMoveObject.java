package gameControl;

import game.Player;
import game.Token;

public class GameMoveObject {

    private boolean moveMade = false;
    private Token[][] board;
    private int latMoveRow;
    private int lastMoveCol;
    private Token lastMoveToken;

    public GameMoveObject(Token[][] board, int latMoveRow, int lastMoveCol, Token lastMoveToken) {
        moveMade = true;
        this.board = board;
        this.latMoveRow = latMoveRow;
        this.lastMoveCol = lastMoveCol;
        this.lastMoveToken = lastMoveToken;
    }

    public GameMoveObject(){}

    public boolean isMoveMade() {
        return moveMade;
    }

    public Token[][] getBoard() {
        return board;
    }

    public int getLatMoveRow() {
        return latMoveRow;
    }

    public int getLastMoveCol() {
        return lastMoveCol;
    }

    public Token getLastMoveToken() {
        return lastMoveToken;
    }
}

