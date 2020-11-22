package RiskModel;

import java.util.ArrayList;
import java.util.List;

/**
 *One of the Phases of the RISKModel.Game, the Attack Phase. This Phase is entered when a player attacks a RISKModel.Country.
 * @version 1.0
 * @author Sarah Jaber
 * @author Walid Baitul
 * @author Judy Hamwi
 * @author Diana Miraflor
 */

public class AttackPhase {


    private Player player;
    private Country attackerCountry;
    private Country defenderCountry;
    private Dice dice ;
    private List<Integer> attackerDiceValues;
    private List<Integer> defenderDiceValues;

    public void setAttackerArmies(int attackerArmies) {
        this.attackerArmies = attackerArmies;
    }

    private int attackerArmies;

    public void setAttackerDiceValues(List<Integer> attackerDiceValues) {
        this.attackerDiceValues = attackerDiceValues;
    }

    public void setDefenderDiceValues(List<Integer> defenderDiceValues) {
        this.defenderDiceValues = defenderDiceValues;
    }

    /**
     *  Constructor of Attack Phase initializes the fields.
     * @param player initiating the attack
     * @param attackerCountry contains the armies that want to attack
     * @param defenderCountry country that contains armies to defend the attack
     */
    public AttackPhase(Player player, Country attackerCountry, Country defenderCountry){
        this.player=player;
        this.attackerDiceValues = new ArrayList<>();
        this.defenderDiceValues = new ArrayList<>();
        this.attackerCountry = attackerCountry;
        this.defenderCountry = defenderCountry;
    }

    /**
     *  Calculate the number of RISKModel.Dice that need to be rolled for the Attack. The number of dice
     *  used to attack is equal to the number of armies attacking. The number is calculated
     *  with the maximum number of armies atacking.
     * @return number of dice rolled for the attack
     */
    public int numberOfDiceForAttacker(){
        if(attackerArmies<1){
            System.out.println("Not Enough Armies to Attack");
        } else if (attackerArmies==1){
            return 1;
        } else if (attackerArmies==2){
            return 2;
        }
        return 3;
    }

    /**
     * Calculate teh number of dice to be rolled for the defender country. The number of dice represent the number
     * if armies defending the country. If the number of armies is only one, the defender can only roll one deice,
     * else the defender can roll two dice
     * @return number of dice rolled by the defender
     */
    public int numberOfDiceForDefender(){
        if(defenderCountry.getNumberOfArmies()==1) {
            return 1;
        }
        return 2;
    }

    /**
     * Roll of RISKModel.Dice of th Attacker
     */
    public void rollForAttacker(){
        dice=new Dice(numberOfDiceForAttacker());
        dice.roll();
        attackerDiceValues = dice.sortedValues();
    }

    /**
     * Roll of RISKModel.Dice of the Defender
     */
    public void rollForDefender(){
        dice=new Dice(numberOfDiceForDefender());
        dice.roll();
        defenderDiceValues = dice.sortedValues();
    }

    /**
     * Compare the Roll of RISKModel.Dice of the Attacker and Defender. Remove armies of the losing country.
     */
    public void compareDice(){
        if (attackerDiceValues.get(0) > defenderDiceValues.get(0)){
            defenderCountry.addArmy(-1);
        } else if(attackerDiceValues.get(0) < defenderDiceValues.get(0)){
            attackerCountry.addArmy(-1);
            attackerArmies --;
        }
        if(attackerDiceValues.size()>1 && defenderDiceValues.size()>1 ) {
            if (attackerDiceValues.get(1) > defenderDiceValues.get(1)) {
                defenderCountry.addArmy(-1);
            } else {
                attackerCountry.addArmy(-1);
                attackerArmies--;
            }
        }
    }

    /**
     * If the Attacker wins, the armies are moved from the attacker country to the defender country
     */
    public  void numberOfArmiesToMove(){
        defenderCountry.addArmy(attackerArmies);
        attackerCountry.addArmy(-attackerArmies);

    }

    /**
     * Applies the attack phase of the RISKModel.Game. It implements the player attacking the defender, and
     * makes the decision of the attacker army conquering the defender armu.
     */
    public boolean attack() {
        attackerArmies=(attackerCountry.getNumberOfArmies())-1;
        System.out.println(player + " has attacked " + defenderCountry + " with " + attackerArmies + " armies.");
        System.out.println(defenderCountry + " is defending with " + defenderCountry.getNumberOfArmies() + " armies.");
        while (attackerArmies > 0 && defenderCountry.getNumberOfArmies()>0 ){
            numberOfDiceForAttacker();
            numberOfDiceForDefender();
            rollForAttacker();
            rollForDefender();
            compareDice();
        }
        if (defenderCountry.getNumberOfArmies() == 0) {
            defenderCountry.getCurrentOwner().removeCountry(defenderCountry);
            player.addCountry(defenderCountry);
            numberOfArmiesToMove();
            System.out.println(player + " has conquered " + defenderCountry + " and is occupied by " + attackerArmies
                                        + " armies.");
            return true;
        } else {
            System.out.println(defenderCountry + " was not conquered and has " + defenderCountry.getNumberOfArmies()
                                        + " armies.");
            return false;
        }
    }

}
