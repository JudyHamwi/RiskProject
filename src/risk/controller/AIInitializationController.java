package risk.controller;

import risk.model.Game;
import risk.model.player.PlayerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * AI Initialization Controller listens to the  user decision on the number of AI players playing the game
 * to enter the initialization phase
 * @version 2.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class AIInitializationController implements ActionListener {
    private final PlayerFactory playerFactory;
    private final Game game;
    private final int aiCount;

    /**
     * creates the listener that listens to the number of AI players chosen to play in the game
     * so it can enter the initialization phase
     * @param game model that deals with initializing the game
     * @param aiCount number of players playing in the game
     * @param playerFactory to create the AI player
     */
    public AIInitializationController(final Game game, final int aiCount, final PlayerFactory playerFactory){
        this.game =game;
        this.aiCount = aiCount;
        this.playerFactory = playerFactory;
    }

    /**
     * creates the chosen number of AI players and starts the game
     * @param e selected AI player menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < aiCount; i++) {
            game.addPlayer(playerFactory.createAI(game.getBoard()));
        }
        game.play();
    }
}