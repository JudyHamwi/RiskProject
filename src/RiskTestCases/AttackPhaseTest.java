package RiskTestCases;

import RiskModel.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class AttackPhaseTest {
    AttackPhase attackPhase;
    Country attackerCountry;
    Country defenderCountry;
    List<Integer> attackerDiceValues;
    List<Integer> defenderDiceValues;
    Player player;
    Board board;
    Game game;
    @Before
    public void setUp() throws Exception {
        player = new Player();
        attackerCountry = new Country("Alaska");
        defenderCountry = new Country("Alberta");
        game = new Game();
        board = new Board();
        attackPhase = new AttackPhase(player,attackerCountry,defenderCountry);
        attackerDiceValues = new ArrayList<>();
        defenderDiceValues = new ArrayList<>();
    }

    @Test
    public void testAttackPhase(){
        assertTrue(attackPhase.equals(attackPhase));
    }

    @Test
    public void testnumberOfDiceForAttacker() {
        int attackerArmies = 1;
        attackPhase.setAttackerArmies(attackerArmies);
        assertEquals(1,attackPhase.numberOfDiceForAttacker());
    }

    @Test
    public void testnumberOfDiceForDefender() {
        int attackerArmies = 4;
        attackPhase.setAttackerArmies(attackerArmies);
        assertEquals(2,attackPhase.numberOfDiceForDefender());
    }

    @Test
    public void testcompareDice() {
        attackerDiceValues.add(6);
        attackerDiceValues.add(5);
        defenderDiceValues.add(4);
        defenderCountry.addArmy(2);
        attackPhase.setAttackerDiceValues(attackerDiceValues);
        attackPhase.setDefenderDiceValues(defenderDiceValues);
        attackPhase.compareDice();
        assertEquals(1,defenderCountry.getNumberOfArmies());
    }

    @Test
    public void testattack() {
        attackerDiceValues.add(6);
        attackerDiceValues.add(5);
        defenderDiceValues.add(4);
        defenderCountry.addArmy(2);
        attackPhase.setAttackerDiceValues(attackerDiceValues);
        attackPhase.setDefenderDiceValues(defenderDiceValues);
        attackPhase.compareDice();
        assertFalse(attackPhase.attack());
    }
}