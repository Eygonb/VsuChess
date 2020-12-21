package model.db.model;

public class GameStatistic {
    private Integer id;
    private Integer count_step;
    private Integer winner_id;
    private Integer looser_id;
    private Long time_start;
    private Long time_end;

    public GameStatistic() {

    }

    public GameStatistic(Integer id, Integer count_step, Integer winner_id, Integer looser_id, Long time_start, Long time_end) {
        this.id = id;
        this.count_step = count_step;
        this.winner_id = winner_id;
        this.looser_id = looser_id;
        this.time_start = time_start;
        this.time_end = time_end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount_step() {
        return count_step;
    }

    public void setCount_step(Integer count_step) {
        this.count_step = count_step;
    }

    public Integer getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(Integer winner_id) {
        this.winner_id = winner_id;
    }

    public Integer getLooser_id() {
        return looser_id;
    }

    public void setLooser_id(Integer looser_id) {
        this.looser_id = looser_id;
    }

    public Long getTime_start() {
        return time_start;
    }

    public void setTime_start(Long time_start) {
        this.time_start = time_start;
    }

    public Long getTime_end() {
        return time_end;
    }

    public void setTime_end(Long time_end) {
        this.time_end = time_end;
    }
}
