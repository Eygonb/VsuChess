package model;

import model.Pieces.*;
import model.enums.Color;
import model.enums.StepType;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private final Integer CHESS_BOARD_SIZE = 8;
    private final List<List<Cell>> cellList;

    public ChessBoard(List<List<Cell>> cellList) {
        if (cellList.size() != CHESS_BOARD_SIZE) throw new IllegalArgumentException();
        for (int i = 0; i < CHESS_BOARD_SIZE; i++)
            if (cellList.get(i).size() != CHESS_BOARD_SIZE) throw new IllegalArgumentException();
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

    public Integer getChessBoardSize() {
        return CHESS_BOARD_SIZE;
    }

    public List<List<Cell>> getCellList() {
        return cellList;
    }

    public boolean move(int fromRow, int fromColumn, int toRow, int toColumn) {
        Cell from = cellList.get(fromRow).get(fromColumn);
        Cell to = cellList.get(toRow).get(toColumn);
        ChessPiece chessFrom = from.getChess();
        StepType stepType = chessFrom.checkStep(cellList, from, to);

        if (stepType == StepType.STEP) {
            if (chessFrom instanceof Pawn &&
                    ((toRow == 0 && chessFrom.getColor() == Color.BLACK) ||
                            (toRow == CHESS_BOARD_SIZE && chessFrom.getColor() == Color.WHITE))) {
                from.setChess(new Queen(chessFrom.getColor()));
            }
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
    }

    public boolean checkWin(Color color) {
        Color enemyColor = color == Color.BLACK ? Color.WHITE : Color.BLACK;
        if (!checkmate(enemyColor)) return false;

        List<Cell> enemyChessPieces = new ArrayList<>();
        for (List<Cell> row : cellList) {
            for (Cell cell : row) {
                if (cell.getChess() != null && cell.getChess().getColor() == enemyColor) enemyChessPieces.add(cell);
            }
        }

        for (Cell enemyChessPieceCell : enemyChessPieces) {
            List<Cell> possibleMoves = enemyChessPieceCell.getChess().getPossibleMoves(cellList, enemyChessPieceCell);

            for (Cell possibleMovesCell : possibleMoves) {
                Cell backupEnemyChessPieceCell = new Cell(enemyChessPieceCell);
                Cell backupPossibleMovesCell = new Cell(possibleMovesCell);

                possibleMovesCell.setChess(enemyChessPieceCell.getChess());
                enemyChessPieceCell.setChess(null);

                boolean checkmate = checkmate(enemyColor);

                possibleMovesCell.setChess(backupPossibleMovesCell.getChess());
                enemyChessPieceCell.setChess(backupEnemyChessPieceCell.getChess());

                if (!checkmate) {
                    return false;
                }


            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();

        boardString.append("  ");
        for (int i = 0; i < cellList.size(); i++) {
            boardString.append("|").append((char) ('a' + i)).append(" ");
            if (i == cellList.size() - 1) boardString.append('|');
        }
        boardString.append('\n');

        for (int i = 0; i < cellList.size(); i++) {
            StringBuilder chessPiecesString = new StringBuilder();
            chessPiecesString.append(i + 1).append(" |");
            for (int j = 0; j < cellList.get(i).size(); j++) {
                ChessPiece chess = cellList.get(i).get(j).getChess();
                if (chess instanceof Bishop) {
                    chessPiecesString.append(chess.getColor() == Color.BLACK ? 'b' : 'w');
                    chessPiecesString.append('B');
                } else if (chess instanceof King) {
                    chessPiecesString.append(chess.getColor() == Color.BLACK ? 'b' : 'w');
                    chessPiecesString.append('K');
                } else if (chess instanceof Knight) {
                    chessPiecesString.append(chess.getColor() == Color.BLACK ? 'b' : 'w');
                    chessPiecesString.append('N');
                } else if (chess instanceof Pawn) {
                    chessPiecesString.append(chess.getColor() == Color.BLACK ? 'b' : 'w');
                    chessPiecesString.append('p');
                } else if (chess instanceof Queen) {
                    chessPiecesString.append(chess.getColor() == Color.BLACK ? 'b' : 'w');
                    chessPiecesString.append('Q');
                } else if (chess instanceof Rook) {
                    chessPiecesString.append(chess.getColor() == Color.BLACK ? 'b' : 'w');
                    chessPiecesString.append('R');
                } else chessPiecesString.append("  ");
                chessPiecesString.append('|');
            }
            boardString.append(chessPiecesString);
            boardString.append('\n');
        }
        return boardString.toString();
    }

    private void step(Cell from, Cell to) {
        from.getChess().setMoved();
        to.setChess(from.getChess());
        from.setChess(null);
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

    private Cell getKing(Color color) { //return cell of king or cell with null chessPiece
        for (List<Cell> row : cellList) {
            for (Cell cell : row) {
                if (cell.getChess() instanceof King && cell.getChess().getColor() == color) return cell;
            }
        }
        return null;
    }

    private boolean checkmate(Color color) {  // return
        Cell kingCell = getKing(color);
        if (kingCell == null) return true;

        for (int i = 0; i < cellList.size(); i++) {
            for (int j = 0; j < cellList.get(i).size(); j++) {
                Cell from = cellList.get(i).get(j);
                if (from.getChess() != null && from.getChess() != kingCell.getChess()
                        && from.getChess().checkStep(cellList, from, kingCell) == StepType.STEP)
                    return true;
            }
        }
        return false;
    }
}
