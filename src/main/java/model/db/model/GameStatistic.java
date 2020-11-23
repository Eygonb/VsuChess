package model.db.model;

public class GameStatistic {
    private int id;
    private int count_step;
    private int winner_id;
    private int looser_id;
    private long time_start;
    private long time_end;

    public GameStatistic() {

    }

    public GameStatistic(int id, int count_step, int winner_id, int looser_id, long time_start, long time_end) {
        this.id = id;
        this.count_step = count_step;
        this.winner_id = winner_id;
        this.looser_id = looser_id;
        this.time_start = time_start;
        this.time_end = time_end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount_step() {
        return count_step;
    }

    public void setCount_step(int count_step) {
        this.count_step = count_step;
    }

    public int getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(int winner_id) {
        this.winner_id = winner_id;
    }

    public int getLooser_id() {
        return looser_id;
    }

    public void setLooser_id(int looser_id) {
        this.looser_id = looser_id;
    }

    public long getTime_start() {
        return time_start;
    }

    public void setTime_start(long time_start) {
        this.time_start = time_start;
    }

    public long getTime_end() {
        return time_end;
    }

    public void setTime_end(long time_end) {
        this.time_end = time_end;
    }
}
