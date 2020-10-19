package model.Pieces;

import model.Cell;
import model.ChessPiece;
import model.Color;
import model.StepType;

import java.util.List;

public class King implements ChessPiece {
    private Color color;
    private boolean wasMoving;

    public King(Color color) {
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
        int leftCastlingCell = 2, rightCastlingCell = 6;

        if (Math.abs(rowStep) <= 1 && Math.abs(columnStep) <= 1) {
            if (!isUnderAttack(cellList, to)) {
                wasMoving = true;
                return StepType.STEP;
            }
        } else if (!wasMoving && to.getRow() == from.getRow() && to.getColumn() == leftCastlingCell) { // to cell is left castling cell
            if (!cellList.get(from.getRow()).get(0).getChess().isWasMoving()) { // if left rook wasn't move
                for (int i = 1; i < from.getColumn(); i++) {
                    if (cellList.get(from.getRow()).get(i).getChess() != null) return StepType.CANCEL; // if way from rook to king isn't empty
                }
                wasMoving = true;
                return StepType.LEFT_CASTLING;
            }
        } else if (!wasMoving && to.getRow() == from.getRow() && to.getColumn() == rightCastlingCell) { // to cell is right castling cell
            if (!cellList.get(from.getRow()).get(7).getChess().isWasMoving()) { // if right rook wasn't move
                for (int i = 7; i > from.getColumn(); i--) {
                    if (cellList.get(from.getRow()).get(i).getChess() != null) return StepType.CANCEL; // if way from rook to king isn't empty
                }
                wasMoving = true;
                return StepType.RIGHT_CASTLING;
            }
        }

        return StepType.CANCEL;
    }

    private boolean isUnderAttack(List<List<Cell>> cellList, Cell to) {
        for(int i = 0; i < cellList.size(); i++) {
            for(int j = 0; j < cellList.get(i).size(); j++) {
                Cell from = cellList.get(i).get(j);
                if(from.getChess() != null && from.getChess() != this
                        && from.getChess().checkStep(cellList, from, to) == StepType.STEP)
                    return true;
            }
        }
        return false;
    }
}
