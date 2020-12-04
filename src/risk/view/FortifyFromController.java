package risk.view;

import risk.model.GameState;
import risk.model.board.Country;
import risk.model.phase.FortifyPhase;
import risk.model.player.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FortifyFromController implements ActionListener {
    private final RiskView riskView;
    private final User fortifier;
    private final FortifyPhase fortifyPhase;
    private final Country country;

    public FortifyFromController(final RiskView riskView, final User fortifier, final FortifyPhase fortifyPhase,
                                 final Country country) {
        this.riskView = riskView;
        this.fortifier = fortifier;
        this.fortifyPhase = fortifyPhase;
        this.country = country;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fortifyPhase.selectMovingFrom(country)) {
            fortifier.wakeUser(GameState.FORTIFY_PHASE);
        } else {
            // show something on UI
        }
    }
}