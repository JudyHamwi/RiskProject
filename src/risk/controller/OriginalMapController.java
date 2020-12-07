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
 * New Game Controller listens to the starting a new game
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class OriginalMapController implements ActionListener {

    private RiskView view;

    /**
     * creates a listener to listen to the user starting a new game
     * @param rv view of the game containing the menu to start a new game
     */
    public OriginalMapController(RiskView rv){
        view = rv;
    }

    @Override
    /**
     * listens to the user creatnug a new game
     * @param e when the user presses the menu item to create a new game
     */
    public void actionPerformed(ActionEvent e) {
        Board board=new OriginalBoardFactory().build();
        Game game = new Game(board, new PhaseFactory());
        view.handleLoadMap(game, board);
        game.addRiskView(view);

    }
}