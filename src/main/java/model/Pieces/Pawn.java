package model.Pieces;

import model.Cell;
import model.ChessPiece;
import model.Color;
import model.StepType;

import java.util.List;

public class Pawn implements ChessPiece {
    private Color color;
    private boolean wasMoving;

    public Pawn(Color color) {
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
        int colorMove = color == Color.WHITE ? 1 : -1;

        if (!wasMoving && rowStep == colorMove * 2 && columnStep == 0) {
            boolean checkBlock = cellList.get(from.getRow() + colorMove).get(from.getColumn()).getChess() == null &&
                    to.getChess() == null;
            if (checkBlock) {
                return StepType.STEP;
            }
        } else if (rowStep == colorMove) {
            boolean checkEat = to.getChess() != null && Math.abs(columnStep) == 1 && to.getChess().getColor() != color;
            if (columnStep == 0 && to.getChess() == null) {
                return StepType.STEP;
            } else if (checkEat) {
                return StepType.STEP;
            }
        }
        return StepType.CANCEL;
    }

    //@Override
    //public List<Cell> getPossibleMoves(List<List<Cell>> cellList, Cell from) {
    //    List<Cell> possibleMoves = new ArrayList<>();
    //    int row = from.getRow(), column = from.getColumn();
//
    //    if(checkStep(cellList, from, cellList.get(row + 1).get(column)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row + 1).get(column));
//
    //    if(checkStep(cellList, from, cellList.get(row + 2).get(column)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row + 2).get(column));
//
    //    if(checkStep(cellList, from, cellList.get(row + 1).get(column + 1)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row + 1).get(column + 1));
//
    //    if(checkStep(cellList, from, cellList.get(row + 1).get(column - 1)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row + 1).get(column - 1));
//
    //    if(checkStep(cellList, from, cellList.get(row - 1).get(column)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row - 1).get(column));
//
    //    if(checkStep(cellList, from, cellList.get(row - 2).get(column)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row - 2).get(column));
//
    //    if(checkStep(cellList, from, cellList.get(row - 1).get(column + 1)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row - 1).get(column + 1));
//
    //    if(checkStep(cellList, from, cellList.get(row - 1).get(column - 1)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row - 1).get(column - 1));
//
    //    return possibleMoves;
    //}
}
