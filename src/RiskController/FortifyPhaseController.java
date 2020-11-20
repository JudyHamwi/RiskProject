package RiskController;

import RiskModel.Game;
import RiskModel.GameState;
import RiskView.RiskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Fortify Phase Game Controller listens to the user entering the fortify phase
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class FortifyPhaseController implements ActionListener {

    private RiskView riskView;
    private Game game;
    private JButton fortifyButton;
    private JButton moveButton;

    public FortifyPhaseController(Game game){
        this.riskView=riskView;
        this.game=game;
        this.fortifyButton=fortifyButton;
        this.moveButton=moveButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.setPhase(GameState.FORTIFY_PHASE);

    }
}
