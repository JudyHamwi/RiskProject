package RiskModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * RISKModel.Country in the RISKModel.Board of RISK RISKModel.Game
 * @version 2.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class Country {

    private String countryName;
    private List<Country> adjacentCountries;
    private int numberOfArmies;
    private Player currentOwner;

    /**
     * Constructor to create a new RISKModel.Country with a specific name
     * @param name of the country
     */
    public Country(String name){
        countryName=name;
        adjacentCountries=new ArrayList<>();
        numberOfArmies=0;
    }

    /**
     * set the country adjacent to this country
     */
    public void setAdjacentCountry(Country country){
        adjacentCountries.add(country);
    }

    /**
     * get the name of the country
     * @return name of the country
     */
    public String getCountryName(){
        return countryName;
    }

    /**
     * get the number of Armies occupying a country
     * @return number of armies occupying a country
     */
    public int getNumberOfArmies(){
        return numberOfArmies;
    }

    /**
     * getter for the adjacent countries to this country
     * @return List<Country> adjacent to this country
     */
    public List<Country> getAdjacentCountries() {
        return adjacentCountries;
    }

    /**
     * returns the current owner of the country
     * @return player that owns the country
     */
    public Player getCurrentOwner(){
        return currentOwner;
    }

    /**
     * sets the owner of the country
     */
    public void addCurrentOwner(Player player){
        currentOwner=player;
    }

    /**
     * text representation of the country
     * @return text representation of the country
     */
    public String toString(){
        return countryName;
    }

    /**
     * adds a number of armies that conquer the country
     * @param numberArmies that are added to conquer the country
     */
    public void addArmy(int numberArmies){
        this.numberOfArmies+=numberArmies;
    }

    /**
     * checks if the two countries are adjacent
     * @param country that is compared if it is adjacent to the current country
     * @return true if the two countries are adjacent and false otherwise
     */
    public boolean isAdjacent(Country country){
        return adjacentCountries.contains(country);
    }

    /**
     * checks if the country is owned by a player
     * @return true if the country is owned by a player and false otherwise
     */
    public boolean hasOwner(){
        return currentOwner!=null;
    }

    /**
     * Sets the number of army to a specific value
     * @param army is the number it should be set too
     */
    public void setArmy(int army){
        this.numberOfArmies = army;
    }

    public void connectedCountries(Country countryFrom, ArrayList<Country> connectedCountryList) {
        for (Country c : countryFrom.getAdjacentCountries()) {
            if (connectedCountryList.contains(c) == false && c.getCurrentOwner() == currentOwner) {
                connectedCountryList.add(c);
                connectedCountries(c, connectedCountryList);
            }
        }
    }

    public ArrayList<Country> listOfConnectedCountries(Country countryFrom) {
        ArrayList<Country> listConnectedCountries = new ArrayList<>();
        connectedCountries(countryFrom, listConnectedCountries);
        return listConnectedCountries;
    }

}


