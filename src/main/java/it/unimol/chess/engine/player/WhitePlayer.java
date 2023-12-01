package it.unimol.chess.engine.player;

import it.unimol.chess.engine.Alliance;
import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.Move;
import it.unimol.chess.engine.pieces.Piece;

import java.util.Collection;

public class WhitePlayer extends Player{
    public WhitePlayer(Board board,
                       Collection<Move> whiteStandardLegalMoves,
                       Collection<Move> blackStandardLegalMoves) {
        super(board,whiteStandardLegalMoves,blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }
}
