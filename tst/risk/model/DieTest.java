package risk.model;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

public class DieTest {
    final Die die = new Die();

    @Test
    public void testRollDie() {
        IntStream.of(1000).forEach(i -> {
            final int value = die.rollDie();

            assertTrue(value <= Die.FACE_COUNT);
            assertTrue(value >= 1);
        });

    }
}