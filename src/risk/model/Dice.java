package risk.model;

import java.util.*;

/**
 * RISKModel.Dice that is rolled when the game is in the Attack Phase. The Defender and Attackr both roll the dice
 * when the attacker attacks a country to try and conquer it.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class Dice {
    private Random random;
    private Die die;

    /**
     * Creates a Dice.
     */
    public Dice() {
        this.random = new Random();
        this.die = new Die();
    }

    /**
     * Rolls the specified number of dice
     *
     * @return a queue of sorted values of the rolled Die
     */
    public PriorityQueue<Integer> roll(final int numberOfDice) {
        final PriorityQueue<Integer> values = new PriorityQueue<>(numberOfDice, (x, y) -> y - x);
        for (int i = 0; i < numberOfDice; i++) {
            values.add(die.rollDie());
        }
        return values;
    }
}