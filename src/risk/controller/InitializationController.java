package risk.controller;

import risk.model.Game;
import risk.model.player.PlayerFactory;
import risk.view.RiskView;
import risk.view.RiskViewFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Initialization Controller listens to the  user decision on the number of players playing the game
 * to enter the initialization phase
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class InitializationController implements ActionListener {
    private final RiskView view;
    private final Game game;
    private final int players;
    private final PlayerFactory playerFactory;

    /**
     *creates the listener that listens to the number of players chosen to play the game
     * so it can enter the initialization phase
     * @param view of the game
     * @param game model
     * @param players humans playing in the game
     * @param playerFactory to create the players
     */
    public InitializationController(final RiskView view, final Game game, final int players,
                                    final PlayerFactory playerFactory) {
        this.view = view;
        this.game = game;
        this.players = players;
        this.playerFactory = playerFactory;
    }

    /**
     * responds to the users action of picking the number of players to play the game
     * @e choosing the number of players from the menu
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        for (int i = 0; i < players; i++) {
            game.addPlayer(playerFactory.createUser(game.getViews()));
        }
        view.handleSetNumOfAIPlayers(players);
    }
}