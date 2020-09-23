public class Knight extends ChessMan {
    public Knight(boolean isWhite, Cell currentCell) {
        super(isWhite, currentCell);
    }

    public boolean step(Cell toCell) {
        return false;
    }
}
