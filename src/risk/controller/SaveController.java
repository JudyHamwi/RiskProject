package risk.controller;

import risk.model.Game;
import risk.model.GameRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveController implements ActionListener {

    private Game game;

    public SaveController(Game game){
        this.game=game;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //game.getBoard().unAuthorizingAdjacentCountries();
        GameRepository gameRepository = new GameRepository("images");
        gameRepository.saveGame(game);
    }
}
