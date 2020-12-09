package risk.model.phase;

import risk.model.Dice;
import risk.model.Game;

public class PhaseFactory {

    /**
     * Creates a new DraftPhase
     *
     * @param game
     * @return DraftPhase a new DraftPhase object
     */
    public DraftPhase buildDraftPhase(final Game game) {
        return new DraftPhase(game.getBoard(), game.getCurrentPlayer());
    }

    /**
     * Creates a new AttackPhase
     *
     * @param game
     * @return AttackPhase a new AttackPhase object
     */
    public AttackPhase buildAttackPhase(final Game game) {
        return new AttackPhase(game.getCurrentPlayer(), new Dice());
    }

    /**
     * Creates a new FortifyPhase
     *
     * @param game
     * @return FortifyPhase a new FortifyPhase object
     */
    public FortifyPhase buildFortifyPhase(final Game game) {
        return new FortifyPhase(game.getBoard(), game.getCurrentPlayer());
    }
}