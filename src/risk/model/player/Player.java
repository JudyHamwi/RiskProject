package risk.model.player;

import risk.model.GameState;
import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.view.RiskView;

import java.util.List;

/**
 * A player interface - AI and User implement this class
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public interface Player {
    int getId();
    GameState performDraft(DraftPhase draftPhase);
    GameState performAttack(AttackPhase attackPhase);
    void performFortify(FortifyPhase fortifyPhase);
    void performEndTurn(List<RiskView> views);
    void setID(int id);
}