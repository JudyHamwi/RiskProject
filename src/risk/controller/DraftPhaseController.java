package risk.controller;

import risk.model.Game;
import risk.model.GameState;
import risk.model.board.Country;
import risk.model.phase.DraftPhase;
import risk.model.player.User;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Draft Phase Controller listens to player's moves in the draft phase
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class DraftPhaseController implements ActionListener {
    private final RiskView view;
    private final User drafter;
    private final DraftPhase draftPhase;
    private final Country country;
    private Game game;

    /**
     * creates the draft phase controller to listen to entering  the draft phase
     *
     * @param country that id being drafted
     */
    public DraftPhaseController(final RiskView view, final User drafter, final Country country, Game game, DraftPhase draftPhase) {
        this.view = view;
        this.drafter = drafter;
        this.country = country;
        this.game = game;
        this.draftPhase = draftPhase;
        draftPhase.setRiskViews(game.getViews());
    }

    /**
     * calls the game to deal with the country being chosen to draft
     *
     * @param e country pressed to draft armies to
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        drafter.wakeUser(country);
    }
}
