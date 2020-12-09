package risk.model;

import java.util.Random;

/**
 * this class represents one die with 6 sides and faces value ranges between 1 and 6.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class Die {
    static final int FACE_COUNT = 6; // maximum face value

    private Random random; // random generator

    /**
     * creates a new Die.
     */
    public Die() {
        this.random = new Random();
    }

    /**
     * rolls one die
     *
     * @return the face value when rolling one die
     */
    public int rollDie() {
        return random.nextInt(FACE_COUNT) + 1;
    }

}