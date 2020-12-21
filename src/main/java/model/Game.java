package model;

import model.enums.Color;

import java.util.Date;

public class Game {
    private final ChessBoard chessBoard;
    private Color currentPlayerColor;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Integer winnerId, looserId;
    private boolean isFinished = false;
    private Integer stepCount = 0;
    private final Long timeStart;
    private Long timeEnd;

    public Game(Player whitePlayer, Player blackPlayer) {
        timeStart = new Date().getTime();
        chessBoard = new ChessBoard();
        currentPlayerColor = Color.WHITE;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Integer getWinnerId() {
        return winnerId;
    }

    public Integer getLooserId() {
        return looserId;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public Long getTimeStart() {
        return timeStart;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public boolean move(int fromColumn, int fromRow, int toColumn, int toRow) {
        if(isFinished) return false;
        if (getCell(fromRow, fromColumn).getChess() == null) return false;
        if (currentPlayerColor != getCell(fromRow, fromColumn).getChess().getColor()) return false;

        if (chessBoard.move(fromRow, fromColumn, toRow, toColumn)) {
            changePlayer();
            stepCount++;
            return true;
        } else return false;
    }

    public boolean isEnded() {
        Color prevPlayerColor = currentPlayerColor == Color.BLACK ? Color.WHITE : Color.BLACK;
        isFinished = chessBoard.checkWin(prevPlayerColor);
        if(isFinished) {
            timeEnd = new Date().getTime();
            this.isFinished = true;
            switch (currentPlayerColor) {
                case BLACK -> {
                    blackPlayer.addWin();
                    winnerId = blackPlayer.getId();
                    whitePlayer.addDefeat();
                    looserId = whitePlayer.getId();
                }
                case WHITE -> {
                    whitePlayer.addWin();
                    winnerId = whitePlayer.getId();
                    blackPlayer.addDefeat();
                    looserId = blackPlayer.getId();
                }
            }
        }
        return isFinished;
    }

    public void surrenderCurrentPlayer() {

    }

    private Cell getCell(int row, int column) {
        return chessBoard.getCellList().get(row).get(column);
    }

    private void changePlayer() {
        if (currentPlayerColor == Color.WHITE) currentPlayerColor = Color.BLACK;
        else currentPlayerColor = Color.WHITE;
    }
}
