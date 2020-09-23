public class Bishop extends ChessMan {
    public Bishop(boolean isWhite, Cell currentCell) {
        super(isWhite, currentCell);
    }

    public boolean step(Cell toCell) {
        return false;
    }
}
