import model.Cell;
import model.Pieces.ChessPiece;
import model.enums.Color;
import model.Pieces.King;
import model.enums.StepType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KingTest {
    private final int CHESS_BOARD_SIZE = 8;

    @Test
    public void moveTest() {
        List<List<Cell>> cellList = new ArrayList<>();
        fillCellList(cellList);

        ChessPiece whiteKing = new King(Color.WHITE);
        int row = 4, column = 4;
        Cell cell = cellList.get(row).get(column);
        cell.setChess(whiteKing);

        Assert.assertEquals(whiteKing.checkStep(cellList, cell, cellList.get(row + 1).get(column + 1)), StepType.STEP);
        Assert.assertEquals(whiteKing.checkStep(cellList, cell, cellList.get(row + 1).get(column - 1)), StepType.STEP);
        Assert.assertEquals(whiteKing.checkStep(cellList, cell, cellList.get(row - 1).get(column + 1)), StepType.STEP);
        Assert.assertEquals(whiteKing.checkStep(cellList, cell, cellList.get(row - 1).get(column - 1)), StepType.STEP);
        Assert.assertEquals(whiteKing.checkStep(cellList, cell, cellList.get(row + 1).get(column)), StepType.STEP);
        Assert.assertEquals(whiteKing.checkStep(cellList, cell, cellList.get(row).get(column + 1)), StepType.STEP);
        Assert.assertEquals(whiteKing.checkStep(cellList, cell, cellList.get(row - 1).get(column)), StepType.STEP);
        Assert.assertEquals(whiteKing.checkStep(cellList, cell, cellList.get(row).get(column - 1)), StepType.STEP);
    }

    //TODO check

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
