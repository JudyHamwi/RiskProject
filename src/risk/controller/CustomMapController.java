package risk.controller;

import risk.model.Game;
import risk.model.board.Board;
import risk.model.board.BoardRepository;
import risk.model.board.JsonBoardFactory;
import risk.model.phase.PhaseFactory;
import risk.view.RiskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Custom Map Controller listens to the user choosing to load a custom map. The custom
 * should be in json form and the user should enter the path
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class CustomMapController implements ActionListener {

    private RiskView view;

    /**
     * creates the listener choosing to load a custom map
     *
     * @param view
     */
    public CustomMapController(RiskView view) {
        this.view = view;
    }

    /**
     * listens to the user wanting to load a custom map. Prompts the user to enter
     * the file of the custom map they want to load and the creates the board
     *
     * @param e clicking on the loading custom map menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /*String fileName = JOptionPane.showInputDialog(view.getRiskFrame(), "Enter File Path");
        Board board = new JsonBoardFactory(fileName).build();
          if (!board.isValidMap()) {
            view.handleInvalidMap();
        } else {
              board.authorizingAdjacentCountries();
              Game game = new Game(board, new PhaseFactory());
              view.handleLoadMap(game, board);
              game.addRiskView(view);
          }*/
        BoardRepository mapRepo = new BoardRepository("maps");
        List<String> mapNames = mapRepo.getMapNames();
        String[] mapNameArray = mapNames.toArray(new String[mapNames.size()]);
        JComboBox<String> mapSelector = new JComboBox<>(mapNameArray);
        String[] options = {"Ok", "Cancel"};
        int selectedOption = JOptionPane.showOptionDialog(null,
                mapSelector,
                "Select a map!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]);

        if (selectedOption == 0) {
            final String mapName = (String) mapSelector.getSelectedItem();
            Board board = mapRepo.loadMap(mapName);

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
}
