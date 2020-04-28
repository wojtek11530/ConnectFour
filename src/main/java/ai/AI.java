package ai;

import game.ConnectFourGame;

public interface AI {

    void setGame(ConnectFourGame game);
    int determineNextMove();
}
