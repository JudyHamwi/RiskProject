package RiskModel;
/**
 * One of the phases of the game. Player enters RISKModel.FortifyPhase after their RISKModel.AttackPhase.
 * @version 2.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class FortifyPhase {

    private Country movingFrom;
    private Country movingTo;
    private int numOfArmies;
    private Player player;


    /**
     * Constructor for a new fortify phase object
     *
     * @param player Player the current player
     * @param movingFrom Country  the country they want to move their armies from
     * @param movingTo Country  the country they want to move their armies to
     */
    public FortifyPhase(Player player, Country movingFrom, Country movingTo) {
        this.player = player;
        this.movingFrom = movingFrom;
        this.movingTo = movingTo;
    }

    /**
     * Sets the number of armies to move
     *
     * @param number int the number of armies to move.
     */
    public void setNumOfArmiesToMove(int number) {
        this.numOfArmies = number;
    }

    /**
     * Checks if the number of armies they want to move is greater than the number of armies
     * in the country they want to move from.
     *
     * @return boolean
     */
    public boolean checkNumOfArmies() {
        if (movingFrom.getNumberOfArmies() > numOfArmies) {
            return true;
        }
        return false;
    }


    /**
     * Fortifies the number of armies the player wants to move to an adjacent country of their choice.
     *
     * @return
     */
    public boolean fortify() {
        if (checkNumOfArmies()) {
            movingTo.addArmy(numOfArmies);
            movingFrom.addArmy(-numOfArmies);
            System.out.println(numOfArmies + " were moved to " + movingTo);
            return true;
        } else {
            System.out.println("Not enough armies to move");
            return false;
        }
    }

}
