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
 * Attack Commit Controller listens to player's decision that the player chose the
 * attacking country and now will begin to choose the defending country
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class AttackCommitFromController implements ActionListener {

    private Game game;

    /**
     * creates the attack commit controller to listen to be ready to choose the
     * defending country
     * @param game model that deals with the logic of the game
     */
    public AttackCommitFromController(Game game) {
        this.game = game;
    }

    /**
     * wakes the player to get ready to choose the defending country
     *
     * @param e the press of the attack button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        User user = (User) game.getCurrentPlayer();
        user.wakeUser(GameState.ATTACK_PHASE);
    }
}