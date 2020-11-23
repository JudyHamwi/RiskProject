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
        setupBonusArmiesForOccupiedCountries();
        setupBonusArmiesForOccupiedContinents();
    }

    /**
     * gets the number of bonus armies for all occupied countries.
     * @return number of bonus armies for the occupied countries
     */
    public int getBonusArmiesForOccupiedCountries() {
        return bonusArmiesForOccupiedCountries;
    }

    /**
     * gets the number of bonus armies for an occupied continent.
     * @return number of bonus armies for the occupied continents
     */
    public int getBonusArmiesForOccupiedContinents() {
        return bonusArmiesForOccupiedContinents;
    }

    /**
     * setup the number of bonus armies that a player recieves at the beginning of each turn.
     * A player receives 3 bonus armies if they have less than 12 occupied countries
     * Otherwise it counts the number of countries that the player occupies and then divides it
     * by 3 and it ignoring any fraction.
     */
    public void setupBonusArmiesForOccupiedCountries(){
        int numOfOccupiedCountries = currentPlayer.getTotalNumberOfCountries();
        if (numOfOccupiedCountries < 12 ){
            this.bonusArmiesForOccupiedCountries = 3;
        } else {
            this.bonusArmiesForOccupiedCountries = Math.floorDiv(numOfOccupiedCountries , 3);
        }
    }

    /**
     * setup the number of bonus armies at the start of each turn that a player receives if they occupy all cuntries in a continent
     * each continent have specific number of bonus armies:
     * +5 for North America
     * +2 for south america
     * +5 for Europe
     * +7 for Asia
     * +3 for Africa
     * +2 for Australia
     */
    public void setupBonusArmiesForOccupiedContinents(){
        List<Continent> occupiedContinents =  currentPlayer.getContinentsOwned();
        for (Continent c: occupiedContinents){
           this.bonusArmiesForOccupiedContinents += c.getBonusArmies();
        }
    }

    /**
     * gets the sum of total bonus armies from occupied countries and continents at the start of each turn for every player.
     * @return the total number of bonus armies for the current player
     */
    public int getTotalBonusArmies(){
        return (this.bonusArmiesForOccupiedContinents+ this.bonusArmiesForOccupiedCountries);
    }

}
