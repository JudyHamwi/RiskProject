package risk.controller;

import risk.model.Game;
import risk.model.GameState;
import risk.view.BoardView;

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

    /**
     * creates the fortify phase risk.controller to prepare the game for the fortufy phase
     * @param game risk.model that deals with the logic of the game
     * @param boardView risk.view of the board
     */
    public FortifyPhaseController(Game game, BoardView boardView){
        this.game=game;
        this.boardView=boardView;
    }

    /**
     * sets up the risk.view to get ready to handle the fortify phase
     * @param e handles the press of the fortify phase
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.setPhase(GameState.FORTIFY_PHASE);
        boardView.getFortifyPhaseButton().setEnabled(false);
        boardView.getAttackButton().setEnabled(false);
        boardView.getFortifyButton().setEnabled(true);
    }

}
