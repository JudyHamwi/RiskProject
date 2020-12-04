package risk.model.phase;

import risk.model.Dice;
import risk.model.Game;

public class PhaseFactory {

    public DraftPhase buildDraftPhase(final Game game) {
        return new DraftPhase(game.getBoard(), game.getCurrentPlayer());
    }

    public AttackPhase buildAttackPhase(final Game game) {
        return new AttackPhase(game.getCurrentPlayer(), new Dice());
    }

    public FortifyPhase buildFortifyPhase(final Game game) {
        return new FortifyPhase(game.getBoard(), game.getCurrentPlayer());
    }
}