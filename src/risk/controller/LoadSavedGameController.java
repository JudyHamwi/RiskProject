package risk.controller;

import risk.model.Game;
import risk.model.GameRepository;
import risk.model.player.Player;
import risk.model.player.User;
import risk.view.RiskView;

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

    private Game game;
    private RiskView riskView;
    public LoadSavedGameController(RiskView riskView){
        this.riskView=riskView;
    }

    /**
     * loads the previously saved game by the user
     * @param e choosing the load saved game menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        GameRepository repository = new GameRepository("images");
        Game game=repository.loadGame();
        game.addLoadView(riskView);
        game.play();
    }
}
