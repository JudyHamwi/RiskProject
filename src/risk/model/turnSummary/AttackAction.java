package risk.model.turnSummary;

import risk.model.board.Country;
import risk.model.player.Player;

public class AttackAction {
    private Country attackerCountry;
    private Player attacker;
    private Country defendingCountry;

    public AttackAction(Country attackerCountry, Player attacker, Country defendingCountry) {
        this.attackerCountry = attackerCountry;
        this.attacker = attacker;
        this.defendingCountry = defendingCountry;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Attack Phase:\n");

        sb.append(attacker.toString() + " attacked " + defendingCountry + " from " +
                attackerCountry.toString()+ "\n");

        return sb.toString();
    }
}
