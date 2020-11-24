package RiskModel;

import java.util.*;

/**
 * RISKModel.Continent in the RISKModel.Board of RISK RISKModel.Game.
 * @version 2.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class Continent {
    private String continentName;
    private LinkedList<Country> countries;
    private Player currentOwner;
    private int bonusArmies;

    /**
     * Contrusctor of RISKModel.Continent that creates a new RISKModel.Continent
     * @param name of the RISKModel.Continent
     */
    public Continent(String name, int bonusArmies) {
        continentName = name;
        countries = new LinkedList<>();
        this.bonusArmies = bonusArmies;
    }

    /**
     * Add country to the continent
     * @param country added to the continent
     */
    public void addCountry(Country country) {
        countries.add(country);
    }

    /**
     * getter for the countries in the continent
     * @return LikedList<Conuntry> List of countries in the continent
     */
    public LinkedList<Country> getContinentCountries() {
        return countries;
    }

    /**
     * Returns the number of bonus armies a player gets each turn when the player occupy this continent
     * @return
     */
    public int getBonusArmies() {
        return bonusArmies;
    }

    /**
     * get the name of the continent
     * @return name of the continent
     */
    public String getContinentName() {
        return continentName;
    }
    /**
     * Text representation of the continent.
     * @return text representation of the continent
     */
    public Country getCountryFromContinent(String name){
        for (Country c:countries){
            if(c.getCountryName().equals(name)){
                return c;
            }
        }
        return null;
    }

    public String toString() {
        String continent = continentName + ": \n";
        for (Country c : countries) {
            continent += "    " + c.getCountryName();
            if (c.hasOwner()) {
                continent += " Owned by: " + c.getCurrentOwner();
            }
            continent += "\n Adjacent Countries: ";
            for (int i = 0; i < c.getAdjacentCountries().size(); i++) {
                continent += " " + c.getAdjacentCountries().get(i);
            }
            continent += "\n";
        }
        return continent;
    }

}
