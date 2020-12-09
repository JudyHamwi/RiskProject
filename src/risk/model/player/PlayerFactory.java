package risk.model.player;

import risk.model.board.Board;
import risk.view.RiskView;

import java.util.List;

/**
 * This class instantiates either a new AI or a new User
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class PlayerFactory {
    private int nextPlayerId = 0;

    /**
     * Creates a new user
     *
     * @param views
     * @return User
     */
    public User createUser(final List<RiskView> views) {

        return new User(nextPlayerId++, views);
    }

    /**
     * Creates a new AI
     *
     * @param board
     * @return AI
     */
    public AI createAI(final Board board) {
        return new AI(nextPlayerId++, board);
    }
}