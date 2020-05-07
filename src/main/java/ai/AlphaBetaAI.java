package ai;

import game.Board;
import game.ConnectFourGame;
import game.Player;
import game.Token;
import gameControl.GameMoveObject;


public class AlphaBetaAI implements AI {


    private int minmaxMaxDepth = 5;
    private ConnectFourGame game;
    private GameStateEvaluator evaluator;


    public AlphaBetaAI() {
    }

    public AlphaBetaAI(int minmaxMaxDepth, GameStateEvaluator evaluator) {
        this.minmaxMaxDepth = minmaxMaxDepth;
        this.evaluator = evaluator;
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

        if (currentToken == Token.RED) {
            bestResult = Integer.MIN_VALUE;
        } else {
            bestResult = Integer.MAX_VALUE;
        }

        for (int colIndex = 0; colIndex < Board.COLUMNS_NUMBER; colIndex++) {
            if (game.putIntoColumnPossible(colIndex)) {
                GameMoveObject moveObject = game.putCurrentPlayerToken(colIndex);

                int minMaxResult = minMaxWihAlphaBeta(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (minMaxResult > bestResult && currentToken == Token.RED) {
                    bestResult = minMaxResult;
                    column = colIndex;
                } else if (minMaxResult < bestResult && currentToken == Token.YELLOW) {
                    bestResult = minMaxResult;
                    column = colIndex;
                }
                game.setEmptyField(moveObject.getLatMoveRow(), moveObject.getLastMoveCol());
                game.setCurrentPlayer(currentPlayer);
                game.setWinner(null);
            }
        }

        game.setCurrentPlayer(currentPlayer);
        game.setWinner(null);
        return column;
    }

    public int minMaxWihAlphaBeta(int depth, int alpha, int beta) {

        Player previousPlayer = game.getCurrentPlayer();
        if (game.isEnded() || depth > minmaxMaxDepth) {
            return (int) (Math.pow(0.98, depth - 1) * evaluateState());
        } else {
            int bestResult;

            game.swapPlayers();
            Player currentPlayer = game.getCurrentPlayer();
            Token currentToken = currentPlayer.getPlayerToken();

            if (currentToken == Token.YELLOW) { // minimizing
                bestResult = Integer.MAX_VALUE;
            } else {
                bestResult = Integer.MIN_VALUE; // maximizing
            }

            for (int colIndex = 0; colIndex < Board.COLUMNS_NUMBER; colIndex++) {
                if (game.putIntoColumnPossible(colIndex)) {
                    GameMoveObject moveObject = game.putCurrentPlayerToken(colIndex);

                    int minMaxResult = minMaxWihAlphaBeta(depth + 1, alpha, beta);

                    game.setEmptyField(moveObject.getLatMoveRow(), moveObject.getLastMoveCol());
                    game.setWinner(null);
                    game.setCurrentPlayer(currentPlayer);

                    if (currentToken == Token.RED) {
                        bestResult = Math.max(minMaxResult, bestResult);
                        alpha = Math.max(alpha, bestResult);
                        if (beta <= alpha) {
                            game.setCurrentPlayer(previousPlayer);
                            return alpha;
                        }

                    } else {
                        bestResult = Math.min(minMaxResult, bestResult);
                        beta = Math.min(beta, bestResult);
                        if (beta <= alpha) {
                            return beta;
                        }
                    }
                }
            }

            game.setCurrentPlayer(previousPlayer);
            return bestResult;
        }
    }

    public int evaluateState() {
        return evaluator.evaluateGame(game.getBoard());
    }

    @Override
    public String toString() {
        return "AlphaBeta_" + minmaxMaxDepth;
    }
}
