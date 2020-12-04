package tst;
/**
 * One of the phases of the game. Player enters RISKModel.FortifyPhase after their RISKModel.AttackPhase.
 * @version 2.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 *
 * Test the Attack Phase class
 */
import risk.model.*;
import risk.model.board.Board;
import risk.model.board.Country;
import org.junit.Before;
import org.junit.Test;
import risk.model.phase.AttackPhase;
import risk.model.player.Player;

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
    public void testNumberOfDiceForAttacker() {
        int attackerArmies = 1;
        attackPhase.setAttackerArmies(attackerArmies);
        assertEquals(1,attackPhase.numberOfDiceForAttacker());
    }

    @Test
    public void testNumberOfDiceForDefender() {
        int attackerArmies = 4;
        attackPhase.setAttackerArmies(attackerArmies);
        assertEquals(2,attackPhase.numberOfDiceForDefender());
    }

    @Test
    public void testCompareDice() {
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
    public void testAttack() {
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