package gameResearch;

import ai.AI;
import ai.GameStateEvaluator;
import ai.GameStateEvaluatorImpl;
import ai.MinMaxAI;
import game.*;
import gameControl.EndGameObject;
import gameControl.GameMoveObject;
import gameControl.GameStartObject;
import userInreface.UserInterface;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalTime;

public class GameResearchController {

    ConnectFourGame game;
    GameStatistics statistics;

    public GameStatistics startGame(AI playerOneAi, AI playerTwoAi,  GameStateEvaluator evaluator) {
        setNewGame(playerOneAi, playerTwoAi, evaluator);
        game.initGame();
        statistics.setStartingPlayer(game.getCurrentPlayer());

        boolean gameNotEnded = true;
        while (gameNotEnded) {
            Player currentPlayer = game.getCurrentPlayer();

            LocalTime beginningTime = LocalTime.now();
            gameNotEnded = !playNextMove();
            LocalTime endTime = LocalTime.now();
            long moveTime = Duration.between(beginningTime, endTime).toMillis();
            statistics.addPlayerMoveTime(currentPlayer, moveTime);

            game.swapPlayers();
        }
        statistics.setWinner(game.getWinner());
        return statistics;
    }

    private void setNewGame(AI playerOneAi, AI playerTwoAi,  GameStateEvaluator evaluator) {
        ComputerPlayer playerOne = new ComputerPlayer(Token.YELLOW, PlayerType.AI);
        playerOne.setAi(playerOneAi);

        ComputerPlayer playerTwo = new ComputerPlayer(Token.RED, PlayerType.AI);
        playerTwo.setAi(playerTwoAi);

        game = new ConnectFourGame(playerOne, playerTwo, evaluator);

        playerOne.assignToGame(game);
        playerTwo.assignToGame(game);

        statistics = new GameStatistics(game);
    }

    private boolean playNextMove() {
        ComputerPlayer currentAIPlayer = (ComputerPlayer) game.getCurrentPlayer();
        boolean moveNotMade = true;
        while (moveNotMade) {
            int aiNextMove = currentAIPlayer.getAIMove();
            GameMoveObject moveObject = game.putCurrentPlayerToken(aiNextMove);
            moveNotMade = !moveObject.isMoveMade();
        }
        return game.isEnded();
    }


}
