package gameResearch;

import ai.*;
import game.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Simulations {

    public static void run() {

        int fourInLineWeightOne = 1000;
        int threeInLineWeightOne = 100;
        int twoInLineWeightOne = 10;
        GameStateEvaluator evaluatorOne = new AllLinesEvaluator(fourInLineWeightOne, threeInLineWeightOne, twoInLineWeightOne);

        int fourInLineWeightTwo = 1000;
        int threeInLineWeightTwo = 100;
        int twoInLineWeightTwo = 10;
        GameStateEvaluator evaluatorTwo = new ThreateningLinesEvaluator(fourInLineWeightTwo, threeInLineWeightTwo, twoInLineWeightTwo);

        String aiAlgorithmTypeOne = "Alphabeta";
        String aiAlgorithmTypeTwo = "Alphabeta";
        int maxDepth = 5;

        try {
            performSimulations(evaluatorOne, evaluatorTwo, aiAlgorithmTypeOne, aiAlgorithmTypeTwo, maxDepth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runSingle() {

        GameResearchController gameResearchController = new GameResearchController();
        String aiAlgorithmType = "Minmax";

        int fourInLineWeight = 1000;
        int threeInLineWeight = 100;
        int twoInLineWeight = 10;
        GameStateEvaluator evaluatorOne = new AllLinesEvaluator(fourInLineWeight, threeInLineWeight, twoInLineWeight);
        GameStateEvaluator evaluatorTwo = new AllLinesEvaluator(fourInLineWeight, threeInLineWeight, twoInLineWeight);

        int ai_one_depth = 4;
        int ai_two_depth = 4;

        AI playerOneAi;
        AI playerTwoAi;
        if (aiAlgorithmType.equals("Alphabeta")) {
            playerOneAi = new AlphaBetaAI(ai_one_depth, evaluatorOne);
            playerTwoAi = new AlphaBetaAI(ai_two_depth, evaluatorTwo);
        } else {
            playerOneAi = new MinMaxAI(ai_one_depth, evaluatorOne);
            playerTwoAi = new MinMaxAI(ai_two_depth, evaluatorTwo);
        }

        GameStatistics statistics = gameResearchController.startGame(playerOneAi, playerTwoAi);
        System.out.println(statistics);
        System.out.println(statistics.getGame().getBoard());
    }


    private static void performSimulations(GameStateEvaluator evaluatorOne, GameStateEvaluator evaluatorTwo,
                                           String aiPlayerOneAlgorithmType,
                                           String aiPLayerTwoAlgorithmType, int maxDepth) throws IOException {

        GameResearchController gameResearchController = new GameResearchController();
        String fileName = "Results_" + aiPlayerOneAlgorithmType + "_" + evaluatorOne + "_" + aiPLayerTwoAlgorithmType +
                "_" + evaluatorTwo + "_" + maxDepth + "_" + ".csv";

        FileWriter csvWriter = new FileWriter(fileName);
        csvWriter.append("players_one_ai");
        csvWriter.append(",");
        csvWriter.append("players_two_ai");
        csvWriter.append(",");
        csvWriter.append("starting_player");
        csvWriter.append(",");
        csvWriter.append("winning");
        csvWriter.append(",");
        csvWriter.append("winner_or_1st_player_moves_count");
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
                    System.out.print(" " + aiPlayerOneAlgorithmType + "_" + aiOneDepth + "_" + aiPLayerTwoAlgorithmType + "_" + aiTwoDepth);
                    AI playerOneAi;
                    AI playerTwoAi;
                    switch (aiPlayerOneAlgorithmType) {
                        case "Alphabeta":
                            playerOneAi = new AlphaBetaAI(aiOneDepth, evaluatorOne);
                            break;
                        case "Alphabeta Middle":
                            playerOneAi = new AlphaBetaMiddleColStartAI(aiOneDepth, evaluatorOne);
                            break;
                        case "Minmax":
                            playerOneAi = new MinMaxAI(aiOneDepth, evaluatorOne);
                            break;
                        default:
                            playerOneAi = new MinMaxMiddleColStartAI(aiOneDepth, evaluatorOne);
                            break;
                    }

                    switch (aiPLayerTwoAlgorithmType) {
                        case "Alphabeta":
                            playerTwoAi = new AlphaBetaAI(aiTwoDepth, evaluatorTwo);
                            break;
                        case "Alphabeta Middle":
                            playerTwoAi = new AlphaBetaMiddleColStartAI(aiTwoDepth, evaluatorTwo);
                            break;
                        case "Minmax":
                            playerTwoAi = new MinMaxAI(aiTwoDepth, evaluatorTwo);
                            break;
                        default:
                            playerTwoAi = new MinMaxMiddleColStartAI(aiTwoDepth, evaluatorTwo);
                            break;
                    }

                    GameStatistics statistics = gameResearchController.startGame(playerOneAi, playerTwoAi);
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

                    List<Double> times = statistics.getAllMovesTimes()
                            .stream().mapToDouble(val -> (double) val)
                            .boxed()
                            .collect(Collectors.toList());
                    double avgMoveTime = StatisticProperties.average(times);

                    List<Double> p1Times = statistics.getPlayersMovesTimes().get(playerOne)
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
