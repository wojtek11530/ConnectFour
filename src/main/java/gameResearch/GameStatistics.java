package gameResearch;

import game.ConnectFourGame;
import game.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStatistics {

    private ConnectFourGame game;
    private Player startingPlayer;
    private Player playerOne;
    private Player playerTwo;
    private Player winner;
    private Map<Player, Integer> playersMovesCount = new HashMap<>();
    private Map<Player, List<Long>> playersMovesTimes = new HashMap<>();
    private List<Long> allMovesTimes = new ArrayList<>();

    public GameStatistics(ConnectFourGame game) {
        this.game = game;

        playerOne = game.getPlayer1();
        playerTwo = game.getPlayer2();

        playersMovesCount.put(playerOne, 0);
        playersMovesTimes.put(playerOne, new ArrayList<>());
        playersMovesCount.put(playerTwo, 0);
        playersMovesTimes.put(playerTwo, new ArrayList<>());
    }

    public void addPlayerMoveTime(Player player, long time) {
        increasePlayerMoveCount(player);
        List<Long> times = playersMovesTimes.get(player);
        times.add(time);
        allMovesTimes.add(time);
    }

    private void increasePlayerMoveCount(Player player) {
        int count = playersMovesCount.get(player);
        count++;
        playersMovesCount.put(player, count);
    }

    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder("GameStatistics");
        sb.append("\nstartingPlayer: ").append(startingPlayer);
        sb.append("\nwinner: ").append(winner);
        for (Player player : playersMovesCount.keySet()) {
            sb.append("\n").append(player).append(" move count: ").append(playersMovesCount.get(player));
        }
        for (Player player : playersMovesCount.keySet()) {
            sb.append("\n").append(player).append(" move times: \n");
            for (long time : playersMovesTimes.get(player)) {
                sb.append(time).append(" ms, ");
            }
        }
        return sb.toString();
    }

    public void setStartingPlayer(Player startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }


    public Player getStartingPlayer() {
        return startingPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public Map<Player, Integer> getPlayersMovesCount() {
        return playersMovesCount;
    }

    public Map<Player, List<Long>> getPlayersMovesTimes() {
        return playersMovesTimes;
    }

    public List<Long> getAllMovesTimes() {
        return allMovesTimes;
    }

    public ConnectFourGame getGame() {
        return game;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
