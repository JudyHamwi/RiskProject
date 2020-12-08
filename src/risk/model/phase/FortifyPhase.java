package risk.model.phase;

import risk.model.board.Board;
import risk.model.board.Country;
import risk.model.player.Player;
import risk.view.RiskView;

import java.util.List;

/**
 * One of the phases of the game. Player enters RISKModel.FortifyPhase after their RISKModel.AttackPhase.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class FortifyPhase {
    static final int MIN_ARMIES_TO_FORTIFY_FROM = 2;

    private final Board board;
    private final Player player;
    private Country movingFrom;
    private Country movingTo;
    private int armiesToMove;

    /**
     * Constructor for a new fortify phase object
     *
     * @param player Player the current player
     */
    public FortifyPhase(final Board board, final Player player) {
        this.board = board;
        this.player = player;
    }

    public void reset() {
        movingFrom = null;
        movingTo = null;
    }

    public boolean initiated() {
        return movingFrom != null;
    }

    public boolean selectMovingFrom(final Country selectedCountry) {
        if (board.getCountriesOwnedBy(player).contains(selectedCountry)
                && !selectedCountry.getAdjacentCountries().isEmpty()
                && selectedCountry.getNumberOfArmies() >= MIN_ARMIES_TO_FORTIFY_FROM) {
            movingFrom = selectedCountry;
            System.out.println(movingFrom);
            return true;
        } else {
            System.out.format("Cannot move armies from %s as %s does not own it or the country does not have any allies\n",
                    selectedCountry, player);

            return false;
        }
    }

    public boolean selectMovingTo(final Country selectedCountry) {
        if (movingFrom.getConnectedCountries().contains(selectedCountry)) {
            movingTo = selectedCountry;
            return true;
        } else {
            System.out.format("Cannot move armies to %s as it is not connected to %s.\n", selectedCountry, movingFrom);
            return false;
        }
    }

    public Country getMovingFrom() {
        return movingFrom;
    }

    /**
     * Fortifies the number of armies the player wants to move to an adjacent country of their choice.
     *
     * @return
     */
    public boolean fortify() {
        if (checkNumOfArmies(armiesToMove)) {
            movingFrom.addArmies(-armiesToMove);
            movingTo.addArmies(armiesToMove);
            System.out.format("%s armies were moved from %s to %s\n", armiesToMove, movingFrom, movingTo);
            return true;
        } else {
            System.out.format("Not enough armies in %s to move %s armies\n", movingFrom, armiesToMove);
            return false;
        }
    }

    /**
     * Checks if the number of armies they want to move is greater than the number of armies
     * in the country they want to move from.
     *
     * @return boolean
     */
    public boolean checkNumOfArmies(int armiesToMove) {
        return movingFrom.getNumberOfArmies() > armiesToMove;
    }

    public void setArmiesToMove(int armiesToMove){
        this.armiesToMove=armiesToMove;
    }
}