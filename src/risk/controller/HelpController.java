package risk.controller;

import risk.model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Help Controller listens to player's move of asking for help in the game.
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class HelpController implements ActionListener {
    private Game game;

    /**
     * creates the listener to listen to the user asking for help
     * @param game model that deals with the user asking for help
     */
    public HelpController(Game game) {
        this.game = game;
    }

    /**
     * responds to the users action of asking for help
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.printHelp();
    }
}