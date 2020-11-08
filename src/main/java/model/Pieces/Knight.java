package model.Pieces;

import model.Cell;
import model.ChessPiece;
import model.Color;
import model.StepType;

import java.util.ArrayList;
import java.util.List;

public class Knight implements ChessPiece {
    private Color color;
    private boolean wasMoving;

    public Knight(Color color) {
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

        if (Math.abs(rowStep) == 2 && Math.abs(columnStep) == 1 || Math.abs(rowStep) == 1 && Math.abs(columnStep) == 2) {
            return StepType.STEP;
        }
        return StepType.CANCEL;
    }

    //@Override
    //public List<Cell> getPossibleMoves(List<List<Cell>> cellList, Cell from) {
    //    List<Cell> possibleMoves = new ArrayList<>();
    //    int row = from.getRow(), column = from.getColumn();
//
    //    if(checkStep(cellList, from, cellList.get(row + 2).get(column + 1)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row + 2).get(column + 1));
//
    //    if(checkStep(cellList, from, cellList.get(row + 2).get(column - 1)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row + 2).get(column - 1));
//
    //    if(checkStep(cellList, from, cellList.get(row - 2).get(column + 1)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row - 2).get(column + 1));
//
    //    if(checkStep(cellList, from, cellList.get(row - 2).get(column - 1)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row - 2).get(column - 1));
//
    //    if(checkStep(cellList, from, cellList.get(row + 1).get(column + 2)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row + 1).get(column + 2));
//
    //    if(checkStep(cellList, from, cellList.get(row + 1).get(column - 2)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row + 1).get(column - 2));
//
    //    if(checkStep(cellList, from, cellList.get(row - 1).get(column + 2)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row - 1).get(column + 2));
//
    //    if(checkStep(cellList, from, cellList.get(row - 1).get(column - 2)) == StepType.STEP)
    //        possibleMoves.add(cellList.get(row - 1).get(column - 2));
//
    //    return possibleMoves;
    //}
}
