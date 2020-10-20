package model;

import java.util.ArrayList;
import java.util.List;

public interface ChessPiece {
    StepType checkStep(List<List<Cell>> cellList, Cell from, Cell to);

    Color getColor();

    boolean isWasMoving();

    void setWasMoving();

    default List<Cell> getPossibleMoves(List<List<Cell>> cellList, Cell from) {
        List<Cell> possibleMoves = new ArrayList<>();
        for (List<Cell> row : cellList) {
            for (Cell cell : row) {
                if (from.getChess().checkStep(cellList, from, cell) == StepType.STEP) possibleMoves.add(cell);
            }
        }
        return possibleMoves;
    }
}
