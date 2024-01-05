package it.unimol.chess.engine.player.ai;

import it.unimol.chess.engine.board.Board;
import it.unimol.chess.engine.board.Move;

public interface MoveStrategy {
    Move execute(Board board);

}
