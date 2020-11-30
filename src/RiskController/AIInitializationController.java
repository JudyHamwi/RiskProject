package RiskController;

import RiskModel.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Initialization Controller listens to the  user decision on the number of AI players playing the game
 * to enter the initialization phase
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class AIInitializationController implements ActionListener {
    private Game gameModel;
    private int players;

    /**
     * creates the listener that listens to the number of players chosen to play the game
     * so it can enter the initialization phase
     * @param game model that deals with initializing the game
     * @param players number of players playing in the game
     */
    public AIInitializationController(Game game, int players){
        this.gameModel=game;
        this.players=players;
    }

    /**
     * calls the model to initialize the game  with the set up based on the  number of AI players selected
     * @param e selected AI player menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("AI InitializationController");
        gameModel.addAIPlayers(players);
        gameModel.theInitialState();
    }
}
