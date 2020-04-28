package game;

import ai.AI;

public class ComputerPlayer extends Player {

    private AI ai;

    public ComputerPlayer(Token playerToken, PlayerType type) {
        super(playerToken, type);
    }

    public ComputerPlayer(Token playerToken, PlayerType type, AI ai) {
        super(playerToken, type);
        this.ai = ai;
    }

    public AI getAi() {
        return ai;
    }

    public int getAIMove() {
        return ai.determineNextMove();
    }

    public void assignToGame(ConnectFourGame game) {
        ai.setGame(game);
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    @Override
    public boolean isReal() {
        return false;
    }
}
