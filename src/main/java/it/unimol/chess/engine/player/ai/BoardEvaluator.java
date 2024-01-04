package it.unimol.chess.engine.player.ai;

import it.unimol.chess.engine.board.Board;

public interface BoardEvaluator {
    int evaluate(Board board, int depth);
}
