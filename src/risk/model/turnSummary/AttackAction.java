package risk.model.turnSummary;

import risk.model.board.Country;
import risk.model.player.Player;

public class AttackAction {
    private Country attackerCountry;
    private Player attacker;
    private int attackerUnitsLost;
    private Country defendingCountry;
    private Player defender;
    private int defenderUnitsLost;
    private boolean defenderLost;
}
