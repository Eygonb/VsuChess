public class Cell {
    private final String name;
    private ChessMan chess;

    public Cell(String name, ChessMan chess) {
        this.name = name;
        this.chess = chess;
    }

    public void setChess(ChessMan chess) {
        this.chess = chess;
    }

    public String getName() {
        return name;
    }

    public ChessMan getChess() {
        return chess;
    }
}
