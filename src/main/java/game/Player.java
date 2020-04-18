package game;


public abstract class Player {

    protected Token playerToken;
    protected PlayerType type;

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

    public abstract boolean isReal();

    @Override
    public String toString() {
        return "Player with Token: " + playerToken;
    }
}
