package gameResearch;

import ai.*;
import game.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Simulations {

    public static void run() {

        int fourInLineWeight = 1000;
        int threeInLineWeight = 100;
        int twoInLineWeight = 10;
        GameStateEvaluator evaluator = new GameStateEvaluatorImpl(fourInLineWeight, threeInLineWeight, twoInLineWeight);

        String aiAlgorithmType = "Alphabeta";
        int maxDepth = 5;

        try {
            performSimulations(evaluator, aiAlgorithmType, aiAlgorithmType, maxDepth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runSingle() {

        GameResearchController gameResearchController = new GameResearchController();
        String aiAlgorithmType = "Minmax";

        int ai_one_depth = 4;
        int ai_two_depth = 4;

        AI playerOneAi;
        AI playerTwoAi;
        if (aiAlgorithmType.equals("Alphabeta")) {
            playerOneAi = new AlphaBetaAI(ai_one_depth);
            playerTwoAi = new AlphaBetaAI(ai_two_depth);
        } else {
            playerOneAi = new MinMaxAI(ai_one_depth);
            playerTwoAi = new MinMaxAI(ai_two_depth);
        }

        int fourInLineWeight = 1000;
        int threeInLineWeight = 100;
        int twoInLineWeight = 10;
        GameStateEvaluator evaluator = new GameStateEvaluatorImpl(fourInLineWeight, threeInLineWeight, twoInLineWeight);

        GameStatistics statistics = gameResearchController.startGame(playerOneAi, playerTwoAi, evaluator);
        System.out.println(statistics);
        System.out.println(statistics.getGame().getBoard());
    }


    private static void performSimulations(GameStateEvaluator evaluator, String aiPlayerOneAlgorithmType,
                                           String aiPLayerTwoAlgorithmType, int maxDepth) throws IOException {
        GameResearchController gameResearchController = new GameResearchController();

        String fileName = "Results_" + aiPlayerOneAlgorithmType + "_"  + aiPLayerTwoAlgorithmType + "_" + maxDepth + "_" + evaluator + ".csv";

        FileWriter csvWriter = new FileWriter(fileName);
        csvWriter.append("players_one_ai");
        csvWriter.append(",");
        csvWriter.append("players_two_ai");
        csvWriter.append(",");
        csvWriter.append("starting_player");
        csvWriter.append(",");
        csvWriter.append("winning");
        csvWriter.append(",");
        csvWriter.append("winner_1st_pl__moves_count");
        csvWriter.append(",");
        csvWriter.append("avg_move_time");
        csvWriter.append(",");
        csvWriter.append("p1_avg_move_time");
        csvWriter.append(",");
        csvWriter.append("p2_avg_move_time");
        csvWriter.append("\n");

        for (int aiOneDepth = 1; aiOneDepth <= maxDepth; aiOneDepth++) {
            for (int aiTwoDepth = 1; aiTwoDepth <= maxDepth; aiTwoDepth++) {
                for (int i = 0; i < 10; i++) {
                    System.out.print(" " + aiPlayerOneAlgorithmType + "_" + aiOneDepth +  "_" + aiPLayerTwoAlgorithmType + "_" + aiTwoDepth);
                    AI playerOneAi;
                    AI playerTwoAi;
                    if (aiPlayerOneAlgorithmType.equals("Alphabeta")) {
                        playerOneAi = new AlphaBetaAI(aiOneDepth);
                    } else {
                        playerOneAi = new MinMaxAI(aiOneDepth);
                    }

                    if (aiPLayerTwoAlgorithmType.equals("Alphabeta")) {
                        playerTwoAi = new AlphaBetaAI(aiTwoDepth);
                    } else {
                        playerTwoAi = new MinMaxAI(aiTwoDepth);
                    }

                    GameStatistics statistics = gameResearchController.startGame(playerOneAi, playerTwoAi, evaluator);

                    Player playerOne = statistics.getPlayerOne();
                    Player playerTwo = statistics.getPlayerTwo();
                    Player startingPlayer = statistics.getStartingPlayer();
                    Player winner = statistics.getWinner();

                    String startingPlayerLabel = startingPlayer == playerOne ? "P1" : "P2";
                    String winnerLabel = winner == playerOne ? "P1" : winner == playerTwo ? "P2" : "T";

                    int movesCount;
                    if (winner == null) {
                        movesCount = statistics.getPlayersMovesCount().get(startingPlayer);
                    } else {
                        movesCount = statistics.getPlayersMovesCount().get(winner);
                    }

                    List<Double> times =  statistics.getAllMovesTimes()
                            .stream().mapToDouble(val -> (double) val)
                            .boxed()
                            .collect(Collectors.toList());
                    double avgMoveTime = StatisticProperties.average(times);

                    List<Double> p1Times =  statistics.getPlayersMovesTimes().get(playerOne)
                            .stream().mapToDouble(val -> (double) val)
                            .boxed()
                            .collect(Collectors.toList());
                    double p1AvgMoveTime = StatisticProperties.average(p1Times);

                    List<Double> p2Times = statistics.getPlayersMovesTimes().get(playerTwo)
                            .stream().mapToDouble(val -> (double) val)
                            .boxed()
                            .collect(Collectors.toList());
                    double p2AvgMoveTime = StatisticProperties.average(p2Times);

                    csvWriter.append(playerOneAi.toString())
                            .append(",").append(playerTwoAi.toString())
                            .append(",").append(startingPlayerLabel)
                            .append(",").append(winnerLabel)
                            .append(",").append(String.valueOf(movesCount))
                            .append(",").append(String.valueOf(avgMoveTime))
                            .append(",").append(String.valueOf(p1AvgMoveTime))
                            .append(",").append(String.valueOf(p2AvgMoveTime))
                            .append("\n");

                }
                System.out.println();
            }
            System.out.println();
        }
        csvWriter.flush();
        csvWriter.close();
    }

}
