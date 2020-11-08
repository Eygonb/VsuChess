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
    public boolean isMoved() {
        return wasMoving;
    }

    @Override
    public void setMoved() {
        this.wasMoving = true;
    }

    @Override
    public StepType checkStep(List<List<Cell>> cellList, Cell from, Cell to) {
        if (from == to) return StepType.CANCEL;
        if (to.getChess() != null && to.getChess().getColor() == color) return StepType.CANCEL;

        int rowStep = to.getRow() - from.getRow();
        int columnStep = to.getColumn() - from.getColumn();

        if (rowStep == 0 && columnStep != 0) {
            if (checkForMissingHorizontalBlock(cellList, from, to)) return StepType.STEP;
            else return StepType.CANCEL;
        } else if (columnStep == 0 && rowStep != 0) {
            if (checkForMissingVerticalBlock(cellList, from, to)) return StepType.STEP;
            else return StepType.CANCEL;
        } else if (Math.abs(rowStep) == Math.abs(columnStep)) {
            if (checkForMissingDiagonalBlock(cellList, from, to)) return StepType.STEP;
            else return StepType.CANCEL;
        }
        return StepType.CANCEL;
    }

    //@Override
    //public List<Cell> getPossibleMoves(List<List<Cell>> cellList, Cell from) {
    //    return null;
    //}

    private boolean checkForMissingDiagonalBlock(List<List<Cell>> cellList, Cell from, Cell to) {
        int directionRow = to.getRow() - from.getRow() > 0 ? 1 : -1;
        int directionColumn = to.getColumn() - from.getColumn() > 0 ? 1 : -1;

        for (int i = from.getRow() + directionRow, j = from.getColumn() + directionColumn;
             i != to.getRow() && j != to.getColumn();
             i += directionRow, j += directionColumn) {
            if (cellList.get(i).get(j).getChess() != null) return false;
        }
        return true;
    }

    private boolean checkForMissingHorizontalBlock(List<List<Cell>> cellList, Cell from, Cell to) {
        int columnStep = to.getColumn() - from.getColumn();
        int direction = columnStep > 0 ? 1 : -1;

        for (int i = from.getColumn() + direction; i != to.getColumn(); i += direction) {
            if (cellList.get(from.getRow()).get(i).getChess() != null) return false;
        }
        return true;
    }

    private boolean checkForMissingVerticalBlock(List<List<Cell>> cellList, Cell from, Cell to) {
        int rowStep = to.getRow() - from.getRow();
        int direction = rowStep > 0 ? 1 : -1;

        for (int i = from.getRow() + direction; i != to.getRow(); i += direction) {
            if (cellList.get(i).get(from.getColumn()).getChess() != null) return false;
        }
        return true;
    }
}
