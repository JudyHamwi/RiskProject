package risk.controller;

import risk.model.Game;
import risk.model.GameState;
import risk.model.board.Country;
import risk.model.phase.FortifyPhase;
import risk.model.player.User;
import risk.view.RiskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FortifyFromController implements ActionListener {
    private final RiskView riskView;
    private final FortifyPhase fortifyPhase;
    private final Country country;
    private Game game;

    public FortifyFromController(final RiskView riskView, Game game,final Country country, final FortifyPhase fortifyPhase) {
        this.riskView = riskView;
        this.fortifyPhase = fortifyPhase;
        this.country = country;
        this.game=game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fortifyPhase.setRiskViews(game.getViews());
        String armiesFortify = JOptionPane.showInputDialog(riskView.getRiskFrame(), "Number of armies to fortify");
        int armiesMoved = Integer.parseInt(armiesFortify);
        if (fortifyPhase.selectMovingFrom(country)) {
            User user=(User) game.getCurrentPlayer();
            user.wakeUser(GameState.FORTIFY_PHASE);
            fortifyPhase.setArmiesToMove(armiesMoved);
            riskView.handleFortifyFromSelected(country,fortifyPhase);
        }    else {
            riskView.handleCanNotFortify();
        }
    }
}