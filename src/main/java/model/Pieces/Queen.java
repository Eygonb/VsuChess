package model.Pieces;

import model.Cell;
import model.ChessPiece;
import model.Color;
import model.StepType;

import java.util.List;

public class Queen implements ChessPiece {
    private Color color;
    private boolean wasMoving;

    public Queen(Color color) {
        this.color = color;
        wasMoving = false;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isWasMoving() {
        return wasMoving;
    }

    @Override
    public void setWasMoving() {
        this.wasMoving = true;
    }

    @Override
    public StepType checkStep(List<List<Cell>> cellList, Cell from, Cell to) {
        if (from == to) return StepType.CANCEL;
        if (to.getChess() != null && to.getChess().getColor() == color) return StepType.CANCEL;

        int rowStep = to.getRow() - from.getRow();
        int columnStep = to.getColumn() - from.getColumn();

        if (rowStep == 0 && columnStep != 0) {
            int direction = columnStep > 0 ? 1 : -1;
            for (int i = from.getColumn() + direction; i != to.getColumn(); i += direction) {
                if (cellList.get(from.getRow()).get(i).getChess() != null) return StepType.CANCEL;
            }
            return StepType.STEP;

        } else if (columnStep == 0 && rowStep != 0) {
            int direction = rowStep > 0 ? 1 : -1;
            for (int i = from.getRow() + direction; i != to.getRow(); i += direction) {
                if (cellList.get(i).get(from.getColumn()).getChess() != null) return StepType.CANCEL;
            }
            return StepType.STEP;
        } else if (Math.abs(rowStep) == Math.abs(columnStep)) {
            int directionRow = rowStep > 0 ? 1 : -1;
            int directionColumn = columnStep > 0 ? 1 : -1;

            for (int i = from.getRow() + directionRow; i != to.getRow(); i += directionRow) {
                for (int j = from.getColumn() + directionColumn; j != to.getColumn(); j += directionColumn) {
                    if (cellList.get(i).get(j).getChess() != null) return StepType.CANCEL;
                }
            }
            return StepType.STEP;
        }
        return StepType.CANCEL;
    }
}
