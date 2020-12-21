package model;

import java.util.Objects;

public class Player {
    private Integer id;
    private final String name;
    private Integer winCount = 0;
    private Integer defeatCount = 0;

    public Player(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public Integer getDefeatCount() {
        return defeatCount;
    }

    public void addWin() {
        winCount++;
    }

    public void addDefeat() {
        defeatCount++;
    }

    public void setWinCount(Integer winCount) {
        this.winCount = winCount;
    }

    public void setDefeatCount(Integer defeatCount) {
        this.defeatCount = defeatCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(winCount, that.winCount) &&
                Objects.equals(defeatCount, that.defeatCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, winCount, defeatCount, id);
    }
}
