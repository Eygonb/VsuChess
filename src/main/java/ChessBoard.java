import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private Map<String, Cell> cellMap; // Key looks like "a1", "h8"
    private final int ChessBoardSize = 8;

    public ChessBoard() {
        cellMap = new HashMap<String, Cell>();

        //create all chess
        createChessMen(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}, 2, ChessManType.PAWN);
        createChessMen(new char[]{'a', 'h'}, 1, ChessManType.ROOK);
        createChessMen(new char[]{'b', 'g'}, 1, ChessManType.KNIGHT);
        createChessMen(new char[]{'c', 'f'}, 1, ChessManType.BISHOP);
        createChessMen(new char[]{'d'}, 1, ChessManType.KING);
        createChessMen(new char[]{'e'}, 1, ChessManType.QUEEN);

        //create and add to cellMap empty cells
        for(int i = 3; i < 7; i++) {
            for(char ch = 'a'; ch <= 'h'; ch++) {
                String cellName = ch + Integer.toString(i);
                cellMap.put(cellName, new Cell(cellName, new ChessMan(false, cellName, ChessManType.EMPTY)));
            }
        }
    }

    /**
     * Create group of chess, white and black, add this to map.
     * @param posArr array of chess positions
     * @param numRowForWhite num of row for white chess piece, black calculated symmetrically
     * @param chessType type chess piece(Pawn, Bishop, etc.)
     */
    private void createChessMen(char[] posArr, int numRowForWhite, ChessManType chessType) {
        for (char pos : posArr) { // Add pawns to cellMap
            String whitePawnLocation = pos + Integer.toString(numRowForWhite);
            ChessMan whiteChess = new ChessMan(true, whitePawnLocation, chessType);
            Cell whiteCell = new Cell(whitePawnLocation, whiteChess);
            cellMap.put(whitePawnLocation, whiteCell);

            String blackPawnLocation = pos + Integer.toString(ChessBoardSize - numRowForWhite + 1) ;
            ChessMan blackChess = new ChessMan(false, blackPawnLocation, chessType);
            Cell blackCell = new Cell(blackPawnLocation, blackChess);
            cellMap.put(blackPawnLocation, blackCell);
        }
    }

    public Cell getCell(String cell) {
        return cellMap.get(cell);
    }
}
