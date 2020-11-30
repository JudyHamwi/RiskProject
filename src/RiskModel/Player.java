package RiskModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RISKModel.Player that plays in the RISKModel.Game.
 * @version 2.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 *  @author Diana Miraflor
 */
public class Player {

    private static int playerCounter =1;
    private final int PLAYER_ID;
    private List<Country> countriesOwned;
    private List<Continent> continentsOwned;
    private int placeArmy;
    private int bonusArmy;

    /**
     * RISKModel.Player that plays in the RISKModel.Game
     */
    public Player(){
        this.PLAYER_ID = this.getNextPlayerId();
        this.countriesOwned = new ArrayList<>();
        this.continentsOwned = new ArrayList<>();
        this.placeArmy=0;
    }

    /**
     * gets the countries owned by the player
     * @return List of countries owned by the player
     */
    public List<Country> getCountriesOwned() {
        return countriesOwned;
    }

    /**
     * Gets the Continents owned by the player
     * @return HashMap of the continents owned by the player
     */
    public List<Continent> getContinentsOwned() {
        return continentsOwned;
    }

    /**
     * Instantiates the ID of the next player to be added to the RISKModel.Game
     * @return ID of the next player to be added in the game
     */
    public static int getNextPlayerId() {
        return playerCounter++;
    }

    /**
     * Returns the id of the player
     * @return the player's ID
     */
    public int getPlayerID(){
        return this.PLAYER_ID;
    }

    /**
     * get the number of armies owned by the player
     * @return number of armies owned by the player
     */
    public int getPlayerArmy(){
        return placeArmy;
    }

    /**
     * adds a number of armies that belongs to the player
     * @param army number of armies that are added to belong to the player
     */
    public void addPlayerArmy(int army){
        placeArmy += army;
    }

    /**
     * gets the number of countries owned by the player
     * @return number of countries owned by the player
     */
    public int getTotalNumberOfCountries(){
        return countriesOwned.size();
    }

    /**
     * adds a country owned by the player
     * @param country owned by the player
     */
    public void addCountry(Country country){
        this.countriesOwned.add(country);
        country.addCurrentOwner(this);
    }

    /**
     * removes a country when it no longer belongs to the player
     * @param country that no longer belongs to the player
     */
    public void removeCountry(Country country){
        countriesOwned.remove(country);
    }

    /**
     * adds a continent owned by a player that occupies all the countries on a continent
     * @param continent owned by a player
     */
    public void addContinent(Continent continent){
        continentsOwned.add(continent);
    }

    /**
     * removes a continent from the Map of ownedContinents when a player no longer controls the whole continent
     * @param continent to be removed
     */
    public void removeContinent(Continent continent){
        continentsOwned.remove(continent);
    }

    /**
     * checks the conditions if the player can attack a country by checking if th ecountry attacked from
     * and attacked, and number of armies follow the rules of the game
     * @param attackFrom country that the player wants to attack from
     * @param attackTo country that the player wants to attack
     * @return true of the player can do the attack and false otherwise
     */
    public boolean canAttack(Country attackFrom, Country attackTo){
        if(attackFrom.getCurrentOwner().equals(attackTo.getCurrentOwner())) {
            System.out.println("You can not attack your own country");
            return false;
        } else if(!attackFrom.isAdjacent(attackTo)) {
            System.out.println("The countries are not adjacent");
            return false;
        }
        else{
            return true;
        }
    }

    public boolean canMove(Country moveFrom, Country moveTo, ArrayList<Country> connectedCountries) {
        if (!(moveFrom.getCurrentOwner().equals(moveTo.getCurrentOwner()))) {
            System.out.println("You do not own the country you want to move to");
            return false;
        } else if (!(connectedCountries.contains(moveTo))) {
            System.out.println("Countries are not adjacent");
            return false;
        } else {
            return true;
        }
    }

    /**
     * checks if the country the player wants to attack from can be used, following the
     * rules of the game
     * @param country country that the player wants to attack from
     * @return true of the player can use this country to attack
     */
    public boolean ifPlayerOwns(Country country){
        if(!(countriesOwned.contains(country))){
            System.out.println("You do not own this country");
            return false;
        }else if (country.getNumberOfArmies()==1){
            System.out.println("You only have one army");
            return false;
        }else {
            return true;
        }
    }

    /**
     * Text represenation of the player
     * @return String representation of the player
     */
    public String toString(){
        return "Player" + getPlayerID();
    }

    @Override
    /**
     * Checks if two players are equal.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return PLAYER_ID == player.PLAYER_ID ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(PLAYER_ID, countriesOwned, placeArmy);
    }
    public boolean playerOwnsCountry(Country country){
        for(Country c:countriesOwned){
            if(c.equals(country)){
                return true;
            }
        }
        return false;
    }

    public void setPlayerCounter(int reset){
        playerCounter=reset;
    }

    /**
     * Checks if the player is an AI
     * @return boolean check
     */

    /**
     * Sets an existing player to an AI player
     */

    public void draftPhase() {
        DraftPhase playerDraft = new DraftPhase(this);
        this.bonusArmy = playerDraft.getTotalBonusArmies();
    }

    public int getBonusArmies(){
        return bonusArmy;
    }

    public void placeBonusArmy(){
        bonusArmy--;
    }

    public boolean attackPhase(Country defenderCountry, Country attackCountry) {
            AttackPhase playerAttack = new AttackPhase(this, attackCountry, defenderCountry);
            return playerAttack.attack();
    }

    public boolean fortifyPhase(Country moveFromCountry, Country movingTo, int armiesFortify){
        FortifyPhase playerFortify=new FortifyPhase(this, moveFromCountry, movingTo);
        playerFortify.setNumOfArmiesToMove(armiesFortify);
        return playerFortify.fortify();
    }

}


