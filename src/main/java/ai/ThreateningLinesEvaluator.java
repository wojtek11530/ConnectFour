package ai;

import game.Board;
import game.Token;

public class ThreateningLinesEvaluator implements GameStateEvaluator {
    private int fourInLineWeight = 1000;
    private int threeInLineWeight = 10;
    private int twoInLineWeight = 1;

    public ThreateningLinesEvaluator() {
    }

    public ThreateningLinesEvaluator(int fourInLineWeight, int threeInLineWeight, int twoInLineWeight) {
        this.fourInLineWeight = fourInLineWeight;
        this.threeInLineWeight = threeInLineWeight;
        this.twoInLineWeight = twoInLineWeight;
    }

    @Override
    public int evaluateGame(Board board) {
        int yellowEvaluation = getEvaluationForTokenColor(Token.YELLOW, board);
        int redEvaluation = getEvaluationForTokenColor(Token.RED, board);
        return redEvaluation - yellowEvaluation;
    }

    private int getEvaluationForTokenColor(Token token, Board board) {
        int evaluation = 0;
        evaluation += assesPossibleLines(board, token);
        return evaluation;
    }

    public int assesPossibleLines(Board board, Token token) {
        int result = 0;
        Token[][] boardFields = board.getBoard();
        int[][] directions = {{1, 0}, {1, -1}, {1, 1}, {0, 1}};
        int lineLength = 4;

        for (int[] d : directions) {
            int rowDirection = d[0];
            int colDirection = d[1];
            for (int rowIndex = 0; rowIndex < Board.ROW_NUMBER; rowIndex++) {
                for (int colIndex = 0; colIndex < Board.COLUMNS_NUMBER; colIndex++) {
                    int lastRowIndex = rowIndex + (lineLength - 1) * rowDirection;
                    int lastColIndex = colIndex + (lineLength - 1) * colDirection;
                    if (0 <= lastRowIndex && lastRowIndex < Board.ROW_NUMBER && 0 <= lastColIndex && lastColIndex < Board.COLUMNS_NUMBER) {
                        int count = 0;
                        boolean lineDiscarded = false;
                        for (int i = 0; i <= (lineLength - 1); i++) {
                            if (!lineDiscarded) {
                                Token nextToken = boardFields[rowIndex + i * rowDirection][colIndex + i * colDirection];
                                if (nextToken == token) {
                                    count++;
                                } else if (nextToken != Token.EMPTY) {
                                    lineDiscarded = true;
                                }
                            }
                        }
                        if (!lineDiscarded) {
                            if (count == 2) {
                                result += twoInLineWeight;
                            } else if (count == 3) {
                                result += threeInLineWeight;
                            } else  if (count == 4) {
                                result += fourInLineWeight;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }



    @Override
    public String toString() {
        return "ThreateningLineEval" +
                "_4w=" + fourInLineWeight +
                "_3w=" + threeInLineWeight +
                "_2w=" + twoInLineWeight;
    }
}
