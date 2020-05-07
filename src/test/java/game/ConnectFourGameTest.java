package game;

import gameControl.GameMoveObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectFourGameTest {

    ConnectFourGame game;
    Board board;

    @BeforeEach
    void setUp() {
        game = new ConnectFourGame();
        game.initGame();
        board = game.getBoard();
    }

    @Test
    void putCurrentPlayerTokenWhenThereIsPlace() {

        // given
        game.setCurrentPlayer(game.getPlayer1()); // player with YELLOW token

        // O O O O O O O
        // O O O O O O O
        // R R O O O R O
        // R Y R R Y Y O
        // Y R Y Y R Y O
        // Y Y Y R Y R R
        Token[][] boardField = new Token[][]
                {
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.YELLOW, Token.EMPTY, Token.EMPTY},
                        {Token.RED, Token.RED, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.RED, Token.EMPTY},
                        {Token.RED, Token.YELLOW, Token.RED, Token.RED, Token.YELLOW, Token.YELLOW, Token.EMPTY},
                        {Token.YELLOW, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED, Token.YELLOW, Token.EMPTY},
                        {Token.YELLOW, Token.YELLOW, Token.YELLOW, Token.RED, Token.YELLOW, Token.RED, Token.RED}
                };

        board.setBoard(boardField);

        // when
        GameMoveObject gmo = game.putCurrentPlayerToken(0);

        // then
        assertTrue(gmo.isMoveMade());
        assertEquals(Token.YELLOW, board.getBoard()[gmo.getLatMoveRow()][gmo.getLastMoveCol()]);
    }

    @Test
    void putCurrentPlayerTokenToFullColumn() {

        // given
        game.setCurrentPlayer(game.getPlayer1()); // player with YELLOW token

        // Y O O O O O O
        // Y O O O O O O
        // R R O O O R O
        // R Y R R Y Y O
        // Y R Y Y R Y O
        // Y Y Y R Y R R
        Token[][] boardField = new Token[][]
                {
                        {Token.YELLOW, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.YELLOW, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.YELLOW, Token.EMPTY, Token.EMPTY},
                        {Token.RED, Token.RED, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.RED, Token.EMPTY},
                        {Token.RED, Token.YELLOW, Token.RED, Token.RED, Token.YELLOW, Token.YELLOW, Token.EMPTY},
                        {Token.YELLOW, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED, Token.YELLOW, Token.EMPTY},
                        {Token.YELLOW, Token.YELLOW, Token.YELLOW, Token.RED, Token.YELLOW, Token.RED, Token.RED}
                };

        board.setBoard(boardField);

        // when
        GameMoveObject gmo = game.putCurrentPlayerToken(0);

        // then
        assertFalse(gmo.isMoveMade());
    }


    @Test
    void testBoardFullWhenFull() {
        // given
        // Y R Y Y R R R
        // Y Y R R Y Y R
        // R R Y R R R Y
        // R Y R R Y Y R
        // Y R Y Y R Y Y
        // Y Y Y R Y R R
        Token[][] boardField = new Token[][]
                {
                        {Token.YELLOW, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED, Token.RED, Token.RED},
                        {Token.YELLOW, Token.YELLOW, Token.RED, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED},
                        {Token.RED, Token.RED, Token.YELLOW, Token.RED, Token.RED, Token.RED, Token.YELLOW},
                        {Token.RED, Token.YELLOW, Token.RED, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED},
                        {Token.YELLOW, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED, Token.YELLOW, Token.YELLOW},
                        {Token.YELLOW, Token.YELLOW, Token.YELLOW, Token.RED, Token.YELLOW, Token.RED, Token.RED}
                };

        board.setBoard(boardField);

        // when
        boolean result = game.boardFull();

        // then
        assertTrue(result);
    }

    @Test
    void testBoardFullWhenNotFull() {
        //given
        // O R Y Y R R R
        // Y Y R R Y Y R
        // R R Y R R R Y
        // R Y R R Y Y R
        // Y R Y Y R Y Y
        // Y Y Y R Y R R
        Token[][] boardField = new Token[][]
                {
                        {Token.EMPTY, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED, Token.RED, Token.RED},
                        {Token.YELLOW, Token.YELLOW, Token.RED, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED},
                        {Token.RED, Token.RED, Token.YELLOW, Token.RED, Token.RED, Token.RED, Token.YELLOW},
                        {Token.RED, Token.YELLOW, Token.RED, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED},
                        {Token.YELLOW, Token.RED, Token.YELLOW, Token.YELLOW, Token.RED, Token.YELLOW, Token.YELLOW},
                        {Token.YELLOW, Token.YELLOW, Token.YELLOW, Token.RED, Token.YELLOW, Token.RED, Token.RED}
                };

        board.setBoard(boardField);

        // when
        boolean result = game.boardFull();

        //then
        assertFalse(result);
    }


    @Test
    void winningColumn() {
        //given
        // O O O O O O O
        // O O O O O O O
        // Y O O O O O O
        // Y R O O O O O
        // Y R O O O O O
        // Y R O O O O O
        Token[][] boardField = new Token[][]
                {
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.YELLOW, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.YELLOW, Token.RED, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.YELLOW, Token.RED, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.YELLOW, Token.RED, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                };
        board.setBoard(boardField);

        // when
        boolean isEnded = game.isEnded();
        Player winner = game.getWinner();

        // then
        assertTrue(isEnded);
        assertEquals(Token.YELLOW, winner.getPlayerToken());
    }


    @Test
    void winningRow() {
        // O O O O O O O
        // O O O O O O O
        // O O O O O O O
        // O O O O O O O
        // O O O O O O O
        // Y Y Y Y R R R
        Token[][] boardField = new Token[][]
                {
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.YELLOW, Token.YELLOW, Token.YELLOW, Token.YELLOW, Token.RED, Token.RED, Token.RED},
                };
        board.setBoard(boardField);

        // when
        boolean isEnded = game.isEnded();
        Player winner = game.getWinner();

        // then
        assertTrue(isEnded);
        assertEquals(Token.YELLOW, winner.getPlayerToken());
    }


    @Test
    void winningDiagonal() {
        // O O O O O O O
        // O O O O O O O
        // O O O Y O O O
        // O O Y R O O O
        // O Y R R O O O
        // Y R R Y O O O
        Token[][] boardField = new Token[][]
                {
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.YELLOW, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.EMPTY, Token.YELLOW, Token.RED, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.EMPTY, Token.YELLOW, Token.RED, Token.RED, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                        {Token.YELLOW, Token.RED, Token.RED, Token.YELLOW, Token.EMPTY, Token.EMPTY, Token.EMPTY},
                };
        board.setBoard(boardField);

        // when
        boolean isEnded = game.isEnded();
        Player winner = game.getWinner();

        // then
        assertTrue(isEnded);
        assertEquals(Token.YELLOW, winner.getPlayerToken());
    }


//    @Test
//    void evaluateState() {
//        // O O O O O O O
//        // O O O O O O O
//        // O O O Y O O O
//        // O O Y R O O O
//        // O Y R R O O O
//        // Y R R Y O O O
//        Token[][] boardField = new Token[][]
//                {
//                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
//                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.EMPTY},
//                        {Token.EMPTY, Token.EMPTY, Token.EMPTY, Token.YELLOW, Token.EMPTY, Token.EMPTY, Token.EMPTY},
//                        {Token.EMPTY, Token.EMPTY, Token.YELLOW, Token.RED, Token.EMPTY, Token.EMPTY, Token.EMPTY},
//                        {Token.EMPTY, Token.YELLOW, Token.RED, Token.RED, Token.EMPTY, Token.EMPTY, Token.EMPTY},
//                        {Token.YELLOW, Token.RED, Token.RED, Token.YELLOW, Token.EMPTY, Token.EMPTY, Token.EMPTY},
//                };
//        board.setBoard(boardField);
//
//        // when
//        int evaluation = game.evaluateState();
//
//        // then
//        int expected = 7 * 1 + 1 * 10 - 1000 - 2 * 10 - 3 * 1;
//        assertEquals(expected, evaluation);
//    }
}