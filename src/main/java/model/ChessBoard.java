package model;

import model.Pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private final Integer CHESS_BOARD_SIZE = 8;
    private List<List<Cell>> cellList;

    public List<List<Cell>> getCellList() {
        return cellList;
    }

    public ChessBoard(List<List<Cell>> cellList) {
        this.cellList = cellList;
    }

    public ChessBoard() {
        cellList = new ArrayList<>();

        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                row.add(new Cell(i, j));
            }
            cellList.add(row);
        }

        for (Cell cell : cellList.get(1)) { // fill bottom row with pawns
            cell.setChess(new Pawn(Color.WHITE));
        }

        for (Cell cell : cellList.get(CHESS_BOARD_SIZE - 2)) { // fill top row with pawns
            cell.setChess(new Pawn(Color.BLACK));
        }

        fillRow(0, Color.WHITE); // fill top row

        fillRow(CHESS_BOARD_SIZE - 1, Color.BLACK); // fill bottom row
    }

    public boolean move(int fromRow, int fromColumn, int toRow, int toColumn) {
        Cell from = cellList.get(fromRow).get(fromColumn);
        Cell to = cellList.get(toRow).get(toColumn);
        ChessPiece chessFrom = from.getChess();
        StepType stepType = chessFrom.checkStep(cellList, from, to);

        if (stepType == StepType.STEP) {
            step(from, to);
            return true;
        } else if (stepType == StepType.LEFT_CASTLING) {
            Cell leftCastlingCellForRook = cellList.get(fromRow).get(3);
            Cell leftRookCell = cellList.get(fromRow).get(0);

            step(from, to);
            step(leftRookCell, leftCastlingCellForRook);
            return true;
        } else if (stepType == StepType.RIGHT_CASTLING) {
            Cell rightCastlingCellForRook = cellList.get(fromRow).get(5);
            Cell rightRookCell = cellList.get(fromRow).get(7);

            step(from, to);
            step(rightRookCell, rightCastlingCellForRook);
            return true;
        } else if (stepType == StepType.CANCEL) return false;
        return false;
    } //TODO превращение пешки

    private void step(Cell from, Cell to) {
        to.setChess(from.getChess());
        from.setChess(null); //TODO изменить wasMoving
    }

    private void fillRow(int row, Color color) {
        cellList.get(row).get(0).setChess(new Rook(color));
        cellList.get(row).get(CHESS_BOARD_SIZE - 1).setChess(new Rook(color));
        cellList.get(row).get(1).setChess(new Knight(color));
        cellList.get(row).get(CHESS_BOARD_SIZE - 2).setChess(new Knight(color));
        cellList.get(row).get(2).setChess(new Bishop(color));
        cellList.get(row).get(CHESS_BOARD_SIZE - 3).setChess(new Bishop(color));
        cellList.get(row).get(3).setChess(new Queen(color));
        cellList.get(row).get(CHESS_BOARD_SIZE - 4).setChess(new King(color));
    }
}
