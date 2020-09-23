public class Rook extends ChessMan {
    public Rook(boolean isWhite, Cell currentCell) {
        super(isWhite, currentCell);
    }

    public boolean step(Cell toCell) {
        return false;
    }
}
