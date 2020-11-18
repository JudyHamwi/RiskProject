package RiskTestCases;

import RiskModel.Dice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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