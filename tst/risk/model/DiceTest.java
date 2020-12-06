package risk.model;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiceTest {
    final int numDice = 10;
    final Dice dice = new Dice();

    @Test
    public void testRoll() {
        final PriorityQueue<Integer> roll = dice.roll(numDice);

        assertEquals(numDice, roll.size());

        int left = roll.poll();

        while (!roll.isEmpty()) {
            int right = roll.poll();
            assertTrue(left >= right);
            left = right;
        }
    }

}