package risk.controller;

import org.junit.Before;
import risk.model.Game;
import risk.model.GameRepository;
import risk.model.board.Board;
import risk.model.board.BoardRepository;
import risk.model.marshalling.GameMarshaller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Save Controller saves the states of the game
 */
public class SaveController implements ActionListener {

    private Game game;
    private Board board;

    /**
     * creates the listener to the save the state of the game
     * @param game
     * @param board
     */
    public SaveController(Game game, Board board){
        this.game=game;
        this.board=board;
    }

    /**
     * listens to the user choosing to save the state of the game
     * @param e pressing the save menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        GameRepository repository = new GameRepository("images");
        repository.saveMap("savedGame",game);
        //GameMarshaller marshaller=new GameMarshaller();
        //String saveGame=marshaller.toJson(game);

    }
}
