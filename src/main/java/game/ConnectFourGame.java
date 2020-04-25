package game;

import ai.GameStateEvaluator;
import ai.GameStateEvaluatorImpl;
import gameControl.GameMoveObject;

public class ConnectFourGame {

    public static final int WINNING_LENGTH = 4;

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player winner;

    private GameStateEvaluator evaluator;

    public ConnectFourGame() {
        board = new Board();
        player1 = new ComputerPlayer(Token.YELLOW, PlayerType.AI);
        player2 = new HumanPlayer(Token.RED, PlayerType.HUMAN);
        evaluator = new GameStateEvaluatorImpl();
    }

    public ConnectFourGame(Player playerOne, Player playerTwo) {
        board = new Board();
        player1 = playerOne;
        player2 = playerTwo;
        evaluator = new GameStateEvaluatorImpl();
    }

    public ConnectFourGame(Player playerOne, Player playerTwo, GameStateEvaluator gameStateEvaluator) {
        board = new Board();
        player1 = playerOne;
        player2 = playerTwo;
        evaluator = gameStateEvaluator;
    }

    public void initGame() {
        board.setBoardEmpty();
        winner = null;
        currentPlayer = player1;
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

    public boolean putIntoColumnPossible(int column) {
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

    public boolean boardFull() {
        Token[] theUppestRow = board.getUppestRow();
        for (Token token : theUppestRow) {
            if (token == Token.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public Token winning() {
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


    public void setEmptyField(int rowIndex, int colIndex) {
        Token[][] boardFields = board.getBoard();
        boardFields[rowIndex][colIndex] = Token.EMPTY;
    }


    public int evaluateState() {
        return evaluator.evaluateGame(getBoard());
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

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
