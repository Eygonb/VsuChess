package model.Pieces;

import model.Cell;
import model.ChessPiece;
import model.Color;
import model.StepType;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements ChessPiece {
    private Color color;
    private boolean moved;

    public Bishop(Color color) {
        this.color = color;
        moved = false;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isMoved() {
        return moved;
    }

    @Override
    public void setMoved() {
        this.moved = true;
    }

    @Override
    public StepType checkStep(List<List<Cell>> cellList, Cell from, Cell to) {
        if (from == to) return StepType.CANCEL;
        if (to.getChess() != null && to.getChess().getColor() == color) return StepType.CANCEL;

        int rowStep = to.getRow() - from.getRow();
        int columnStep = to.getColumn() - from.getColumn();

        if (Math.abs(rowStep) == Math.abs(columnStep)) {
            if (checkForMissingDiagonalBlock(cellList, from, to)) return StepType.STEP;
            else return StepType.CANCEL;
        }
        return StepType.CANCEL;
    }

    @Override
    public List<Cell> getPossibleMoves(List<List<Cell>> cellList, Cell from) {
        List<Cell> possibleMoves = new ArrayList<>();
        for (int directionIterator = 0; directionIterator < 4; directionIterator++) {
            int horizontalDirection = directionIterator % 2 == 0 ? 1 : -1;
            int verticalDirection = directionIterator / 2 == 0 ? 1 : -1;

            for (int i = from.getRow() + verticalDirection, j = from.getColumn() + horizontalDirection;
                 i >= 0 && i < 8 && j >= 0 && j < 8;
                 i += verticalDirection, j += horizontalDirection) {
                Cell to = cellList.get(i).get(j);
                if (from.getChess().checkStep(cellList, from, to) == StepType.STEP) possibleMoves.add(to);
            }
        }

        return possibleMoves;
    }

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
}
