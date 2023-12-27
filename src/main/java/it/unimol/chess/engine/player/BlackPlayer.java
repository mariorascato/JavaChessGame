package it.unimol.chess.engine.player;

import com.google.common.collect.ImmutableList;
import it.unimol.chess.engine.Alliance;
import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.Move;
import it.unimol.chess.engine.board.Move.KingSideCastleMove;
import it.unimol.chess.engine.board.Move.QueenSideCastleMove;
import it.unimol.chess.engine.board.Tile;
import it.unimol.chess.engine.pieces.Piece;
import it.unimol.chess.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player {
    public BlackPlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board,blackStandardLegalMoves,whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerlegals,
                                                    final Collection<Move> opponentsLegals) {
        // PARTE NERA DEL KING
        final List<Move> kingCastels = new ArrayList<>();
        if(this.playerKing.isFirstMove() && !this.isInCheck()) {
            if(!this.board.getTile(5).isTileOccupied() && !this.board.getTile(6).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(7);

                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if(Player.calculateAttackOnTile(5,opponentsLegals).isEmpty() &&
                            Player.calculateAttackOnTile(6, opponentsLegals).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                        kingCastels.add(new KingSideCastleMove(this.board,
                                this.playerKing,
                                6,
                                (Rook) rookTile.getPiece(),
                                rookTile.getTileCoordinate(),
                                5 ));
                    }

                }
            }
            if(!this.board.getTile(1).isTileOccupied() &&
                    !this.board.getTile(2).isTileOccupied() &&
                    !this.board.getTile(3).isTileOccupied()) {

                final Tile rookTile = this.board.getTile(0);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    kingCastels.add(new QueenSideCastleMove(this.board,this.playerKing,
                            2,
                            (Rook) rookTile.getPiece()
                            , rookTile.getTileCoordinate(),
                            3));
                }
            }
        }

        return ImmutableList.copyOf(kingCastels);
    }
    }

}
