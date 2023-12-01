package it.unimol.chess.engine.pieces;

import com.google.common.collect.ImmutableList;
import it.unimol.chess.engine.Alliance;
import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.BoardUtils;
import it.unimol.chess.engine.board.Move;
import it.unimol.chess.engine.board.Tile;
import static  it.unimol.chess.engine.board.Move.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9,-7,7,9};

    public Bishop( final Alliance pieceAlliance, final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {


        final List<Move> legalMoves = new ArrayList<>();
        for(final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES ){
            int candidateDestinationCoordinate = this.piecePosition;
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                if(isFirstcolumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)||
                isEighthcolumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)){
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;

                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
                    } else
                    {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if(this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new attackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                        }
                        break;
                    }

                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public String toString(){
        return PieceType.BISHOP.toString();
    }
    private static boolean isFirstcolumnExclusion(final int currentPosition , final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
    }
    private static boolean isEighthcolumnExclusion(final int currentPosition , final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
    }
}
