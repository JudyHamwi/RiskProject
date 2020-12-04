package risk.view;

import risk.model.board.Country;
import risk.model.phase.FortifyPhase;
import risk.model.player.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FortifyToController implements ActionListener {
    private final RiskView view;
    private final User fortifier;
    private final FortifyPhase fortifyPhase;
    private final Country country;

    public FortifyToController(RiskView view, User fortifier, FortifyPhase fortifyPhase, Country country) {
        this.view = view;
        this.fortifier = fortifier;
        this.fortifyPhase = fortifyPhase;
        this.country = country;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fortifyPhase.selectMovingTo(country)) {
            view.handleFortifyToSelected();
            //fortifier.wakeUser();
        } else {
            // show some message
        }
    }
}
