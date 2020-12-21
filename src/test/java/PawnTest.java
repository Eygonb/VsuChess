import model.Cell;
import model.Pieces.ChessPiece;
import model.enums.Color;
import model.Pieces.Pawn;
import model.enums.StepType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PawnTest {
    private final int CHESS_BOARD_SIZE = 8;

    @Test
    public void moveTest() {
        List<List<Cell>> cellList = new ArrayList<>();
        fillCellList(cellList);

        ChessPiece whitePawn = new Pawn(Color.WHITE);
        Cell cell = cellList.get(1).get(4);
        cell.setChess(whitePawn);
        Cell firstStepCell = cellList.get(cell.getRow() + 2).get(cell.getColumn());
        Cell secondStepCell = cellList.get(cell.getRow() + 1).get(cell.getColumn());
        Cell wrongStepCell = cellList.get(cell.getRow() + 3).get(cell.getColumn());

        Assert.assertEquals(whitePawn.checkStep(cellList, cell, firstStepCell), StepType.STEP);
        Assert.assertEquals(whitePawn.checkStep(cellList, cell, secondStepCell), StepType.STEP);
        Assert.assertEquals(whitePawn.checkStep(cellList, cell, wrongStepCell), StepType.CANCEL);
    }

    @Test
    public void eatTest() {
        List<List<Cell>> cellList = new ArrayList<>();
        fillCellList(cellList);

        ChessPiece whitePawn = new Pawn(Color.WHITE);
        ChessPiece blackPawn = new Pawn(Color.BLACK);
        Cell cell = cellList.get(1).get(4);
        Cell enemyCell = cellList.get(2).get(5);
        Cell wrongCell = cellList.get(2).get(3);
        cell.setChess(whitePawn);
        enemyCell.setChess(blackPawn);

        Assert.assertEquals(whitePawn.checkStep(cellList, cell, enemyCell), StepType.STEP);
        Assert.assertEquals(whitePawn.checkStep(cellList, cell, wrongCell), StepType.CANCEL);
    }

    @Test
    public void moveWithBlockTest() {
        List<List<Cell>> cellList = new ArrayList<>();
        fillCellList(cellList);

        ChessPiece whitePawn = new Pawn(Color.WHITE);
        Cell cell = cellList.get(1).get(4);
        cell.setChess(whitePawn);
        ChessPiece blockPawn = new Pawn(Color.WHITE);
        Cell blockCell = cellList.get(1).get(5);
        blockCell.setChess(blockPawn);
        Cell stepCell = cellList.get(1).get(6);

        Assert.assertEquals(whitePawn.checkStep(cellList, cell, stepCell), StepType.CANCEL);
    }

    private void fillCellList(List<List<Cell>> cellList) {
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            List<Cell> rowList = new ArrayList<>();
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                rowList.add(new Cell(i, j));
            }
            cellList.add(rowList);
        }
    }
}
