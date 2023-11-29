package it.unimol.chess.engine.pieces;

import com.google.common.collect.ImmutableList;
import it.unimol.chess.engine.Alliance;
import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.BoardUtils;
import it.unimol.chess.engine.board.Move;
import it.unimol.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17,-15,-10,-6,6,10,15,17};
    Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for( final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){
            final int candidateDestinationCoordinate = currentCandidateOffset + this.piecePosition;

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){

                if((isFirstColumnExclusion(this.piecePosition,currentCandidateOffset))||
                        (isSecondColumnExclusion(this.piecePosition,currentCandidateOffset))||
                        (isSeventhColumnExclusion(this.piecePosition,currentCandidateOffset))||
                        (isEighthColumnExclusion(this.piecePosition,currentCandidateOffset))){
                    continue;
                }


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
    private static boolean isFirstColumnExclusion(final int currentPosition,final int candidateOffset){
    return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17)||(candidateOffset == -10)||(candidateOffset == 6)||
            (candidateOffset == 15));
    }
    private static boolean isSecondColumnExclusion(final int currentPosition,final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10)||(candidateOffset == 6));
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition,final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6)|| (candidateOffset == 10));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == 17 ) || (candidateOffset == 10)
                ||(candidateOffset == -6)||(candidateOffset == -15));
    }
}
