package risk.model.turnSummary;

import risk.model.board.Country;

/**
 * DraftAction is a class that records an AI's drafting move.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class DraftAction {
    private Country country;

    /**
     * Constructs a new drafting record
     *
     * @param country
     */
    public DraftAction(Country country) {
        this.country = country;
    }

    /**
     * String representation of the drafting event
     *
     * @return String
     */
    public String toString() {
        return "Draft Phase:\n" + country.toString() + " was drafted.";
    }
}
