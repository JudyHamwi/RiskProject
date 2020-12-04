package risk.model.player;

import risk.model.GameState;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.view.RiskView;

import javax.swing.*;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Semaphore;

/**
 * RISKModel.Player that plays in the RISKModel.Game.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class User implements Player {
    private final int id;
    private final List<RiskView> views;

    private Semaphore uiLock = new Semaphore(0);
    private Object responseFromUI = null;

    /**
     * RISKModel.Player that plays in the RISKModel.Game
     */
    public User(final int id, final List<RiskView> views) {
        this.id = id;
        this.views = views;
    }

    /**
     * Returns the id of the player
     *
     * @return the player's ID
     */
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void performDraft(final DraftPhase draftPhase) {
        views.forEach(v -> v.handleNewDraftPhase(this, draftPhase));
        while (draftPhase.haveArmiesToPlace()) {
            waitForUI();
            final Country selectedCountry = (Country) responseFromUI;
            views.forEach(v -> v.handleAddedArmy(selectedCountry, draftPhase.getArmiesToPlace()));
        }
    }

    @Override
    public void performAttack(final AttackPhase attackPhase) {

    }

    @Override
    public void performFortify(final FortifyPhase fortifyPhase) {
        while(true) {
            views.forEach(v -> v.handleNewFortifyPhase(this, fortifyPhase));
            waitForUI();
            final GameState selectedAction = (GameState) responseFromUI;
            switch (selectedAction) {
                case FORTIFY_PHASE:
                    views.forEach(v -> v.handleFortifyFromSelected(fortifyPhase.getMovingFrom()));
                    waitForUI();

                    final RiskView view = (RiskView) responseFromUI;
                    final int armiesToMove = view.getNumber(0, fortifyPhase.getMovingFrom().getNumberOfArmies() - 1, "Number of armies to move");
                    fortifyPhase.fortify(armiesToMove);
                    fortifyPhase.reset();
                    break;
                case END_TURN:
                    return;
            }
        }
    }

    public void wakeUser(final Object response) {
        uiLock.release();
        responseFromUI = response;
    }

    private void waitForUI() {
        try {
            responseFromUI = null;
            uiLock.acquire();
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Text represenation of the player
     *
     * @return String representation of the player
     */
    @Override
    public String toString() {
        return "User_" + getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Checks if two players are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }
}

