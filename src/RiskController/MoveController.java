package RiskController;

import RiskModel.Country;
import RiskModel.Game;
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
public class MoveController implements ActionListener {

    private RiskView riskView;
    private Game game;
    private Country country;
    private int armiesMoved;

    public MoveController(RiskView riskView, Game game, Country country){
        this.riskView=riskView;
        this.game=game;
        this.country=country;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(riskView.getBoardView().getAttackButton().isEnabled()) && riskView.getBoardView().getMoveButton().isEnabled()) {
           String armies=JOptionPane.showInputDialog(this,"Number of Armies to move");
           armiesMoved=Integer.parseInt(armies);
           game.checkFortifyCountry(country, armiesMoved);
        }
            else if (!(riskView.getBoardView().getAttackButton().isEnabled()) && !(riskView.getBoardView().getMoveButton().isEnabled())
                     && !(riskView.getBoardView().getFortifyButton().isEnabled())){
                game.fortifyPhase(country, armiesMoved);
            }
        }

}
