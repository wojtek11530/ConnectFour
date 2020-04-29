package ai;

import game.Board;
import game.Token;

public class GameStateEvaluatorImpl implements GameStateEvaluator {

    private int fourInLineWeight = 1000;
    private int threeInLineWeight = 10;
    private int twoInLineWeight = 1;

    public GameStateEvaluatorImpl() {
    }

    public GameStateEvaluatorImpl(int fourInLineWeight, int threeInLineWeight, int twoInLineWeight) {
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
        evaluation += fourInLineWeight * countInLine(board, token, 4);
        evaluation += threeInLineWeight * countInLine(board, token, 3);
        evaluation += twoInLineWeight * countInLine(board, token, 2);
        return evaluation;
    }

    public int countInLine(Board board, Token token, int lineLength) {
        int count = 0;
        Token[][] boardFields = board.getBoard();
        int[][] directions = {{1, 0}, {1, -1}, {1, 1}, {0, 1}};

        for (int[] d : directions) {
            int rowDirection = d[0];
            int colDirection = d[1];
            for (int rowIndex = 0; rowIndex < Board.ROW_NUMBER; rowIndex++) {
                for (int colIndex = 0; colIndex < Board.COLUMNS_NUMBER; colIndex++) {
                    int lastRowIndex = rowIndex + (lineLength - 1) * rowDirection;
                    int lastColIndex = colIndex + (lineLength - 1) * colDirection;
                    if (0 <= lastRowIndex && lastRowIndex < Board.ROW_NUMBER && 0 <= lastColIndex && lastColIndex < Board.COLUMNS_NUMBER) {
                        boolean theSameTokensInLine = theSameTokensInLine(token, lineLength, boardFields,
                                rowDirection, colDirection, rowIndex, colIndex);
                        if (theSameTokensInLine) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    boolean theSameTokensInLine(Token token, int lineLength, Token[][] boardFields,
                                int rowDirection, int colDirection, int rowIndex, int colIndex) {
        Token currentToken = boardFields[rowIndex][colIndex];
        boolean theSameTokensInLine;
        if (currentToken == token) {
            theSameTokensInLine = true;
            for (int i = 1; i <= (lineLength - 1); i++) {
                Token nextToken = boardFields[rowIndex + i * rowDirection][colIndex + i * colDirection];
                theSameTokensInLine = theSameTokensInLine && currentToken == nextToken;
            }
        } else {
            theSameTokensInLine = false;
        }
        return theSameTokensInLine;
    }

    @Override
    public String toString() {
        return "NumInLineEval" +
                "_4w=" + fourInLineWeight +
                "_3w=" + threeInLineWeight +
                "_2w=" + twoInLineWeight;
    }
}
