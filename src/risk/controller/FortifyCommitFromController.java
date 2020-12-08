package risk.controller;

import risk.model.board.Country;
import risk.model.Game;
import risk.model.GameState;
import risk.model.phase.FortifyPhase;
import risk.model.player.User;
import risk.view.ContinentView;
import risk.view.RiskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Move Controller listens to the user entering the fortify phase
 * @version 2.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class FortifyCommitFromController implements ActionListener {

    private final FortifyPhase fortifyPhase;
    private Game game;
    private RiskView view;

    /**
     * Creates the Fortify Controller that listens to the player's decisions to enter the  fortify phase
     */
    public FortifyCommitFromController(Game game, RiskView view, FortifyPhase fortifyPhase) {
        this.game = game;
        this.view = view;
        this.fortifyPhase = fortifyPhase;
    }

    /**
     * Responds to the action chosen by the user to enter the fortify phase
     *
     * @param e the press of the button of the fortify phase button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //User user = (User) game.getCurrentPlayer();
        //user.wakeUser(view);
        view.handleNewFortify(fortifyPhase);
    }
}

