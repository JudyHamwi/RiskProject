package RiskModel;

public class FortifyPhase {

    private Country movingFrom;
    private Country movingTo;
    private int numOfArmies;
    private Player player;


    public FortifyPhase(Player player, Country movingFrom, Country movingTo) {
        this.player = player;
        this.movingFrom = movingFrom;
        this.movingTo = movingTo;
    }

    public void setNumOfArmiesToMove(int number) {
        this.numOfArmies = number;
    }

    public boolean checkNumOfArmies() {
        if (movingFrom.getNumberOfArmies() > numOfArmies) {
            return true;
        }
        return false;
    }

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
