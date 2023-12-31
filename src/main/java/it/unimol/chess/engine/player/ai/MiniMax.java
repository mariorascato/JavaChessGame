package it.unimol.chess.engine.player.ai;

import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.Move;
import it.unimol.chess.engine.player.MoveTransition;

public class MiniMax implements MoveStrategy{
    private final BoardEvaluator boardEvaluator;
    private long boardsEvaluated;
    private final int searchDepth;
    public MiniMax(final int searchDepth) {
        this.boardEvaluator = new StandardBoardEvaluator();
        this.searchDepth = searchDepth;
        this.boardsEvaluated = 0;
    }

    @Override
    public  String toString() {
        return "MiniMax";
    }
    @Override
    public Move execute(Board board) {

        final long  startTime = System.currentTimeMillis();
        Move bestMove = null;

        int highestSeenValue = Integer.MIN_VALUE;
        int lowestSeenValue = Integer.MAX_VALUE;
        int currentValue;

        System.out.println(board.currentPlayer() + "THINKING with depth = " + this.searchDepth);

        int numMoves = board.currentPlayer().getLegalMoves().size();

        for(final Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()) {
                currentValue = board.currentPlayer().getAlliance().isWhite() ?
                        min(moveTransition.getTransitionBoard(), this.searchDepth - 1 ) :
                        max(moveTransition.getTransitionBoard(), this.searchDepth - 1);

                if(board.currentPlayer().getAlliance().isWhite() && currentValue >= highestSeenValue) {
                    highestSeenValue = currentValue;
                    bestMove = move;
                } else if (board.currentPlayer().getAlliance().isBlack() && currentValue <= lowestSeenValue) {
                    lowestSeenValue = currentValue;
                    bestMove = move;
                }
            }
        }
        final long executionTime = System.currentTimeMillis() - startTime;
        return bestMove;

    }
    public int min(final Board board,
                   final int depth) {
    if(depth == 0) {
        this.boardsEvaluated++;
        return this.boardEvaluator.evaluate(board, depth);
    }
    if(isEndGameScenario(board)) {
        return this.boardEvaluator.evaluate(board,depth);
    }
    int lowestSeenValue = Integer.MAX_VALUE;
    for(final  Move move : board.currentPlayer().getLegalMoves()) {
        final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
        if(moveTransition.getMoveStatus().isDone()) {
            final int currentValue = max(moveTransition.getTransitionBoard(), depth -1);
            if(currentValue <= lowestSeenValue) {
                lowestSeenValue = currentValue;
            }
        }
    }
    return lowestSeenValue;
    }
    private static boolean isEndGameScenario(final Board board){
        return board.currentPlayer().isInCheckMate() ||
               board.currentPlayer().isInStaleMate();
    }
    public int max(final Board board,
                   final int depth) {
        if(depth == 0) {
            this.boardsEvaluated++;
            return this.boardEvaluator.evaluate(board, depth);
        }
        if(isEndGameScenario(board)) {
            return this.boardEvaluator.evaluate(board,depth);
        }
        int highestSeenValue = Integer.MIN_VALUE;
        for(final  Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()) {
                final int currentValue = min(moveTransition.getTransitionBoard(), depth -1);
                if(currentValue >= highestSeenValue) {
                    highestSeenValue = currentValue;
                }
            }
        }
        return highestSeenValue;
    }
    public long getNumBoardsEvaluated() {
        return this.boardsEvaluated;
    }
}
