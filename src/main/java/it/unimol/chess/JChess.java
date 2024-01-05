package it.unimol.chess;

import it.unimol.chess.engine.board.Board;
import it.unimol.chess.gui.Table;

public class JChess {
    public static void main(String[] args){

        Board board = Board.createStandardBoard();
        System.out.println(board);

        Table.get().show();
    }
}
