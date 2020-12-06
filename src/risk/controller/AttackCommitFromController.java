package risk.controller;

import risk.model.Game;
import risk.model.GameState;
import risk.model.board.Country;
import risk.model.player.User;
import risk.view.BoardView;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Attack Phase Controller listens to player's moves to enter the attack phase
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 * @version 1.0
 */
public class AttackCommitFromController implements ActionListener {

    private Game game;

    /**
     * creates the attack phase controller to listen to entering  the attack phase
     *
     * @param game model that deals with the logic of the game
     */
    public AttackCommitFromController(Game game) {
        this.game = game;
    }

    /**
     * sets up the view to get ready to handle the attack phase
     *
     * @param e the press of the attack phase button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        User user = (User) game.getCurrentPlayer();
        user.wakeUser(GameState.ATTACK_PHASE);
    }
}