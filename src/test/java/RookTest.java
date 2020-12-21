import model.Cell;
import model.Pieces.ChessPiece;
import model.enums.Color;
import model.Pieces.Rook;
import model.enums.StepType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RookTest {
    private final int CHESS_BOARD_SIZE = 8;

    @Test
    public void moveTest() {
        List<List<Cell>> cellList = new ArrayList<>();
        fillCellList(cellList);

        ChessPiece whiteRook = new Rook(Color.WHITE);
        int row = 4, column = 4;
        Cell cell = cellList.get(row).get(column);
        cell.setChess(whiteRook);

        for(int i = 1; i < CHESS_BOARD_SIZE - row; i++) {
            Assert.assertEquals(whiteRook.checkStep(cellList, cell, cellList.get(row + i).get(column)), StepType.STEP);
            Assert.assertEquals(whiteRook.checkStep(cellList, cell, cellList.get(row - i).get(column)), StepType.STEP);
            Assert.assertEquals(whiteRook.checkStep(cellList, cell, cellList.get(row).get(column + i)), StepType.STEP);
            Assert.assertEquals(whiteRook.checkStep(cellList, cell, cellList.get(row).get(column - i)), StepType.STEP);
        }
    }

    @Test
    public void moveWithBlockTest() {
        List<List<Cell>> cellList = new ArrayList<>();
        fillCellList(cellList);

        ChessPiece whiteRook = new Rook(Color.WHITE);
        int row = 4, column = 4;
        Cell cell = cellList.get(row).get(column);
        cell.setChess(whiteRook);

        cellList.get(row).get(column + 1).setChess(new Rook(Color.WHITE));

        Assert.assertEquals(whiteRook.checkStep(cellList, cell, cellList.get(row).get(column + 2)), StepType.CANCEL);
    }

    private void fillCellList(List<List<Cell>> cellList) {
        for(int i = 0; i < CHESS_BOARD_SIZE; i++) {
            List<Cell> rowList = new ArrayList<>();
            for(int j = 0; j < CHESS_BOARD_SIZE; j++) {
                rowList.add(new Cell(i, j));
            }
            cellList.add(rowList);
        }
    }
}
