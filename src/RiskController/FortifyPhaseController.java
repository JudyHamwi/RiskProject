package RiskController;

import RiskModel.Game;
import RiskModel.GameState;
import RiskView.BoardView;

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

    private Game game;
    private BoardView boardView;

    public FortifyPhaseController(Game game, BoardView boardView){
        this.game=game;
        this.boardView=boardView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.setPhase(GameState.FORTIFY_PHASE);
        boardView.getFortifyPhaseButton().setEnabled(false);
        boardView.getAttackButton().setEnabled(false);
        boardView.getFortifyButton().setEnabled(true);
    }

}
