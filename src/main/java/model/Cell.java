package model;

import model.Pieces.ChessPiece;

public class Cell {
    private final Integer row;
    private final Integer column;
    private ChessPiece chessPiece;

    public Cell(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    public Cell(Cell cell) {
        this.row = cell.row;
        this.column = cell.column;
        this.chessPiece = cell.chessPiece;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ChessPiece getChess() {
        return chessPiece;
    }

    public void setChess(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }
}
