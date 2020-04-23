package ai;

import game.ConnectFourGame;

/*
PSEUDOKOD

function alphaBeta(game, depth, alpha, beta):

    if game state is a terminal state or depth > MAX_DEPTH:
        return value of the board

    isMaximizingPlayer = game.currentPLayer.isMaximizing()

    if isMaximizingPlayer :             // RED player case
        bestVal = -INFINITY
        for each move in game.board :
            value = alphaBeta(game, depth+1, alpha, beta)
            bestVal = max(bestVal, value)
            alpha = max(alpha, bestVal)
            if beta <= alpha:
                return alpha
        return bestVal

    else :                              // YELLOW player case
        bestVal = +INFINITY
        for each move in game.board :
            value = alphaBeta(game, depth+1, alpha, beta)
            bestVal = min( bestVal, value)
            beta = min(beta, bestVal)
            if beta <= alpha:
                return beta
        return bestVal

 Początkowe wywołanie:
 alphaBeta(game, 0, -INFINITY, +INFINITY)
 */


public class AlphaBetaAI implements AI {
    private ConnectFourGame game;

    @Override
    public void setGame(ConnectFourGame game) {
        this.game = game;
    }

    @Override
    public int determineNextMove() {
        return 0;
    }
}
