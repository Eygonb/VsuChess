import model.ChessBoard;
import model.Pieces.Knight;
import model.Pieces.Pawn;
import org.junit.Assert;
import org.junit.Test;

public class ChessBoardTest {
    @Test
    public void moveTest() {
        ChessBoard board = new ChessBoard();

        board.move(1, 5, 2, 5);
        Assert.assertTrue(board.getCellList().get(2).get(5).getChess() instanceof Pawn);
        Assert.assertNull(board.getCellList().get(1).get(5).getChess());

        board.move(0, 1, 2, 0);
        Assert.assertTrue(board.getCellList().get(2).get(0).getChess() instanceof Knight);
        Assert.assertNull(board.getCellList().get(0).get(1).getChess());
    }
}
