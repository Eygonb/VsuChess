public class ChessMan {
    final private boolean isWhite;
    private String currentCell;
    private ChessManType type;

    public ChessMan(boolean isWhite, String currentCell, ChessManType type) {
        this.isWhite = isWhite;
        this.currentCell = currentCell;
        this.type = type;
    }

    public boolean step(Cell toCell) {
        return false;
    }
}
