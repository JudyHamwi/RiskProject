package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import risk.model.Dice;
import risk.model.board.Country;
import risk.model.Game;
import risk.model.GameState;
import risk.model.phase.AttackPhase;
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

public class AttackController implements ActionListener {
    private RiskView riskView;
    private AttackPhase attackPhase;


    /**
     * Creates the Attack Controller that listens to the player's decisions in the attack phase
     * @param riskView contains the buttons that the player makes the moves in
     *                or null if the player chooses the Attack button
     */
    public AttackController(RiskView riskView,AttackPhase attackPhase ) {
        this.riskView=riskView;
        this.attackPhase=attackPhase;
    }

    /**
     * Responds to the action chosen by the user. It handles the country the player is attacking with,
     * the attack button that prepares the game to recieve the country being attacked, and handling
     * the country being attacked.
     * @param e the press of the button for attacker country, defender country, and attack button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
            riskView.handleNewAttack(attackPhase);
    }

}