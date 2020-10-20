package model;

import java.util.List;

public interface ChessPiece {
    StepType checkStep(List<List<Cell>> cellList, Cell from, Cell to);

    Color getColor();

    boolean isWasMoving();

    void setWasMoving();
}
