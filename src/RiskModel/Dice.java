package RiskModel;

import java.util.*;

/**
 * RISKModel.Dice that is rolled when the game is in the Attack Phase. The Defender and Attackr both roll the dice
 * when the attacker attacks a country to try and conquer it.
 * @version 1.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class Dice {
    private Random random;
    private Die die;
    private int numberOfDice;
    private PriorityQueue<Integer> values;

    /**
     * Creates a dice with a specified number of RISKModel.Die
     * @param numberOfDice to be rolled by the attacker
     */
    public Dice (int numberOfDice){
        this.random = new Random();
        this.numberOfDice = numberOfDice;
        this.values = new PriorityQueue<>();
    }

    /**
     * Rolls the specified number of dice
     * @return Collection of the values of the rolled RISKModel.Die
     */
    public Collection<Integer> roll(){
        for (int i = 0; i <numberOfDice; i++){
            this.die = new Die();
            die.rollDie();
            this.values.add(die.getFaceValue());
        }
        return values;
    }

    /**
     * Sorts the values of the collection of rolled dice in descending order
     * @return a list of rolled dices in descending order
     */
    public List<Integer> sortedValues(){
        List<Integer> sortedList = new ArrayList<>();
        while(!values.isEmpty()){
            sortedList.add(values.poll());
        }
        Collections.reverse(sortedList);
        return sortedList;
    }

    public PriorityQueue<Integer> getValues(){ return this.values;}
}
