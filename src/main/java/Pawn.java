public class Pawn extends ChessMan {
    public Pawn(boolean isWhite, Cell currentCell) {
        super(isWhite, currentCell);
    }

    public boolean step(Cell toCell) {
        return false;
    }
}
