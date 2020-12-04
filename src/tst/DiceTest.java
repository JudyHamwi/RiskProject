package tst;

import risk.model.Dice;
import org.junit.Before;
import org.junit.Test;

public class DiceTest {
    Dice dice;
    int numDice;

    @Before
    public void setUp() throws Exception {
        numDice = 2;
        dice = new Dice(numDice);
    }

    @Test
    public void testroll() {
        assertEquals(dice.roll(), dice.getValues());
    }

}