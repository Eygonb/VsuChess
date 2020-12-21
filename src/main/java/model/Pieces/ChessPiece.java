package model.Pieces;

import model.Cell;
import model.enums.Color;
import model.enums.StepType;

import java.util.ArrayList;
import java.util.List;

public interface ChessPiece {
    StepType checkStep(List<List<Cell>> cellList, Cell from, Cell to);

    Color getColor();

    boolean isMoved();

    void setMoved();

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
