package it.unimol.chess.engine.player;

import it.unimol.chess.engine.Alliance;
import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.Move;
import it.unimol.chess.engine.pieces.King;
import it.unimol.chess.engine.pieces.Piece;

import java.util.Collection;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    Player(final Board board,
           final Collection<Move> legalMoves,
           final Collection<Move> opponentMoves){
        
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    private King establishKing() {
        for (final Piece piece : getActivePieces()){
            if(piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("Non dovresti essere qui! Non è una Board valida");
    }
    public boolean isMoveLegal(final Move move){
        return this.legalMoves.contains(move);
    }
    //TO DO (implementare tutti e 5 i metodi)
    public boolean isInCheck(){
        return false;
    }
    public boolean isInCheckMate(){
        return false;
    }
    public boolean isInStaleMate(){
        return false;
    }
    public boolean isCastled(){
        return false;
    }
    public MoveTransition makeMove(final Move move){
        return null;
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
}
