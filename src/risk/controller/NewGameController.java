package risk.controller;

import risk.model.Game;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * New Game Controller listens to the starting a new game
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class NewGameController implements ActionListener {

    private Game game;
    private RiskView rv;

    /**
     * creates a listener to listen to the user starting a new game
     * @param rv view of the game containing the menu to start a new game
     * @param game model that deals with creating a new game
     */
    public NewGameController(RiskView rv, Game game){
        this.rv = rv;
        this.game=game;
    }

    @Override
    /**
     * listens to the user creatnug a new game
     * @param e when the user presses the menu item to create a new game
     */
    public void actionPerformed(ActionEvent e) {
        game.addRiskView(rv);
    }
}