import model.Game;
import model.Player;
import model.db.DataStorage;
import model.db.model.GameStatistic;
import org.junit.Assert;
import org.junit.Test;

public class DataStorageTest {
    @Test
    public void savePlayerTest() {
        DataStorage ds = new DataStorage();

        Player player = new Player("TestPlayerNAME");
        player.setWinCount(1);
        player.setDefeatCount(1);
        Player expected = ds.savePlayer(player);
        Player actual = ds.getPlayerByName("TestPlayerNAME");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void saveGameTest() {
        DataStorage ds = new DataStorage();

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        player1.setId(20);
        player2.setId(30);
        Game game = new Game(player1, player2);
        game.move(4, 1, 4, 3);
        game.move(4, 6, 4, 4);
        game.move(5, 0, 2, 3);
        game.move(1, 7, 2, 5);
        game.move(3, 0, 7, 4);
        game.move(6, 7, 5, 5);
        game.move(7, 4, 5, 6);
        game.isEnded();

        ds.saveGame(game);
        GameStatistic gameStatistic = ds.getLastGameStatistic(1).get(0);
        Assert.assertEquals(game.getStepCount(), gameStatistic.getCount_step());
        Assert.assertEquals(game.getWinnerId(), gameStatistic.getWinner_id());
        Assert.assertEquals(game.getLooserId(), gameStatistic.getLooser_id());
        Assert.assertEquals(game.getTimeStart(), gameStatistic.getTime_start());
        Assert.assertEquals(game.getTimeEnd(), gameStatistic.getTime_end());
    }

    @Test
    public void getPlayerById() {
        DataStorage ds = new DataStorage();

        Player player = ds.getPlayerById(-100000);
    }
}
