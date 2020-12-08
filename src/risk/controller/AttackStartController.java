package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import risk.model.Dice;
import risk.model.board.Country;
import risk.model.Game;
import risk.model.GameState;
import risk.model.phase.AttackPhase;
import risk.model.player.User;
import risk.view.RiskView;

import javax.swing.*;

/**
 * Attack Start Controller listens to player's moves to enter the attack phase
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */

public class AttackStartController implements ActionListener {
    private Game game;


    /**
     * Creates the Attack Controller that listens to the player's decisions to enter
     * the attack phase
     */
    public AttackStartController(Game game ) {
        this.game = game;
    }

    /**
     * wakes the user to enter the attack phase It handles the country the player is attacking with,
     * @param e the press of the attack phase button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        final User attacker = (User) game.getCurrentPlayer();
        attacker.wakeUser(GameState.ATTACK_PHASE);
    }
}