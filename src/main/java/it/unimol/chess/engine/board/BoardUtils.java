package it.unimol.chess.engine.board;

public class BoardUtils {
    private BoardUtils(){
        throw new RuntimeException("non puoi instanziarmi");
    }
    public static boolean isValidTileCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < 64;
    }
}
