package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import risk.model.Game;

/**
 * EndTurn Controller listens to player's move of ending their turn
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class EndTurnController implements ActionListener {
    private Game gameModel;

    /**
     * creates the listener that listens to the player ending their turn and passing the turn
     * to the next player
     * @param game model that responds to the logic of ending the turn
     */
    public EndTurnController(Game game) {
        this.gameModel = game;
    }

    /**
     * responds to the users action of ending their turn and passing it to the next player
     * @param e of user pressing the button to end their turn.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //gameModel.runEndTurn();
    }
}