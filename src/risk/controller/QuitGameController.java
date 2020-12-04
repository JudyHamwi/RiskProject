package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Quit Game Controller listens to the player quiting the game
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class QuitGameController implements ActionListener {

    /**
     * creates the listener that listens to quiting the game
     */
    public QuitGameController() {
    }

    /**
     * quits the game when the user chooses to the quit
     * @param e when the user click the quit game menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}