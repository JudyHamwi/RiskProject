package RiskController;

import RiskModel.Game;
import RiskModel.GameState;
import RiskView.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Attack Phase Controller listens to player's moves to enter the attack phase
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class AttackPhaseController implements ActionListener {

    private Game game;
    private BoardView boardView;

    /**
     * creates the attack phase controller to listen to entering  the attack phase
     * @param game model that deals with the logic of the game
     * @param boardView board in the view
     */
    public AttackPhaseController(Game game, BoardView boardView){
        this.game=game;
        this.boardView=boardView;
    }

    /**
     * sets up the view to get ready to handle the attack phase
     * @param e the press of the attack phase button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.setPhase(GameState.ATTACK_PHASE);
        boardView.getAttackPhaseButton().setEnabled(false);
        boardView.getAttackButton().setEnabled(true);
        boardView.getFortifyPhaseButton().setEnabled(true);

    }
}
