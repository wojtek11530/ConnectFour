package gameControl;

import game.Player;
import game.Token;

public class EndGameObject {

    private Token[][] board;
    private Player winner;
    private GameMoveObject gameMoveObject;

    public EndGameObject(Token[][] board, Player winner, GameMoveObject gameMoveObject) {
        this.board = board;
        this.winner = winner;
        this.gameMoveObject = gameMoveObject;
    }

    public Player getWinner() {
        return winner;
    }

    public Token[][] getBoard() {
        return board;
    }

    public GameMoveObject getGameMoveObject() {
        return gameMoveObject;
    }
}
