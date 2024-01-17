package it.unimol.chess.tests;

import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.BoardUtils;
import it.unimol.chess.engine.board.Move;
import it.unimol.chess.engine.player.MoveTransition;
import it.unimol.chess.engine.player.ai.MiniMax;
import it.unimol.chess.engine.player.ai.MoveStrategy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPatternConMiniMax {
    @Test
    public void testFoolsMate(){
        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(Move.MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("f2"),
                        BoardUtils.getCoordinateAtPosition("f3")));
        assertTrue(t1.getMoveStatus().isDone());

        final MoveTransition t2 = t1.getTransitionBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t1.getTransitionBoard(),BoardUtils.getCoordinateAtPosition("e7"),
                        BoardUtils.getCoordinateAtPosition("e5")));
        assertTrue(t2.getMoveStatus().isDone());

        final MoveTransition t3 = t2.getTransitionBoard().
                currentPlayer().
                makeMove(Move.MoveFactory.createMove(t2.getTransitionBoard(),BoardUtils.getCoordinateAtPosition("g2"),
                        BoardUtils.getCoordinateAtPosition("g4")));

        assertTrue(t3.getMoveStatus().isDone());

        final MoveStrategy strategy = new MiniMax(4);
        final Move aiMove = strategy.execute(t3.getTransitionBoard());

        final Move bestMove = Move.MoveFactory.createMove(t3.getTransitionBoard(),BoardUtils.getCoordinateAtPosition("d8"),
                BoardUtils.getCoordinateAtPosition("h4"));

        assertEquals(aiMove, bestMove);







    }
    @Test
    public void testScaccoMattoDelBarbiere(){
        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(Move.MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e2"),
                        BoardUtils.getCoordinateAtPosition("e4")));
        assertTrue(t1.getMoveStatus().isDone());
        final MoveTransition t2 = t1.getTransitionBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t1.getTransitionBoard(),BoardUtils.getCoordinateAtPosition("e7"),
                        BoardUtils.getCoordinateAtPosition("e5")));
        assertTrue(t2.getMoveStatus().isDone());
        final MoveTransition t3 = t2.getTransitionBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t2.getTransitionBoard(),BoardUtils.getCoordinateAtPosition("f1"),
                        BoardUtils.getCoordinateAtPosition("c4")));
        assertTrue(t3.getMoveStatus().isDone());
        final MoveTransition t4 = t3.getTransitionBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t3.getTransitionBoard(),BoardUtils.getCoordinateAtPosition("b8"),
                        BoardUtils.getCoordinateAtPosition("c6")));
        assertTrue(t4.getMoveStatus().isDone());
        final MoveTransition t5 = t4.getTransitionBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t4.getTransitionBoard(),BoardUtils.getCoordinateAtPosition("d1"),
                        BoardUtils.getCoordinateAtPosition("h5")));
        assertTrue(t5.getMoveStatus().isDone());
        final MoveTransition t6 = t5.getTransitionBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t5.getTransitionBoard(),BoardUtils.getCoordinateAtPosition("g8"),
                        BoardUtils.getCoordinateAtPosition("f6")));
        assertTrue(t6.getMoveStatus().isDone());

        final MoveStrategy strategy = new MiniMax(4);
        final Move aiMove = strategy.execute(t6.getTransitionBoard());

        final Move bestMove = Move.MoveFactory.createMove(t6.getTransitionBoard(),BoardUtils.getCoordinateAtPosition("h5"),
                BoardUtils.getCoordinateAtPosition("f7"));
        assertEquals(aiMove, bestMove);
    }
}
