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

    public FortifyController(RiskView rv,Game game, Country c){
        gameModel=game;
        riskView=rv;
        country=c;
    }

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
