package model.Pieces;

import model.Cell;
import model.ChessPiece;
import model.Color;
import model.StepType;

import java.util.ArrayList;
import java.util.List;

public class King implements ChessPiece {
    private Color color;
    private boolean wasMoving;

    public King(Color color) {
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

        if (Math.abs(rowStep) <= 1 && Math.abs(columnStep) <= 1) {
            if (!isUnderAttack(cellList, to)) {
                return StepType.STEP;
            }
        } else if (checkLeftCastling(cellList, from, to)) { // to cell is left castling cell
            return StepType.LEFT_CASTLING;
        } else if (checkRightCastling(cellList, from, to)) { // to cell is right castling cell
            return StepType.RIGHT_CASTLING;
        }

        return StepType.CANCEL;
    }

    @Override
    public List<Cell> getPossibleMoves(List<List<Cell>> cellList, Cell from) {
        List<Cell> possibleMoves = new ArrayList<>();
        int row = from.getRow(), column = from.getColumn();
        for(int directionIterator = 0; directionIterator < 9; directionIterator++) {
            int directionRow = - 1 + directionIterator % 3;
            int directionColumn = -1 + directionIterator / 3;
            int rowIndex = row + directionRow;
            int columnIndex = column + directionColumn;

            if(rowIndex >= 0 && rowIndex < 8 && columnIndex >= 0 && columnIndex < 8 &&
            checkStep(cellList, from, cellList.get(rowIndex).get(columnIndex)) == StepType.STEP)
                possibleMoves.add(cellList.get(rowIndex).get(columnIndex));
        }
        return possibleMoves;
    }

    private boolean isUnderAttack(List<List<Cell>> cellList, Cell to) {
        for (int i = 0; i < cellList.size(); i++) {
            for (int j = 0; j < cellList.get(i).size(); j++) {
                Cell from = cellList.get(i).get(j);
                if (from.getChess() != null && from.getChess() != this
                        && from.getChess().checkStep(cellList, from, to) == StepType.STEP)
                    return true;
            }
        }
        return false;
    }

    private boolean checkLeftCastling(List<List<Cell>> cellList, Cell from, Cell to) {
        int leftCastlingCell = 2;

        if (!wasMoving && to.getRow() == from.getRow() && to.getColumn() == leftCastlingCell) { // to cell is left castling cell
            if (!cellList.get(from.getRow()).get(0).getChess().isWasMoving()) { // if left rook wasn't move
                for (int i = 1; i < from.getColumn(); i++) {
                    if (cellList.get(from.getRow()).get(i).getChess() != null)
                        return false; // if way from rook to king isn't empty
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkRightCastling(List<List<Cell>> cellList, Cell from, Cell to) {
        int rightCastlingCell = 6;

        if (!wasMoving && to.getRow() == from.getRow() && to.getColumn() == rightCastlingCell) { // to cell is right castling cell
            if (!cellList.get(from.getRow()).get(7).getChess().isWasMoving()) { // if right rook wasn't move
                for (int i = 7; i > from.getColumn(); i--) {
                    if (cellList.get(from.getRow()).get(i).getChess() != null)
                        return false; // if way from rook to king isn't empty
                }
                return true;
            }
        }
        return false;
    }
}
