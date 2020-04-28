package game;

public class HumanPlayer extends Player {


    public HumanPlayer(Token playerToken, PlayerType type) {
        super(playerToken, type);
    }

    @Override
    public boolean isReal() {
        return true;
    }
}
