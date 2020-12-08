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

/**
 * Fortify From controller listens to the user picking the country to fortify from
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class FortifyFromController implements ActionListener {
    private final RiskView riskView;
    private final FortifyPhase fortifyPhase;
    private final Country country;
    private Game game;

    /**
     * creates the listener to choose the fortufying country
     * @param riskView view of the game
     * @param game model
     * @param country fortifying from
     * @param fortifyPhase of the game
     */
    public FortifyFromController(final RiskView riskView, Game game,final Country country, final FortifyPhase fortifyPhase) {
        this.riskView = riskView;
        this.fortifyPhase = fortifyPhase;
        this.country = country;
        this.game=game;
    }

    /**
     * listens to the user choosing the country to fortify from and prompts the user to enter
     * the number of armies to fortify with
     * @param e pressing the country to fortify from
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String armiesFortify = JOptionPane.showInputDialog(riskView.getRiskFrame(), "Number of armies to fortify");
        int armiesMoved = Integer.parseInt(armiesFortify);
        if (fortifyPhase.selectMovingFrom(country)) {
            User user=(User) game.getCurrentPlayer();
            user.wakeUser(GameState.FORTIFY_PHASE);
            fortifyPhase.setArmiesToMove(armiesMoved);
        }    else {
            riskView.handleCanNotFortify();
        }
    }
}