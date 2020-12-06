package risk.controller;

import risk.model.Game;
import risk.model.board.Country;
import risk.model.phase.FortifyPhase;
import risk.model.player.User;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FortifyToController implements ActionListener {
    private final RiskView view;
    private final FortifyPhase fortifyPhase;
    private final Country country;
    private Game game;

    public FortifyToController(final RiskView riskView, Game game, final Country country, final FortifyPhase fortifyPhase) {
        view = riskView;
        this.fortifyPhase = fortifyPhase;
        this.country = country;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fortifyPhase.selectMovingTo(country)) {
            if (fortifyPhase.selectMovingTo(country)) {
                User user = (User) game.getCurrentPlayer();
                user.wakeUser(user);
                if (fortifyPhase.fortify()) {
                    view.handleFortifyToSelected();
                    game.runEndTurn();
                } else {
                    view.handleCanNotFortify();
                }
            }
        }
    }
}
