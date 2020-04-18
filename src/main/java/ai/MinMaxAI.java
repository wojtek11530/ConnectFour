package ai;

import game.Board;
import game.ConnectFourGame;
import game.Player;
import game.Token;
import gameControl.GameMoveObject;

import java.util.Random;


public class MinMaxAI implements AI {

    private int minmaxMaxDepth = 5;
    private ConnectFourGame game;

    public MinMaxAI() {
    }

    public MinMaxAI(int minmaxMaxDepth) {
        this.minmaxMaxDepth = minmaxMaxDepth;
    }

    @Override
    public void setGame(ConnectFourGame game) {
        this.game = game;
    }

    @Override
    public int determineNextMove() {

        Player currentPlayer = game.getCurrentPlayer();
        Token currentToken = currentPlayer.getPlayerToken();
        int column = 0;
        int bestResult;
        int minMaxResult;
        if (currentToken == Token.RED) {
            bestResult = Integer.MIN_VALUE;
        } else {
            bestResult = Integer.MAX_VALUE;
        }


        for (int colIndex = 0; colIndex < Board.COLUMNS_NUMBER; colIndex++) {
            if (game.putIntoColumnPossible(colIndex)) {
                GameMoveObject moveObject = game.putCurrentPlayerToken(colIndex);

                minMaxResult = minMax(0);
                if (minMaxResult > bestResult && currentToken == Token.RED) {
                    bestResult = minMaxResult;
                    column = colIndex;
                } else if (minMaxResult < bestResult && currentToken == Token.YELLOW) {
                    bestResult = minMaxResult;
                    column = colIndex;
                }
                game.setCurrentPlayer(currentPlayer);
                game.setEmptyField(moveObject.getLatMoveRow(), moveObject.getLastMoveCol());
                game.setWinner(null);
            }
        }

        game.setCurrentPlayer(currentPlayer);
        game.setWinner(null);
        return column;
    }

    public int minMax(int depth) {

        Player previousPlayer = game.getCurrentPlayer();
        if (game.isEnded() || depth > minmaxMaxDepth) {
            return game.evaluateState();
        } else {
            int minMaxResult;
            int bestResult;

            game.swapPlayers();

            Player currentPlayer = game.getCurrentPlayer();
            Token currentToken = currentPlayer.getPlayerToken();

            if (currentToken == Token.YELLOW) {
                bestResult = Integer.MAX_VALUE;
            } else {
                bestResult = Integer.MIN_VALUE;
            }

            for (int colIndex = 0; colIndex < Board.COLUMNS_NUMBER; colIndex++) {
                if (game.putIntoColumnPossible(colIndex)) {
                    GameMoveObject moveObject = game.putCurrentPlayerToken(colIndex);
                    minMaxResult = minMax(depth + 1);
                    if ((currentToken != Token.YELLOW && minMaxResult > bestResult) ||
                            (currentToken == Token.YELLOW && minMaxResult < bestResult)) {
                        bestResult = minMaxResult;
                    }
                    game.setCurrentPlayer(currentPlayer);
                    game.setEmptyField(moveObject.getLatMoveRow(), moveObject.getLastMoveCol());
                    game.setWinner(null);
                }
            }

            game.setCurrentPlayer(previousPlayer);
            return bestResult;
        }
    }



}
