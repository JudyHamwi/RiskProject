package risk.model.player;

import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;

public interface Player {
    int getId();
    void performDraft(DraftPhase draftPhase);
    void performAttack(AttackPhase attackPhase);
    void performFortify(FortifyPhase fortifyPhase);
}