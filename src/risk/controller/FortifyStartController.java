package risk.controller;

import risk.model.Game;
import risk.model.GameState;
import risk.model.player.User;
import risk.view.BoardView;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fortify Start Game Controller listens to the user to have decided the country to fortify
 * from and now will decide the country to fortify to
 * @version 2.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class FortifyStartController implements ActionListener {

    private Game game;

    /**
     * creates the fortify starr controller to prepare the game to be ready
     * to choose the country to fortify to
     * @param game model that deals with the logic of the game
     */
    public FortifyStartController(Game game){
        this.game=game;
    }

    /**
     * sets up the game to be ready to choose the country to fortify to
     * @param e handles the press of the fortify button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        User user = (User) game.getCurrentPlayer();
        user.wakeUser(GameState.FORTIFY_PHASE);
    }

}
