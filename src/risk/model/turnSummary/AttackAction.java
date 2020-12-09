package risk.model.turnSummary;

import risk.model.board.Country;
import risk.model.player.Player;

/**
 * AttackAction is a class that summarizes an AI's attacking move.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class AttackAction {
    private Country attackerCountry;
    private Player attacker;
    private Country defendingCountry;

    /**
     * Constructs a new attacking summary
     *
     * @param attackerCountry
     * @param attacker
     * @param defendingCountry
     */
    public AttackAction(Country attackerCountry, Player attacker, Country defendingCountry) {
        this.attackerCountry = attackerCountry;
        this.attacker = attacker;
        this.defendingCountry = defendingCountry;
    }

    /**
     * String representation of the attacking event
     *
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Attack Phase:\n");

        sb.append(attacker.toString() + " attacked " + defendingCountry + " from " +
                attackerCountry.toString()+ "\n");

        return sb.toString();
    }
}
