package it.unimol.chess.tests;
import it.unimol.chess.engine.Alliance;
import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.pieces.King;
import it.unimol.chess.engine.pieces.Pawn;
import it.unimol.chess.engine.pieces.Rook;
import it.unimol.chess.engine.player.ai.MiniMax;
import it.unimol.chess.engine.player.ai.MoveStrategy;
import org.junit.Test;

import static it.unimol.chess.engine.board.Board.*;
import static org.junit.Assert.assertEquals;

public class TestMiniMax {
    @Test
    public void testOpeningDepth1() {
        final Board board = createStandardBoard();
        final MoveStrategy minMax = new MiniMax(1);
        minMax.execute(board);
        final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
        assertEquals(numBoardsEvaluated, 20L);
    }
    @Test
    public void testPosition3Depth1() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 19));
        builder.setPiece(new Rook(Alliance.BLACK, 31));
        builder.setPiece(new Pawn(Alliance.BLACK, 37));
        builder.setPiece(new King(Alliance.BLACK, 39, false));
        // White Layout
        builder.setPiece(new King(Alliance.WHITE, 24, false));
        builder.setPiece(new Pawn(Alliance.WHITE, 25));
        builder.setPiece(new Rook(Alliance.WHITE, 33));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        // Set the current player
        builder.setMoveMaker(Alliance.WHITE);
        final Board board = builder.build();
        final MoveStrategy minMax = new MiniMax(1);
        minMax.execute(board);
        final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
        assertEquals(numBoardsEvaluated, 14);
    }
}
