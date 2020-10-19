package model.Pieces;

import model.Cell;
import model.ChessPiece;
import model.Color;
import model.StepType;

import java.util.List;

public class Rook implements ChessPiece {
    private Color color;
    private boolean wasMoving;

    public Rook(Color color) {
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
        int direction;

        if (rowStep == 0 && columnStep != 0) {
            direction = columnStep > 0 ? 1 : -1;
            for (int i = from.getColumn() + direction; i != to.getColumn(); i += direction) {
                if (cellList.get(from.getRow()).get(i).getChess() != null) return StepType.CANCEL;
            }
            wasMoving = true;
            return StepType.STEP;
        } else if (columnStep == 0 && rowStep != 0) {
            direction = rowStep > 0 ? 1 : -1;
            for (int i = from.getRow() + direction; i != to.getRow(); i += direction) {
                if (cellList.get(i).get(from.getColumn()).getChess() != null) return StepType.CANCEL;
            }
            wasMoving = true;
            return StepType.STEP;
        }
        return StepType.CANCEL;
    }
}
