package risk.controller;

import risk.model.Game;
import risk.model.board.Board;
import risk.model.board.JsonBoardFactory;
import risk.model.phase.PhaseFactory;
import risk.view.RiskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Custom Map Controller listens to the user choosing to load a custom map. The custom
 * should be in json form and the user should enter the path
 *  @author Sarah Jaber
 *  @author Judy Hamwi
 *  @author Diana Miraflor
 *  @version 2.0
 */
public class CustomMapController implements ActionListener {

    private RiskView view;

    /**
     * creates the listener choosing to load a custom map
     * @param view
     */
    public CustomMapController(RiskView view) {
        this.view=view;
    }

    /**
     * listens to the user wanting to load a custom map. Prompts the user to enter
     * the file of the custom map they want to load and the creates the board
     * @param e clicking on the loading custom map menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String fileName = JOptionPane.showInputDialog(view.getRiskFrame(), "Enter File Path");
        Board board = new JsonBoardFactory(fileName).build();
          if (!board.isValidMap()) {
            view.handleInvalidMap();
        } else {
              board.authorizingAdjacentCountries();
              Game game = new Game(board, new PhaseFactory());
              view.handleLoadMap(game, board);
              game.addRiskView(view);
          }
    }
}
