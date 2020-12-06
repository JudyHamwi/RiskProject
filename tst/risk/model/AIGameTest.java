package risk.model;

import org.junit.Test;
import risk.model.board.Board;
import risk.model.board.OriginalBoardFactory;
import risk.model.phase.PhaseFactory;
import risk.model.player.PlayerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Runs a headless game completely ran by AI.
 */
public class AIGameTest {
    private static final int NUM_AI = 3;

    @Test
    public void runAIGame() {
        final Board board = new OriginalBoardFactory().build();
        final Game game = new Game(board, new PhaseFactory());

        final PlayerFactory playerFactory = new PlayerFactory();
        for (int i = 0; i < NUM_AI; i++) {
            game.addPlayer(playerFactory.createAI(board));
        }

        game.play();

        assertEquals(GameState.COMPLETED, game.getState());
    }
}
