package RiskModel;

import java.util.List;

public class DraftPhase {
    private Player currentPlayer;
    private int bonusArmiesForOccupiedCountries;
    private int bonusArmiesForOccupiedContinents;

    public DraftPhase(Player player) {
        this.currentPlayer = player;
        this.bonusArmiesForOccupiedCountries = 0;
        this.bonusArmiesForOccupiedContinents = 0;
    }

    public int getBonusArmiesForOccupiedCountries() {
        return bonusArmiesForOccupiedCountries;
    }

    public void setBonusArmiesForOccupiedCountries(int bonusArmiesForOccupiedCountries) {
        this.bonusArmiesForOccupiedCountries = bonusArmiesForOccupiedCountries;
    }

    public int getBonusArmiesForOccupiedContinents() {
        return bonusArmiesForOccupiedContinents;
    }

    public void setBonusArmiesForOccupiedContinents(int bonusArmiesForOccupiedContinents) {
        this.bonusArmiesForOccupiedContinents = bonusArmiesForOccupiedContinents;
    }
    // you will always receive 3 armies on a turn even if you occupy fewer than 9 countries
    // so if numberOfOccupiedCountries < 9 --> bonus armies = 3;
    // else...
    //count the number of countries that the player occupy
    // divide by 3 and ignore any fraction

    public void setupBonusArmiesForOccupiedCountries(){
        int numOfOccupiedCountries = currentPlayer.getTotalNumberOfCountries();
        if (numOfOccupiedCountries < 9 ){
            this.bonusArmiesForOccupiedCountries = 3;
        } else {
            this.bonusArmiesForOccupiedCountries = (numOfOccupiedCountries / 3);
        }
    }

    /*you will receive armies for each continent you control at the start of your turn
        When a continent is fully occupied by one player, the player gets:
        +5 for North America
        +2 for south america
        +5 for Europe
        +7 for Asia
        +3 for Africa
        +2 for Australia
     */
    public void setupBonusArmiesForOccupiedContinents(){
        List<Continent> occupiedContinents =  currentPlayer.getContinentsOwned();
        for (Continent c: occupiedContinents){
           this.bonusArmiesForOccupiedContinents = c.getBonusArmies();
        }
    }

    // After calculating the number of extra troops, the player
    // gets to choose where to place them ( On their occupied territory)
    public int getTotalBonusArmies(){
        return (this.bonusArmiesForOccupiedContinents+ this.bonusArmiesForOccupiedCountries);
    }
}
