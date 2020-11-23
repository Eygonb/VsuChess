package model.db;

import model.Game;
import model.Player;
import model.db.model.GameStatistic;

import java.sql.*;
import java.util.ResourceBundle;

public class DataStorage {
    private static final ResourceBundle resource = ResourceBundle.getBundle("DBConfig");

    private final String url = resource.getString("db.url");
    private final String user = resource.getString("db.user");
    private final String password = resource.getString("db.password");

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private Player savePlayerToDb(Player player) throws SQLException {
        try(Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("insert into player (name, win_count, defeat_count) values (?, ?, ?)");
            statement.setString(1, player.getName());
            statement.setInt(2, player.getWinCount());
            statement.setInt(3, player.getDefeatCount());
            statement.executeUpdate();

            statement = c.prepareStatement("select id from player where name=?");
            statement.setString(1, player.getName());

            ResultSet rs = statement.executeQuery();
            rs.next();

            player.setId(rs.getInt(1));
        }
        return player;
    }

    private Game saveGameToDb(Game game) throws SQLException {
        if(!game.isFinished()) return null;
        try(Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement(
                    "insert into game (count_step, winner_id, looser_id, time_start, time_end) values (?, ?, ?, ?, ?)");
            statement.setInt(1, game.getStepCount());
            statement.setInt(2, game.getWinnerId());
            statement.setInt(3, game.getLooserId());
            statement.setLong(4, game.getTimeStart());
            statement.setLong(5, game.getTimeEnd());
            statement.executeUpdate();
        }
        return game;
    }

    private Player getPlayerByNameFromBd(String name) throws SQLException{
        Player player = new Player(name);
        try(Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("select id, win_count, defeat_count from player where name=?");
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();
            rs.next();
            player.setId(rs.getInt(1));
            player.setWinCount(rs.getInt(2));
            player.setDefeatCount(rs.getInt(3));
        }
        return player;
    }

    public Player getPlayerByName(String name) {
        try {
            return getPlayerByNameFromBd(name);
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot get player", exception);
        }
    }

    private Player getPlayerByIdFromBd(Integer id) throws SQLException{
        Player player;
        try(Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("select name, win_count, defeat_count from player where id=?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            rs.next();
            player = new Player(rs.getString(1));
            player.setId(id);
            player.setWinCount(rs.getInt(2));
            player.setDefeatCount(rs.getInt(3));
        }
        return player;
    }

    public Player getPlayerById(Integer id) {
        try {
            return getPlayerByIdFromBd(id);
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot get player", exception);
        }
    }

    private GameStatistic getGameStatisticByIdFromBd(Integer id) throws SQLException{
        GameStatistic gameStatistic;
        try(Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("select id, count_step, winner_id, looser_id, " +
                    "time_start, time_end from game where id=?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            rs.next();
            gameStatistic = new GameStatistic();
            gameStatistic.setId(id);
            gameStatistic.setCount_step(rs.getInt(2));
            gameStatistic.setWinner_id(rs.getInt(3));
            gameStatistic.setLooser_id(rs.getInt(4));
            gameStatistic.setTime_start(rs.getInt(5));
            gameStatistic.setTime_end(rs.getInt(6));
        }
        return gameStatistic;
    }

    public GameStatistic getGameStatistic(Integer id) {
        try {
            return getGameStatisticByIdFromBd(id);
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot get player", exception);
        }
    }

    public Player savePlayer(Player player) {
        try {
            return savePlayerToDb(player);

        } catch (SQLException exception) {
            throw new RuntimeException("Cannot save player", exception);
        }
    }

    public Game saveGame(Game game) {
        try {
            return saveGameToDb(game);

        } catch (SQLException exception) {
            throw new RuntimeException("Cannot save game", exception);
        }
    }

}
