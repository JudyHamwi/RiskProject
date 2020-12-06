package risk.controller;

import risk.model.Game;
import risk.model.GameState;
import risk.view.BoardView;
import risk.view.RiskView;

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
    private RiskView riskView;

    /**
     * creates the fortify phase controller to prepare the game for the fortufy phase
     * @param game model that deals with the logic of the game
     * @param boardView view of the board
     */
    public FortifyPhaseController(Game game, BoardView boardView,RiskView riskView){
        this.game=game;
        this.boardView=boardView;
        this.riskView=riskView;
    }

    /**
     * sets up the view to get ready to handle the fortify phase
     * @param e handles the press of the fortify phase
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.setState(GameState.FORTIFY_PHASE);
        riskView.handleNewFortifyPhase(game.getCurrentPlayer());

    }

}
