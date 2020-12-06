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
 * @author Walid Baitul
 * @version 1.0
 */
public class DraftPhaseController implements ActionListener {
    private final RiskView view;
    private final User drafter;
    private final DraftPhase draftPhase;
    private final Country country;
    private  Game game;

    /**
     * creates the attack phase controller to listen to entering  the attack phase
     *
     * @param country that id being drafted
     */
    public DraftPhaseController(final RiskView view, final User drafter, final Country country, Game game,DraftPhase draftPhase) {
        this.view = view;
        this.drafter = drafter;
        this.country = country;
        this.game=game;
        this.draftPhase=draftPhase;
        draftPhase.setRiskViews(game.getViews());
    }

    /**
     * calls the game to deal with the country being chosen to draft
     *
     * @param e country pressed to draft armies to
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.getState() == GameState.DRAFT_PHASE) {
            if (draftPhase.haveArmiesToPlace()) {
                if (draftPhase.placeArmy(country)) {
                    drafter.wakeUser(country);
                    view.handleAddedArmy(country, draftPhase.getArmiesToPlace());
                } else {
                    view.handleCanNotDraftFrom();
                }
            }
        }
    }
    }
