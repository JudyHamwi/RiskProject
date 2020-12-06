package risk.model.turnSummary;

import risk.model.board.Country;
import risk.model.player.Player;

public class AttackAction {
    private Country attackerCountry;
    private Player attacker;
    private int attackerUnitsLost;
    private Country defendingCountry;
    private int defenderUnitsLost;
    private boolean defenderLost;

    public AttackAction(Country attackerCountry, Player attacker, int attackerUnitsLost,
                        Country defendingCountry, int defenderUnitsLost, boolean defenderLost) {
        this.attackerCountry = attackerCountry;
        this.attacker = attacker;
        this.attackerUnitsLost = attackerUnitsLost;
        this.defendingCountry = defendingCountry;
        this.defenderUnitsLost = defenderUnitsLost;
        this.defenderLost = defenderLost;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Attack Phase:\n");

        if (!defenderLost) {
            sb.append(attacker.toString() + " did not successfully attack " + defendingCountry + " from " +
                    attackerCountry.toString() + ". \n Attacking units lost: " + attackerUnitsLost +
                    "\n Defender units lost: " + defenderUnitsLost);
        } else {
            sb.append(attacker.toString() + " attacked " + defendingCountry + " from " +
                    attackerCountry.toString() + ". \n Attacking units lost: " + attackerUnitsLost +
                    "\n Defender units lost: " + defenderUnitsLost);
        }

        return sb.toString();
    }
}
