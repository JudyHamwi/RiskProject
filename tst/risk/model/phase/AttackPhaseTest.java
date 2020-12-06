package risk.model.phase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import risk.model.Dice;
import risk.model.board.Country;
import risk.model.player.Player;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

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

@RunWith(MockitoJUnitRunner.class)
public class AttackPhaseTest {
    @Mock
    private Player mockPlayer;
    @Mock
    private Dice mockDice;

    @InjectMocks
    private AttackPhase attackPhase;

    private Country canada;
    private Country usa;

    @Before
    public void setup() {
        canada = new Country("Canada");
        canada.setCurrentOwner(mockPlayer);
        canada.setNumberOfArmies(AttackPhase.MIN_ARMIES_TO_ATTACK_WITH);

        usa = new Country("USA");
        usa.setNumberOfArmies(AttackPhase.MIN_ARMIES_TO_ATTACK_WITH);
        canada.addAdjacentCountry(usa);
    }

    @Test
    public void testSelectAttackingCountry_happy() {
        final boolean success = attackPhase.selectAttackingCountry(canada);

        assertTrue(success);
        assertEquals(canada, attackPhase.getAttackerCountry());
    }

    @Test
    public void testSelectAttackingCountry_notOwnCountry() {
        final boolean success = attackPhase.selectAttackingCountry(usa);

        assertFalse(success);
        assertNull(attackPhase.getAttackerCountry());
    }

    @Test
    public void testSelectAttackingCountry_notEnoughArmies() {
        canada.setNumberOfArmies(AttackPhase.MIN_ARMIES_TO_ATTACK_WITH - 1);

        final boolean success = attackPhase.selectAttackingCountry(canada);

        assertFalse(success);
        assertNull(attackPhase.getAttackerCountry());
    }

    @Test
    public void testSelectAttackingCountry_NoAdjacentEnemy() {
        usa.setCurrentOwner(mockPlayer);

        final boolean success = attackPhase.selectAttackingCountry(canada);

        assertFalse(success);
        assertNull(attackPhase.getAttackerCountry());
    }

    @Test
    public void testSelectDefendingCountry_happy() {
        attackPhase.selectAttackingCountry(canada);

        final boolean success = attackPhase.selectDefendingCountry(usa);

        assertTrue(success);
        assertEquals(canada, attackPhase.getAttackerCountry());
        assertEquals(usa, attackPhase.getDefenderCountry());
    }

    @Test
    public void testSelectDefendingCountry_ownCountry() {
        attackPhase.selectAttackingCountry(canada);
        usa.setCurrentOwner(mockPlayer);

        final boolean success = attackPhase.selectDefendingCountry(usa);

        assertFalse(success);
        assertNull(attackPhase.getDefenderCountry());
    }

    @Test
    public void testSelectDefendingCountry_notAdjacent() {
        attackPhase.selectAttackingCountry(canada);
        final Country finland = new Country("Finland");

        final boolean success = attackPhase.selectDefendingCountry(finland);

        assertFalse(success);
        assertNull(attackPhase.getDefenderCountry());
    }

    @Test
    public void testRunAttack_defenderSurvived() {
        attackPhase.selectAttackingCountry(canada);
        final int attackArmySize = canada.getNumberOfArmies();
        attackPhase.selectDefendingCountry(usa);
        final int defendArmySize = usa.getNumberOfArmies();

        final PriorityQueue<Integer> attackerRolls = createRolls(5, 3);
        final PriorityQueue<Integer> defenderRolls = createRolls(4, 3);

        when(mockDice.roll(anyInt())).thenReturn(attackerRolls).thenReturn(defenderRolls);

        final boolean defenderDefeated = attackPhase.runAttack();

        assertFalse(defenderDefeated);
        assertEquals(attackArmySize, canada.getNumberOfArmies());
        assertEquals(defendArmySize - 1, usa.getNumberOfArmies());
        assertEquals(mockPlayer, canada.getCurrentOwner());
        assertNull(usa.getCurrentOwner());
    }

    @Test
    public void testRunAttack_defenderDefeated() {
        attackPhase.selectAttackingCountry(canada);
        canada.setNumberOfArmies(5);
        final int attackArmySize = canada.getNumberOfArmies();
        attackPhase.selectDefendingCountry(usa);

        final PriorityQueue<Integer> attackerRolls = createRolls(5, 3);
        final PriorityQueue<Integer> defenderRolls = createRolls(4, 2);

        when(mockDice.roll(anyInt())).thenReturn(attackerRolls).thenReturn(defenderRolls);

        final boolean defenderDefeated = attackPhase.runAttack();

        assertTrue(defenderDefeated);
        assertEquals(1, canada.getNumberOfArmies());
        assertEquals(attackArmySize - 1, usa.getNumberOfArmies());
        assertEquals(mockPlayer, canada.getCurrentOwner());
        assertEquals(mockPlayer, usa.getCurrentOwner());
    }

    @Test(expected = IllegalStateException.class)
    public void testRunAttack_missingAttackingCountry() {
        attackPhase.runAttack();
    }

    @Test(expected = IllegalStateException.class)
    public void testRunAttack_missingDefendingCountry() {
        attackPhase.selectAttackingCountry(canada);
        attackPhase.runAttack();
    }

    @Test
    public void testResetPhase() {
        attackPhase.selectAttackingCountry(canada);
        attackPhase.selectDefendingCountry(usa);

        assertEquals(canada, attackPhase.getAttackerCountry());
        assertEquals(usa, attackPhase.getDefenderCountry());

        attackPhase.reset();

        assertNull(attackPhase.getAttackerCountry());
        assertNull(attackPhase.getDefenderCountry());
    }

    private PriorityQueue<Integer> createRolls(int... rolls) {
        final PriorityQueue<Integer> prioritizedRolls = new PriorityQueue<>();
        Arrays.stream(rolls).forEach(prioritizedRolls::add);
        return prioritizedRolls;
    }
}