package it.unimol.chess.engine.pieces;

import it.unimol.chess.engine.Alliance;
import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    Piece(final int piecePosition,final Alliance pieceAlliance){
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        //TODO altro lavoro da fare
        this.isFirstMove = false;
    }
    public abstract Collection<Move> calculateLegalMoves(final Board board);
    public int getPiecePosition(){
        return this.piecePosition;
    }
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
    public boolean isFirstMove(){
        return this.isFirstMove;
    }
    public enum PieceType {
        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");

       private  String pieceName;
        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }
        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}
