public class Queen extends ChessMan {
    public Queen(boolean isWhite, Cell currentCell) {
        super(isWhite, currentCell);
    }

    public boolean step(Cell toCell) {
        return false;
    }
}
