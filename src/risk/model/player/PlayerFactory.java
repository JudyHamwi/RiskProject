package risk.model.player;

import risk.model.board.Board;
import risk.view.RiskView;

import java.util.List;

public class PlayerFactory {
    private int nextPlayerId = 1;

    public User createUser(final List<RiskView> views) {
        return new User(nextPlayerId++, views);
    }

    public AI createAI(final Board board) {
        return new AI(nextPlayerId++, board);
    }
}