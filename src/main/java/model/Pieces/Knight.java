package model.Pieces;

import model.Cell;
import model.ChessPiece;
import model.Color;
import model.StepType;

import java.util.List;

public class Knight implements ChessPiece {
    private Color color;
    private boolean wasMoving;

    public Knight(Color color) {
        this.color = color;
        wasMoving = false;
    }

    public Color getColor() {
        return color;
    }

    public boolean isWasMoving() {
        return wasMoving;
    }

    @Override
    public StepType checkStep(List<List<Cell>> cellList, Cell from, Cell to) {
        if (from == to) return StepType.CANCEL;
        if (to.getChess() != null && to.getChess().getColor() == color) return StepType.CANCEL;

        int rowStep = to.getRow() - from.getRow();
        int columnStep = to.getColumn() - from.getColumn();

        if (Math.abs(rowStep) == 2 && Math.abs(columnStep) == 1 || Math.abs(rowStep) == 1 && Math.abs(columnStep) == 2) {
            wasMoving = true;
            return StepType.STEP;
        }
        return StepType.CANCEL;
    }
}
