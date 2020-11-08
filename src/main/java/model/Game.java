package model;

public class Game {
    private final ChessBoard chessBoard;
    private Color currentPlayerColor;

    public Game() {
        chessBoard = new ChessBoard();
        currentPlayerColor = Color.WHITE;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public boolean move(int fromColumn, int fromRow, int toColumn, int toRow) {
        if (getCell(fromRow, fromColumn).getChess() == null) return false;
        if (currentPlayerColor != getCell(fromRow, fromColumn).getChess().getColor()) return false;

        if (chessBoard.move(fromRow, fromColumn, toRow, toColumn)) {
            changePlayer();
            return true;
        } else return false;
    }

    public boolean isEnded() {
        Color prevPlayerColor = currentPlayerColor == Color.BLACK ? Color.WHITE : Color.BLACK;
        return chessBoard.checkWin(prevPlayerColor);
    }

    private Cell getCell(int row, int column) {
        return chessBoard.getCellList().get(row).get(column);
    }

    private void changePlayer() {
        if (currentPlayerColor == Color.WHITE) currentPlayerColor = Color.BLACK;
        else currentPlayerColor = Color.WHITE;
    }
}
