package risk.controller;

import risk.model.Game;
import risk.model.GameRepository;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveController implements ActionListener {

    private Game game;

    public SaveController(Game game){
        this.game=game;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        GameRepository gameRepository = new GameRepository("savedGame");
        gameRepository.saveGame(game);
        System.exit(0);
    }
}
