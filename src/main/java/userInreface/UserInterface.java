package userInreface;

import game.Board;
import game.ConnectFourGame;
import game.Player;
import gameControl.EndGameObject;
import gameControl.GameController;
import gameControl.GameMoveObject;
import gameControl.GameStartObject;

public interface UserInterface {

    void setGameController(GameController gameController);
    void printStartGame(GameStartObject gameStartObject);
    void printGameAfterMove(GameMoveObject gameMoveObject, Player nextPlayer);
    void printEndedGame(EndGameObject endGameObject);
    void printWrongMove();
    void requestNewGame();
}
