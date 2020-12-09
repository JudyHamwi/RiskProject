package risk.model.player;

import risk.model.GameState;
import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.view.RiskView;

import java.util.List;

public interface Player {
    int getId();
    GameState performDraft(DraftPhase draftPhase);
    GameState performAttack(AttackPhase attackPhase);
    void performFortify(FortifyPhase fortifyPhase);
    void performEndTurn(List<RiskView> views);
    void setID(int id);
}