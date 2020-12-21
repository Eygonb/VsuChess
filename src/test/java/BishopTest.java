import model.Cell;
import model.Pieces.ChessPiece;
import model.enums.Color;
import model.Pieces.Bishop;
import model.enums.StepType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BishopTest {
    private final int CHESS_BOARD_SIZE = 8;

    @Test
    public void moveTest() {
        List<List<Cell>> cellList = new ArrayList<>();
        fillCellList(cellList);

        ChessPiece whiteBishop = new Bishop(Color.WHITE);
        int row = 4, column = 4;
        Cell cell = cellList.get(row).get(column);
        cell.setChess(whiteBishop);

        for(int i = 1; i < CHESS_BOARD_SIZE - row; i++) {
            Assert.assertEquals(whiteBishop.checkStep(cellList, cell, cellList.get(row + i).get(column + i)), StepType.STEP);
            Assert.assertEquals(whiteBishop.checkStep(cellList, cell, cellList.get(row + i).get(column - i)), StepType.STEP);
            Assert.assertEquals(whiteBishop.checkStep(cellList, cell, cellList.get(row - i).get(column + i)), StepType.STEP);
            Assert.assertEquals(whiteBishop.checkStep(cellList, cell, cellList.get(row - i).get(column - i)), StepType.STEP);
        }
    }

    @Test
    public void moveWithBlockTest() {
        List<List<Cell>> cellList = new ArrayList<>();
        fillCellList(cellList);

        ChessPiece whiteBishop = new Bishop(Color.WHITE);
        int row = 4, column = 4;
        Cell cell = cellList.get(row).get(column);
        cell.setChess(whiteBishop);

        cellList.get(row + 1).get(column + 1).setChess(new Bishop(Color.WHITE));

        Assert.assertEquals(whiteBishop.checkStep(cellList, cell, cellList.get(row + 2).get(column + 2)), StepType.CANCEL);
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
