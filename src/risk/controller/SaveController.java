package risk.controller;

import org.junit.Before;
import risk.model.Game;
import risk.model.board.Board;
import risk.model.board.BoardRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveController implements ActionListener {

    private Game game;
    private Board board;

    public SaveController(Game game, Board board){
        this.game=game;
        this.board=board;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String fileName = JOptionPane.showInputDialog(this, "Enter File Name");
        BoardRepository repository = new BoardRepository("images");
        repository.saveMap("original",board);
    }
}
