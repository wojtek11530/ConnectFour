package userInreface;

import ai.AI;
import ai.MinMaxAI;
import game.*;
import gameControl.EndGameObject;
import gameControl.GameController;
import gameControl.GameMoveObject;
import gameControl.GameStartObject;

import java.util.Scanner;

public class ConsoleUserInterfaceImpl implements UserInterface {

    private GameController gameController;
    private Scanner scanner = new Scanner(System.in);


    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void printStartGame(GameStartObject gameStartObject) {
        printCurrentPlayer(gameStartObject.getNextPlayer());
        printBoard(gameStartObject.getBoard().getBoard());
        getColumnChoice();
    }

    public void printGameAfterMove(GameMoveObject gameMoveObject, Player nextPlayer) {
        printCurrentPlayer(nextPlayer);
        printBoard(gameMoveObject.getBoard());
        getColumnChoice();
    }

    public void printEndedGame(EndGameObject endGameObject) {
        Player winner = endGameObject.getWinner();
        if (winner != null) {
            System.out.println("WINNER: " + winner);
        } else {
            System.out.println("TIE");
        }
        System.out.println();
        printBoard(endGameObject.getBoard());
    }

    public void printWrongMove() {
        System.out.println("Wrong input");
        getColumnChoice();
    }

    @Override
    public void requestNewGame() {
        HumanPlayer playerOne = new HumanPlayer(Token.YELLOW, PlayerType.HUMAN);

        ComputerPlayer playerTwo = new ComputerPlayer(Token.RED, PlayerType.AI);
        AI ai = new MinMaxAI();
        playerTwo.setAi(ai);

        gameController.setNewGame(new ConnectFourGame(playerOne, playerTwo));
    }

    private void printCurrentPlayer(Player player) {
        System.out.println("Current " + player + "\n");
    }

    private void printBoard(Token[][] board) {
        for (Token[] row : board) {
            for (Token token : row) {
                System.out.print(token.getSign() + ' ');
            }
            System.out.println();
        }
        System.out.println();
    }


    public void getColumnChoice() {
        System.out.print("Choose column: ");
        int columnNumber = scanner.nextInt();
        gameController.playNextMove(columnNumber - 1);
    }
}
