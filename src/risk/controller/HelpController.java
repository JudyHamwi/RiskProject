package risk.controller;

import risk.model.Game;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Help Controller listens to player's move of asking for help in the game.
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class HelpController implements ActionListener {
    private Game game;
    private RiskView riskView;

    /**
     * creates the listener to listen to the user asking for help
     */
    public HelpController(RiskView riskView) {
        this.riskView = riskView;
    }

    /**
     * responds to the users action of asking for help
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        riskView.handlePrintHelp();
    }
}