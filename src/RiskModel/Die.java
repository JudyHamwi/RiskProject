package RiskModel;

import java.util.Random;

/**
 * this class represents one die with 6 sides and faces value ranges between 1 and 6
 * @version 1.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class Die {
    private final int MAX_FACES = 6; // maximum face value
    private int faceValue; // current value produced by rolling the die
    private Random random; // random generator


    /**
     * creates a new RISKModel.Die
     */
    public Die(){
        this.random = new Random();
        this.faceValue = 1;
    }

    /**
     * retrieve the value of the last roll
     * @return the current face value of the RISKModel.Die object
     */
    public int getFaceValue(){
        return this.faceValue;
    }

    /**
     * rolls one die
     * @return the face value when rolling one die
     */
    public int rollDie(){
        faceValue = random.nextInt(MAX_FACES) + 1;
        return faceValue;
    }

    /**
     * Text representation of the die
     * @return a string representation of the rolled die.
     */
    public String toString(){
        return Integer.toString(this.getFaceValue());
    }

}
