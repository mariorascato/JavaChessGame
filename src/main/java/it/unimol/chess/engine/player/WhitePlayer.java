package it.unimol.chess.engine.player;

import com.google.common.collect.ImmutableList;
import it.unimol.chess.engine.Alliance;
import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.Move;
import it.unimol.chess.engine.board.Tile;
import it.unimol.chess.engine.pieces.Piece;
import it.unimol.chess.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static it.unimol.chess.engine.board.Move.*;

public class WhitePlayer extends Player{
    public WhitePlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
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

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerlegals,
                                                    final Collection<Move> opponentsLegals) {
        // PARTE BIANCA DEL KING
        final List<Move> kingCastels = new ArrayList<>();
        if(this.playerKing.isFirstMove() && !this.isInCheck()) {
            if(!this.board.getTile(61).isTileOccupied() && !this.board.getTile(62).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(63);

                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if(Player.calculateAttackOnTile(61,opponentsLegals).isEmpty() &&
                       Player.calculateAttackOnTile(62, opponentsLegals).isEmpty() &&
                       rookTile.getPiece().getPieceType().isRook()) {
                        kingCastels.add(new KingSideCastleMove(this.board,
                                this.playerKing,
                                62,
                                (Rook) rookTile.getPiece(),
                                rookTile.getTileCoordinate(),
                                61 ));
                    }

                }
            }
            if(!this.board.getTile(59).isTileOccupied() &&
               !this.board.getTile(58).isTileOccupied() &&
               !this.board.getTile(57).isTileOccupied()) {

                final Tile rookTile = this.board.getTile(56);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    kingCastels.add(new QueenSideCastleMove(this.board,
                            this.playerKing,58,
                            (Rook)rookTile.getPiece(),
                            rookTile.getTileCoordinate(),
                            59 ));
                }
            }
        }
        return ImmutableList.copyOf(kingCastels);
    }
}
