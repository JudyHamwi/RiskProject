package risk.controller;

import risk.model.Game;
import risk.model.board.Board;
import risk.model.board.JsonBoardFactory;
import risk.model.board.OriginalBoardFactory;
import risk.model.phase.PhaseFactory;
import risk.view.RiskView;
import risk.view.RiskViewFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomMapController implements ActionListener {

    private RiskView view;
    public CustomMapController(RiskView view) {
        this.view=view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String fileName = JOptionPane.showInputDialog(this, "Enter File Path");
        Board board = new JsonBoardFactory(fileName).build();
        if (!board.isValidMap()) {
            view.handleInvalidMap();
        } else {
            Game game = new Game(board, new PhaseFactory());
            view.handleLoadMap(game, board);
            game.addRiskView(view);
        }
    }
}
