import model.Cell;
import model.ChessBoard;
import model.Color;
import model.Pieces.King;
import model.Pieces.Knight;
import model.Pieces.Pawn;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void toStringTest() {
        ChessBoard board = new ChessBoard();

        Assert.assertEquals(board.toString(), "  |a |b |c |d |e |f |g |h |\n" +
                "1 |wR|wN|wB|wQ|wK|wB|wN|wR|\n" +
                "2 |wp|wp|wp|wp|wp|wp|wp|wp|\n" +
                "3 |  |  |  |  |  |  |  |  |\n" +
                "4 |  |  |  |  |  |  |  |  |\n" +
                "5 |  |  |  |  |  |  |  |  |\n" +
                "6 |  |  |  |  |  |  |  |  |\n" +
                "7 |bp|bp|bp|bp|bp|bp|bp|bp|\n" +
                "8 |bR|bN|bB|bQ|bK|bB|bN|bR|\n");

        List<List<Cell>> cellList = new ArrayList<>();
        for(int i = 0; i < board.getChessBoardSize(); i++) {
            cellList.add(new ArrayList<>());
            for(int j = 0; j < board.getChessBoardSize(); j++) {
                cellList.get(i).add(new Cell(i, j));
            }
        }
        cellList.get(0).get(0).setChess(new King(Color.WHITE));
        board = new ChessBoard(cellList);

        Assert.assertEquals(board.toString(), "  |a |b |c |d |e |f |g |h |\n" +
                "1 |wK|  |  |  |  |  |  |  |\n" +
                "2 |  |  |  |  |  |  |  |  |\n" +
                "3 |  |  |  |  |  |  |  |  |\n" +
                "4 |  |  |  |  |  |  |  |  |\n" +
                "5 |  |  |  |  |  |  |  |  |\n" +
                "6 |  |  |  |  |  |  |  |  |\n" +
                "7 |  |  |  |  |  |  |  |  |\n" +
                "8 |  |  |  |  |  |  |  |  |\n");
    }
}
