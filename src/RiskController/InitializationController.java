package RiskController;

import RiskModel.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Initialization Controller listens to the  user decision on the number of players playing the game
 * to enter the initialization phase
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class InitializationController implements ActionListener {
    private Game gameModel;
    private int players;

    /**
     * creates the listener that listens to the number of players chosen to play the game
     * so it can enter the initialization phase
     * @param game model that deals with initializing the game
     * @param players number of players playing in the game
     */
    public InitializationController(Game game, int players){
        this.gameModel=game;
        this.players=players;
    }

    @Override
    /**
     * responds to the users action of picking the number of players to play the game so
     * the game enters the initialization phase
     */
    public void actionPerformed(ActionEvent e) {
        gameModel.setNumberOfPlayers(players);
        gameModel.theInitialState();
    }
}
