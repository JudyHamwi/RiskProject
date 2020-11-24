package RiskController;

import RiskModel.Country;
import RiskModel.Game;
import RiskModel.GameState;
import RiskView.RiskView;

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
public class FortifyController implements ActionListener {

    private Game gameModel;
    private RiskView riskView;
    private Country country;

    /**
     * Creates the Fortify Controller that listens to the player's decisions in the fortify phase
     * @param rv
     * @param game
     * @param c
     */
    public FortifyController(RiskView rv,Game game, Country c){
        gameModel=game;
        riskView=rv;
        country=c;
    }

    /**
     * Responds to the action chosen by the user. It handles the country the player is fortifying from,
     * the fortify button that prepares the game to recieve the country being fortifued to, and handling
     * the country being fortified to.
     * @param e the press of the button for fortifying country, fortifying to country, and fortify button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (gameModel.getState() == GameState.FORTIFY_PHASE) {
            if (b.getName().equals("fortifyButton")) {
                riskView.handleNewFortifyPhase();
            } else if (riskView.getBoardView().getFortifyButton().isEnabled()) {
                String armiesFortify=JOptionPane.showInputDialog(riskView.getRiskFrame(),"Number of armies to fortify");
                int armiesMoved=Integer.parseInt(armiesFortify);
                gameModel.checkFortifyCountry(country, armiesMoved);
            } else {
                gameModel.fortifyPhase(country);
            }
        }
        }

}
