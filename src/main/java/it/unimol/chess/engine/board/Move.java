package it.unimol.chess.engine.board;

import it.unimol.chess.engine.pieces.Piece;

import static it.unimol.chess.engine.board.Board.*;

public abstract  class Move {

    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    private Move(final Board board, final Piece movedPiece,final int destinationCoordinate){
        this.board= board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate(){
        return this.destinationCoordinate;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public abstract Board execute() ;

    public static final class MajorMove extends Move {

        public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                //TODO hashcode e equals per pieces
                if(!this.movedPiece.equals(piece)){
                   builder.setPiece(piece);
                }
            }
            for(final  Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            //muovere i pezzi mossi;
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());

            return builder.build();
        }
    }
    public static final class attackMove extends Move {

        final Piece attackedPiece;
       public attackMove(final Board board,
                   final Piece movedPiece,
                   final int destinationCoordinate,
                   final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        @Override
        public Board execute() {
            return null;
        }
    }
}
