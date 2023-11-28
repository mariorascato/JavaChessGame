package it.unimol.chess.engine.pieces;

import com.google.common.collect.ImmutableList;
import it.unimol.chess.engine.Alliance;
import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.BoardUtils;
import it.unimol.chess.engine.board.Move;
import it.unimol.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17,-15,-10,-6,6,10,15,17};
    Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();
        for( final int currentCandidate : CANDIDATE_MOVE_COORDINATES){
            candidateDestinationCoordinate = currentCandidate + this.piecePosition;

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
              final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
              if(!candidateDestinationTile.isTileOccupied()){
                  legalMoves.add(new Move());
              } else
              {
                  final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                  final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                  if(this.pieceAlliance != pieceAlliance){
                      legalMoves.add(new Move());
                  }
              }

            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
