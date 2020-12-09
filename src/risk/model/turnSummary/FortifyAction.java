package risk.model.turnSummary;

import risk.model.board.Country;

/**
 * FortifyAction is a class that summarizes an AI's fortifying move.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class FortifyAction {
    private Country from;
    private Country to;

    /**
     * Constructs a new fortify summary
     *
     * @param from
     * @param to
     */
    public FortifyAction(Country from, Country to) {
        this.from = from;
        this.to = to;
    }

    /**
     * A string representation of the fortify event
     *
     * @return
     */
    public String toString() {
        return "Fortify Phase:\nArmies were fortified from " + from + " to " + to + "\n";
    }

}
