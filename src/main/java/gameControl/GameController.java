package gameControl;

import game.ComputerPlayer;
import game.ConnectFourGame;
import userInreface.UserInterface;

public class GameController {

    ConnectFourGame game;
    UserInterface userInterface;

    public GameController(UserInterface userInterface) {
        this.userInterface = userInterface;
        this.userInterface.setGameController(this);
    }

    public void settleGame() {
        userInterface.requestNewGame();
    }

    public void setNewGame(ConnectFourGame connectFourGame) {
        game = connectFourGame;

        if (!game.getPlayer1().isReal()) {
            ComputerPlayer computerPlayer = (ComputerPlayer) game.getPlayer1();
            computerPlayer.assignToGame(game);
        }
        if (!game.getPlayer2().isReal()) {
            ComputerPlayer computerPlayer = (ComputerPlayer) game.getPlayer2();
            computerPlayer.assignToGame(game);
        }
        startGame();
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
            }
        } else if (!game.getCurrentPlayer().isReal()) {
            ComputerPlayer currentAIPlayer = (ComputerPlayer) game.getCurrentPlayer();
            performAIMove(currentAIPlayer);
        } else {
            userInterface.printWrongMove();
        }
    }

    public void performAIMove(ComputerPlayer currentAIPlayer) {
        int aiNextMove = currentAIPlayer.getAIMove();
        playNextMove(aiNextMove);
    }

}
