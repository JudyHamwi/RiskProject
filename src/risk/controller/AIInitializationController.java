package risk.controller;

import risk.model.Game;
import risk.model.player.PlayerFactory;

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
    private final PlayerFactory playerFactory;
    private final Game gameModel;
    private final int aiCount;

    /**
     * creates the listener that listens to the number of players chosen to play the game
     * so it can enter the initialization phase
     * @param game model that deals with initializing the game
     * @param aiCount number of players playing in the game
     */
    public AIInitializationController(final Game game, final int aiCount, final PlayerFactory playerFactory){
        this.gameModel=game;
        this.aiCount = aiCount;
        this.playerFactory = playerFactory;
    }

    /**
     * calls the model to initialize the game  with the set up based on the  number of AI players selected
     * @param e selected AI player menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < aiCount; i++) {
            gameModel.addPlayer(playerFactory.createAI(gameModel.getBoard()));
        }
        gameModel.play();
    }
}