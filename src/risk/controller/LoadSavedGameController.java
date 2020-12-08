package risk.controller;

import risk.model.Game;
import risk.model.GameRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Load Saved Game Controller loads the game back to the previous saved state
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class LoadSavedGameController implements ActionListener {

    public LoadSavedGameController(){
    }

    /**
     * loads the previously saved game by the user
     * @param e choosing the load saved game menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        GameRepository repository = new GameRepository("images");
        repository.loadMap("savedGame");

    }
}
