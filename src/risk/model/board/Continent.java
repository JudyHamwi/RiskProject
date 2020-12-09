package risk.model.board;

import risk.model.player.Player;

import java.util.*;

/**
 * RISKModel.Continent in the RISKModel.Board of RISK RISKModel.Game.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class Continent {
    private String continentName;
    private List<Country> countries;
    private int bonusArmies;

    /**
     * Contrusctor of RISKModel.Continent that creates a new RISKModel.Continent
     *
     * @param name of the RISKModel.Continent
     */
    public Continent(String name, int bonusArmies) {
        continentName = name;
        countries = new LinkedList<>();
        this.bonusArmies = bonusArmies;
    }

    /**
     * Add country to the continent
     *
     * @param country added to the continent
     */
    public void addCountry(Country country) {
        countries.add(country);
    }

    /**
     * getter for the countries in the continent
     *
     * @return LikedList<Conuntry> List of countries in the continent
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * Returns the number of bonus armies a player gets each turn when the player occupy this continent
     *
     * @return
     */
    public int getBonusArmies() {
        return bonusArmies;
    }

    /**
     * get the name of the continent
     *
     * @return name of the continent
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * Text representation of the continent.
     *
     * @return text representation of the continent
     */
    public Country getCountry(String name) {
        for (Country c : countries) {
            if (c.getCountryName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public boolean isValid() {
        // Check if all continents have at least one country
        if (this.getCountries().size() < 1) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String continent = continentName + ": \n";
        for (Country c : countries) {
            continent += "    " + c.toString();
            continent += "\n";
        }
        return continent;
    }

    public boolean isOwnedBy(final Player player) {
        for (Country country : countries) {
            if (!country.isOwnedBy(player)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Continent continent = (Continent) o;
        return bonusArmies == continent.bonusArmies &&
                continentName.equals(continent.continentName) &&
                countries.equals(continent.countries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(continentName, countries, bonusArmies);
    }
}