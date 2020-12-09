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
    private  int id;
    private final List<RiskView> views;

    private Semaphore uiLock = new Semaphore(0);
    private Object responseFromUI = null;


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

    /**
     * Contains the logic of a user drafting their bonus armies
     *
     * @param draftPhase
     * @return GameState the next game state
     */
    @Override
    public GameState performDraft(final DraftPhase draftPhase) {
        views.forEach(v -> v.handleNewDraftPhase(this, draftPhase));
        while (draftPhase.haveArmiesToPlace()) {
            waitForUI();
            final Country selectedCountry = (Country) responseFromUI;
            if(draftPhase.placeArmy(selectedCountry)){
                views.forEach(v-> v.handleAddedArmy(selectedCountry, draftPhase.getArmiesToPlace()));
            } else{
                views.forEach(v -> v.handleCanNotDraftFrom());
            }
        }
        views.forEach( v-> v.clearCountryButtons());
        waitForUI();
        return (GameState) responseFromUI;
    }

    /**
     * Contains the logic of a user attacking
     *
     * @param attackPhase
     * @return  GameState the next game state
     */
    @Override
    public GameState performAttack(final AttackPhase attackPhase) {
        while (true) {
            views.forEach(v -> v.handleNewAttackPhase(attackPhase));
            waitForUI();
            if (getNextState() != GameState.ATTACK_PHASE) {
                return getNextState();
            }

            views.forEach(v -> v.handleNewAttack(attackPhase, this));
            waitForUI();
            if (getNextState() != GameState.ATTACK_PHASE) {
                return getNextState();
            }

            final boolean defenderLost = attackPhase.runAttack();

            views.forEach(v -> v.handleAttackResult(attackPhase.getAttackerCountry(), defenderLost, attackPhase));
            attackPhase.reset();
        }
    }

    /**
     * Returns the next game state according to the user interface
     *
     * @return GameState
     */
    private GameState getNextState(){
        final GameState nextState = (GameState) responseFromUI;
        return nextState;
    }

    /**
     * Contains the logic of a user fortifying
     *
     * @param fortifyPhase
     */
    @Override
    public void performFortify(final FortifyPhase fortifyPhase) {
        //while(true) {
            views.forEach(v -> v.handleNewFortifyPhase(this,fortifyPhase));
            waitForUI();
            final GameState selectedAction = (GameState) responseFromUI;
            switch (selectedAction) {
                case FORTIFY_PHASE:
                    views.forEach(v -> v.handleFortifyFromSelected(fortifyPhase.getMovingFrom()));
                    System.out.println("enter fortify phase ");
                    waitForUI();
                    fortifyPhase.fortify();
                    System.out.println("1");
                    views.forEach(v-> v.handleFortifyToSelected());
                    System.out.println("2");
                    fortifyPhase.reset();
                    break;
                case END_TURN:
                    return;
            }
       // }
    }

    @Override
    public void performEndTurn(List<RiskView> views) {
    }

    /**
     * wakes up the user from sleep and sets a response.
     * @param response result of asynchronous processing
     */
    public void wakeUser(final Object response) {
        uiLock.release();
        responseFromUI = response;
    }

    /**
     * Puts the user to sleep and wait for the UI to wake him up.
     */
    private void waitForUI() {
        try {
            responseFromUI = null;
            uiLock.acquire();
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        /*responseFromUI = null;
        while(responseFromUI == null){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            System.out.println("waiting in loop");
        }*/
    }


    /**
     * Text representation of the player
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
        return this.id == user.id;
    }

    @Override
    public void setID(int id) {
        this.id=id;
    }

}

