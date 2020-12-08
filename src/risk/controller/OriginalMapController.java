package risk.controller;

import risk.model.Game;
import risk.model.board.Board;
import risk.model.board.JsonBoardFactory;
import risk.model.board.OriginalBoardFactory;
import risk.model.phase.PhaseFactory;
import risk.view.RiskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Orignial Mao Controller listens to the starting a new game with the original build in map
 * @version 2.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class OriginalMapController implements ActionListener {

    private RiskView view;

    /**
     * creates a listener to listen to the user starting a new game with the orginal map
     * @param rv view of the game containing the menu to start a new game
     */
    public OriginalMapController(RiskView rv){
        view = rv;
    }

    @Override
    /**
     * listens to the user creating a new game with the orginial map
     * @param e when the user presses the menu item to create a new game
     */
    public void actionPerformed(ActionEvent e) {
        Board board=new OriginalBoardFactory().build();
        Game game = new Game(board, new PhaseFactory());
        view.handleLoadMap(game, board);
        game.addRiskView(view);

    }
}