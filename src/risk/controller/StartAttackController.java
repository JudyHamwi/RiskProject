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
 * Attack Controller listens to player's moves in the Attack Phase.
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */

public class StartAttackController implements ActionListener {
    private Game game;


    /**
     * Creates the Attack Controller that listens to the player's decisions in the attack phase
     */
    public StartAttackController(Game game ) {
        this.game = game;
    }

    /**
     * Responds to the action chosen by the user. It handles the country the player is attacking with,
     * the attack button that prepares the game to recieve the country being attacked, and handling
     * the country being attacked.
     * @param e the press of the button for attacker country, defender country, and attack button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        final User attacker = (User) game.getCurrentPlayer();
        attacker.wakeUser(null);
    }
}