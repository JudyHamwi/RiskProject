package risk.controller;

import risk.model.board.Country;
import risk.model.Game;
import risk.model.GameState;
import risk.model.phase.FortifyPhase;
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
public class FortifyController implements ActionListener {

    private Game gameModel;
    private RiskView riskView;
    private FortifyPhase fortifyPhase;
    private Country country;
    private int armiesMoved;

    /**
     * Creates the Fortify Controller that listens to the player's decisions in the fortify phase
     *
     * @param rv
     * @param game
     */
    public FortifyController(RiskView rv, Game game,Country country,FortifyPhase fortifyPhase) {
        gameModel = game;
        riskView = rv;
        this.fortifyPhase=fortifyPhase;
        this.country=country;
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
        JButton b = (JButton) e.getSource();
        if (gameModel.getState() == GameState.FORTIFY_PHASE) {
            if (b.getName().equals("fortifyButton")) {
                b.setEnabled(false);
            } else if (riskView.getBoardView().getFortifyButton().isEnabled()) {
                fortifyPhase.setRiskViews(gameModel.getViews());
                String armiesFortify = JOptionPane.showInputDialog(riskView.getRiskFrame(), "Number of armies to fortify");
                armiesMoved = Integer.parseInt(armiesFortify);
                if(fortifyPhase.selectMovingFrom(country)){
                    fortifyPhase.setArmiesToMove(armiesMoved);
                    riskView.handleFortifyFromSelected(country);
                }else {
                    riskView.handleCanNotFortify();
                }
            } else {
                if(fortifyPhase.selectMovingTo(country)){
                    if(fortifyPhase.fortify()){
                        riskView.handleFortifyToSelected();
                        gameModel.runEndTurn();
                    }else {
                        riskView.handleCanNotFortify();
                    }
                }
            }
        }
    }
}

