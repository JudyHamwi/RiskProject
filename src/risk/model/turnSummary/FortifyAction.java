package risk.model.turnSummary;

import risk.model.board.Country;

public class FortifyAction {
    private Country from;
    private Country to;

    public FortifyAction(Country from, Country to) {
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "Fortify Phase:\nArmies were fortified from " + from + " to " + to;
    }

}
