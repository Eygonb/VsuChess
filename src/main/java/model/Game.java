package model;

public class Game {
    private ChessBoard chessBoard;
    private Color currentPlayerColor;

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public Game() {
        chessBoard = new ChessBoard();
        currentPlayerColor = Color.WHITE;
    }

    public boolean move(int fromRow, int fromColumn, int toRow, int toColumn) {
        if(getCell(fromRow, fromColumn).getChess() == null) return false;
        if(currentPlayerColor != getCell(fromRow, fromColumn).getChess().getColor()) return false;

        if(chessBoard.move(fromRow, fromColumn, toRow, toColumn)) {
            changePlayer();
            return true;
        } else return false;
    }



    private Cell getCell(int row, int column) {
        return chessBoard.getCellList().get(row).get(column);
    }

    private void changePlayer() {
        if(currentPlayerColor == Color.WHITE) currentPlayerColor = Color.BLACK;
        else currentPlayerColor = Color.WHITE;
    }
}
