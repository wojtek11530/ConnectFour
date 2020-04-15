package gameControl;

import game.Board;
import game.ConnectFourGame;
import game.PlayerType;
import userInreface.UserInterface;

import java.util.Random;

public class GameController {

    ConnectFourGame game;
    UserInterface userInterface;
    Random random = new Random();

    public GameController(UserInterface userInterface) {
        game = new ConnectFourGame();
        this.userInterface = userInterface;
        this.userInterface.setGameController(this);
    }

    public void startGame() {
        game.initGame();
        GameStartObject gameStartObject = new GameStartObject(game.getBoard(), game.getPlayer1(), game.getPlayer2(), game.getCurrentPlayer());
        userInterface.printStartGame(gameStartObject);
    }

    public void playNextMove(int columnChoice) {
        GameMoveObject moveObject = game.putCurrentPlayerToken(columnChoice);
        if (moveObject.isMoveMade()) {
            if (game.isEnded()) {
                EndGameObject endGameObject = new EndGameObject(game.getBoard().getBoard(), game.getWinner(), moveObject);
                userInterface.printEndedGame(endGameObject);
            } else {
                game.swapPlayers();
                userInterface.printGameAfterMove(moveObject, game.getCurrentPlayer());
                if (game.getCurrentPlayer().getType() == PlayerType.AI) {
                    performAIMove();
                }
            }
        } else if (game.getCurrentPlayer().getType() == PlayerType.AI) {
            performAIMove();
        } else {
            userInterface.printWrongMove();
        }
    }

    public void performAIMove() {
        int randomCol = random.nextInt(Board.COLUMNS_NUMBER);
        playNextMove(randomCol);
    }

}
