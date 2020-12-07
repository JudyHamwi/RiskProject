package risk.model.phase;

import risk.model.Dice;
import risk.model.board.Country;
import risk.model.player.Player;
import risk.view.RiskView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * One of the Phases of the RISKModel.Game, the Attack Phase. This Phase is entered when a player attacks a RISKModel.Country.
 *
 * @author Sarah Jaber
 * @author Walid Baitul
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */

public class AttackPhase {
    static final int MAX_DEFENDING_ARMIES = 2;
    static final int MAX_ATTACKING_ARMIES = 3;
    static final int MIN_ARMIES_TO_ATTACK_WITH = 2;

    private Player attacker;
    private final Dice dice;

    Country attackerCountry;
    Country defenderCountry;


    private int attackingArmiesLost;
    private int defendingArmiesLost;

    /**
     * Constructor of Attack Phase initializes the fields.
     */
    public AttackPhase(Player attacker, final Dice dice) {
        this.attacker = attacker;
        this.dice = dice;
        this.attackingArmiesLost = 0;
        this.defendingArmiesLost = 0;
    }

    public boolean selectAttackingCountry(Country selectedCountry) {
        if (isValidAttackingCountry(selectedCountry)) {
            attackerCountry = selectedCountry;
            System.out.println(attackerCountry);
            return true;
        } else {
            System.out.format("%s cannot be selected as the country needs to be owned by %s and have at least %s armies\n",
                    selectedCountry, attacker, MIN_ARMIES_TO_ATTACK_WITH);
            return false;
        }
    }

    public boolean selectDefendingCountry(Country selectedCountry) {
        Player attacker = attackerCountry.getCurrentOwner();
        Player defender = selectedCountry.getCurrentOwner();

        if (attackerCountry.isAdjacent(selectedCountry) && !attacker.equals(defender)) {
            defenderCountry = selectedCountry;
            return true;
        } else {
            System.out.format("%s cannot be selected as the country needs to be adjacent to %s and not be owned by %s\n",
                    selectedCountry, attackerCountry, attacker);

            return false;
        }
    }

    /**
     * Compare the Roll of RISKModel.Dice of the Attacker and Defender. Remove armies of the losing country.
     */
    public boolean runAttack() {
        if (attackerCountry == null || defenderCountry == null) {
            throw new IllegalStateException("Both attacker country and defender country should be selected.");
        }

        final PriorityQueue<Integer> attackerRoll = rollAttackerDice();
        final PriorityQueue<Integer> defenderRoll = rollDefenderDice();

        while (!attackerRoll.isEmpty() && !defenderRoll.isEmpty()) {
            final int attackerValue = attackerRoll.poll();
            final int defenderValue = defenderRoll.poll();

            if (attackerValue > defenderValue) {
                defenderCountry.addArmies(-1);
                defendingArmiesLost += 1;
            } else if (defenderValue > attackerValue) {
                attackerCountry.addArmies(-1);
                attackingArmiesLost += 1;
            }
        }
        return transferArmiesIfDefenderDefeated();
    }

    public int getAttackingArmiesLost() {
        return attackingArmiesLost;
    }

    public int getDefendingArmiesLost() {
        return defendingArmiesLost;
    }

    public void reset() {
        attackerCountry = null;
        defenderCountry = null;
    }

    public Country getAttackerCountry() {
        return attackerCountry;
    }

    public Country getDefenderCountry() {
        return defenderCountry;
    }

    public boolean isValidAttackingCountry(Country selectedCountry){
        return selectedCountry.isOwnedBy(attacker) && selectedCountry.getNumberOfArmies() >= MIN_ARMIES_TO_ATTACK_WITH
                && hasAdjacentEnemy(selectedCountry);
    }


    private boolean hasAdjacentEnemy(final Country attackerCountry) {
        return attackerCountry.getAdjacentCountries().stream()
                .map(Country::getCurrentOwner)
                .anyMatch(defender -> !Objects.equals(attacker, defender));
    }

    /**
     * Calculate the number of RISKModel.Dice that need to be rolled for the Attack. The number of dice
     * used to attack is equal to the number of armies attacking. The number is calculated
     * with the maximum number of armies attacking.
     *
     * @return number of dice rolled for the attack
     */
    PriorityQueue<Integer> rollAttackerDice() {
        final int numberOfDice = Math.min(attackerCountry.getNumberOfArmies() - 1, MAX_ATTACKING_ARMIES);
        return dice.roll(numberOfDice);
    }

    /**
     * Calculate teh number of dice to be rolled for the defender country. The number of dice represent the number
     * if armies defending the country. If the number of armies is only one, the defender can only roll one deice,
     * else the defender can roll two dice
     *
     * @return number of dice rolled by the defender
     */
    PriorityQueue<Integer> rollDefenderDice() {
        final int numberOfDice = Math.min(defenderCountry.getNumberOfArmies(), MAX_DEFENDING_ARMIES);
        return dice.roll(numberOfDice);
    }

    private boolean transferArmiesIfDefenderDefeated() {
        if (defenderCountry.getNumberOfArmies() < 1) {
            defenderCountry.setCurrentOwner(attackerCountry.getCurrentOwner());

            final int armiesToMove = attackerCountry.getNumberOfArmies() - 1;
            attackerCountry.addArmies(-armiesToMove);
            defenderCountry.addArmies(armiesToMove);

            System.out.format("%s has conquered %s and is occupied by %s armies\n", attackerCountry.getCurrentOwner(),
                    defenderCountry, defenderCountry.getNumberOfArmies());
            return true;
        } else {
            System.out.format("%s was not conquered and has %s remaining armies\n", defenderCountry,
                    defenderCountry.getNumberOfArmies());
            return false;
        }
    }

}