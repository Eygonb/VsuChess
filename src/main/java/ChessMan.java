public abstract class ChessMan {
    private boolean isWhite;
    private Cell currentCell;
    public abstract boolean step(Cell toCell);
}
