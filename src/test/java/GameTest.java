import model.Color;
import model.Game;
import model.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    @Test
    public void TestKidWin() {
        Game game = new Game(new Player("John"), new Player("Dou"));
        Assert.assertTrue(game.move(4, 1, 4, 3));
        Assert.assertTrue(game.move(4, 6, 4, 4));
        Assert.assertTrue(game.move(5, 0, 2, 3));
        Assert.assertTrue(game.move(1, 7, 2, 5));
        Assert.assertTrue(game.move(3, 0, 7, 4));
        Assert.assertTrue(game.move(6, 7, 5, 5));
        Assert.assertTrue(game.move(7, 4, 5, 6));
        Assert.assertTrue(game.isEnded());

        Assert.assertSame(game.getCurrentPlayerColor(), Color.BLACK);
    }
}
