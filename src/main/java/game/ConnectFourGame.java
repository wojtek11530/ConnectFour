package game;

import gameControl.GameMoveObject;

import java.util.Random;

public class ConnectFourGame {

    public static final int WINNING_LENGTH = 4;

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player winner;

    public ConnectFourGame() {
        board = new Board();
        player1 = new Player(Token.YELLOW, PlayerType.HUMAN);
        player2 = new Player(Token.RED, PlayerType.AI);
    }

    public void initGame() {
        board.setBoardEmpty();
        winner = null;
        currentPlayer = new Random().nextDouble() < 0.5 ? player1 : player2;
    }

    public void swapPlayers() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public GameMoveObject putCurrentPlayerToken(int column) {
        if (column >= 0 && column < Board.COLUMNS_NUMBER && putIntoColumnPossible(column)) {
            Token currentToken = currentPlayer.getPlayerToken();
            int row = putTokenIntoColumn(currentToken, column);
            if (row != -1) {
                return new GameMoveObject(getBoard().getBoard(), row, column, currentToken);
            } else {
                return new GameMoveObject();
            }

        } else {
            return new GameMoveObject();
        }
    }

    public boolean isEnded() {
        Token winningToken = winning();
        if (winningToken != null) {
            determineWinnerPlayer(winningToken);
            return true;
        } else return boardFull();
    }

    private void determineWinnerPlayer(Token winningToken) {
        winner = winningToken == player1.getPlayerToken() ? player1 : player2;
    }

    private boolean putIntoColumnPossible(int column) {
        Token[] theUppestRow = board.getUppestRow();
        return theUppestRow[column] == Token.EMPTY;
    }

    private int putTokenIntoColumn(Token currentToken, int column) {
        Token[][] boardFields = board.getBoard();
        for (int rowIndex = Board.ROW_NUMBER - 1; rowIndex >= 0; rowIndex--) {
            if (boardFields[rowIndex][column] == Token.EMPTY) {
                boardFields[rowIndex][column] = currentToken;
                return rowIndex;
            }
        }
        return -1;
    }

    private boolean boardFull() {
        Token[] theUppestRow = board.getUppestRow();
        for (Token token : theUppestRow) {
            if (token == Token.EMPTY) {
                return false;
            }
        }
        return true;
    }

    private Token winning() {
        Token[][] boardFields = board.getBoard();
        int[][] directions = {{1, 0}, {1, -1}, {1, 1}, {0, 1}};

        for (int[] d : directions) {
            int rowDirection = d[0];
            int colDirection = d[1];
            for (int rowIndex = 0; rowIndex < Board.ROW_NUMBER; rowIndex++) {
                for (int colIndex = 0; colIndex < Board.COLUMNS_NUMBER; colIndex++) {
                    int lastRowIndex = rowIndex + 3 * rowDirection;
                    int lastColIndex = colIndex + 3 * colDirection;
                    if (0 <= lastRowIndex && lastRowIndex < Board.ROW_NUMBER && 0 <= lastColIndex && lastColIndex < Board.COLUMNS_NUMBER) {
                        Token currentToken = boardFields[rowIndex][colIndex];
                        if (currentToken != Token.EMPTY
                                && currentToken == boardFields[rowIndex + rowDirection][colIndex + colDirection]
                                && currentToken == boardFields[rowIndex + 2 * rowDirection][colIndex + 2 * colDirection]
                                && currentToken == boardFields[lastRowIndex][lastColIndex]) {
                            return currentToken;
                        }
                    }
                }
            }
        }
        return null;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    /*

    private Token winning() {
        Token[][] boardFields = board.getBoard();
        Token winningFromRows = winningInAnyRow(boardFields);
        Token winningFromCols = winningInAnyColumn(boardFields);
        Token winningFromDiags = winningInAnyDiag(boardFields);
        if (winningFromRows != null) {
            return winningFromRows;
        } else if (winningFromCols != null) {
            return winningFromCols;
        } else if (winningFromDiags != null) {
            return winningFromDiags;
        } else {
            return null;
        }
    }



    private Token winningInAnyRow(Token[][] boardFields) {
        Token winningToken = null;
        int rowIndex = 0;
        while (winningToken == null && rowIndex < boardFields.length) {
            Token[] row = boardFields[rowIndex];
            winningToken = foundWinningTokenInLine(row);
            rowIndex++;
        }
        return winningToken;
    }

    private Token winningInAnyColumn(Token[][] boardFields) {
        Token winningToken = null;
        int colIndex = 0;
        while (winningToken == null && colIndex < boardFields[0].length) {
            Token[] col = new Token[boardFields.length];
            for (int i = 0; i < boardFields.length; i++) {
                col[i] = boardFields[i][colIndex];
            }
            winningToken = foundWinningTokenInLine(col);
            colIndex++;
        }
        return winningToken;
    }

    private Token winningInAnyDiag(Token[][] boardFields) {

        return null;
    }

    private Token foundWinningTokenInLine(Token[] line) {
        Token winningToken = null;
        Token currentToken;
        int currentLineLength;
        currentToken = null;
        currentLineLength = 0;
        for (Token token : line) {
            if (currentToken == token) {
                currentLineLength++;
            } else {
                currentLineLength = 1;
            }
            currentToken = token;

            if (currentLineLength == WINNING_LENGTH && currentToken != Token.EMPTY) {
                winningToken = currentToken;
            }
        }
        return winningToken;
    }

     */


}
