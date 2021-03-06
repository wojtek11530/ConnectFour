package game;

import java.util.Arrays;

public class Board {

    public static int ROW_NUMBER = 6;
    public static int COLUMNS_NUMBER = 7;

    private Token[][] board;

    public Board() {
        board = new Token[ROW_NUMBER][COLUMNS_NUMBER];
        setBoardEmpty();
    }

    public void setBoardEmpty() {
        for (int rowi = 0; rowi < ROW_NUMBER; rowi++) {
            for (int coli = 0; coli < COLUMNS_NUMBER; coli++) {
                board[rowi][coli] = Token.EMPTY;
            }
        }
    }


    public Token[][] getBoard() {
        return board;
    }

    public void setBoard(Token[][] board) {
        this.board = board;
    }

    public Token[] getUppestRow() {
        return board[0];
    }

    public Token[] getLowestRow() {
        return board[5];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Token[] row : board) {
            for (Token token : row) {
                sb.append(token.getSign()).append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
