package risk.model.player;

import org.junit.Test;
import risk.model.GameConstants;
import risk.model.board.Board;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {

    private final PlayerFactory factory = new PlayerFactory();
    private final Board board = new Board(List.of(), new GameConstants());

    @Test
    public void createUser() {
        final User player1 = factory.createUser(List.of());
        final User player2 = factory.createUser(List.of());

        assertEquals(0, player1.getId());
        assertEquals(1, player2.getId());
    }

    @Test
    public void createAI() {
        final AI player1 = factory.createAI(board);
        final AI player2 = factory.createAI(board);

        assertEquals(0, player1.getId());
        assertEquals(1, player2.getId());
    }

    @Test
    public void testMixed() {
        final User user = factory.createUser(List.of());
        final AI ai = factory.createAI(board);

        assertEquals(0, user.getId());
        assertEquals(1, ai.getId());
    }
}