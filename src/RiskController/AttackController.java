package RiskController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import RiskModel.Country;
import RiskModel.Game;
import RiskModel.GameState;
import RiskView.RiskView;

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
    private Game gameModel;
    private Country country;
    private RiskView riskView;


    /**
     * Creates the Attack Controller that listens to the player's decisions in the attack phase
     * @param riskView contains the buttons that the player makes the moves in
     * @param game model that deals with the logic of the attack phase
     * @param country that player selected to attack from or to if they are picking a country,
     *                or null if the player chooses the Attack button
     */
    public AttackController(RiskView riskView, Game game, Country country) {
        this.gameModel = game;
        this.country=country;
        this.riskView=riskView;
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
        if (gameModel.getState() == GameState.ATTACK_PHASE) {
            if (b.getName().equals("attackButton")) {
                riskView.handleNewAttack();
            } else if (riskView.getBoardView().getAttackButton().isEnabled()) {
                gameModel.checkAttackingCountry(country);
            } else {
                gameModel.attack(country);
            }
        }
    }
}
