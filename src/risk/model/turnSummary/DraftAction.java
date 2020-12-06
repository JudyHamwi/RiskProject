package risk.model.turnSummary;

import risk.model.board.Country;

/**
 * Records the Draft action
 */
public class DraftAction {
    private Country country;

    public DraftAction(Country country) {
        this.country = country;
    }

    public String toString() {
        return "Draft Phase:\n" + country.toString() + " was drafted.";
    }
}
