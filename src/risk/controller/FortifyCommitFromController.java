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
 * Move Controller listens to the user moving the armies in the fortify phase
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class FortifyCommitFromController implements ActionListener {

    private final FortifyPhase fortifyPhase;
    private Game game;
    private RiskView view;

    /**
     * Creates the Fortify Controller that listens to the player's decisions in the fortify phase
     */
    public FortifyCommitFromController(Game game, RiskView view, FortifyPhase fortifyPhase) {
        this.game = game;
        this.view = view;
        this.fortifyPhase = fortifyPhase;
    }

    /**
     * Responds to the action chosen by the user. It handles the country the player is fortifying from,
     * the fortify button that prepares the game to recieve the country being fortifued to, and handling
     * the country being fortified to.
     *
     * @param e the press of the button for fortifying country, fortifying to country, and fortify button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //User user = (User) game.getCurrentPlayer();
        //user.wakeUser(view);
        view.handleNewFortify(fortifyPhase);
    }
}

