package game;

public class Player {

    private Token playerToken;
    private PlayerType type;

    public Player(Token playerToken, PlayerType type) {
        this.playerToken = playerToken;
        this.type = type;
    }

    public Token getPlayerToken() {
        return playerToken;
    }

    public PlayerType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Player with Token: " + playerToken;
    }
}
