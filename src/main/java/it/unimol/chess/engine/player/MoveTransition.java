package it.unimol.chess.engine.player;

import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.Move;

public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board transitionBoard,
                          final Move move,
                          final MoveStatus moveStatus){
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }
    public MoveStatus getMoveStatus(){
        return this.moveStatus;
    }
    public Board getTransitionBoard() {
        return this.transitionBoard;
    }

    public Move getMove() {
        return this.move;
    }
}
