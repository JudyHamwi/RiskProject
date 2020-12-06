package risk.controller;

import risk.model.Game;
import risk.model.GameState;
import risk.view.BoardView;
import risk.view.RiskView;

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
    private RiskView riskView;

    /**
     * creates the attack phase controller to listen to entering  the attack phase
     * @param game model that deals with the logic of the game
     * @param boardView board in the view
     */
    public AttackPhaseController(Game game, BoardView boardView, RiskView riskView){
        this.game=game;
        this.boardView=boardView;
        this.riskView=riskView;
    }

    /**
     * sets up the view to get ready to handle the attack phase
     * @param e the press of the attack phase button
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    //Add selectAttacker and selectDefender controllers
}