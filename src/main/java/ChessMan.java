public abstract class ChessMan {
    final private boolean isWhite;
    private Cell currentCell;

    public ChessMan(boolean isWhite, Cell currentCell) {
        this.isWhite = isWhite;
        this.currentCell = currentCell;
    }

    public abstract boolean step(Cell toCell);
}
