package risk.model.board;

import risk.model.player.Player;

import java.util.*;

/**
 * Continent in the risk board.
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
     * Constructor that creates a new Continent.
     *
     * @param name of the RISKModel.Continent
     */
    public Continent(String name, int bonusArmies) {
        continentName = name;
        countries = new LinkedList<>();
        this.bonusArmies = bonusArmies;
    }

    /**
     * Adds a country to the continent
     *
     * @param country added to the continent
     */
    public void addCountry(Country country) {
        countries.add(country);
    }

    /**
     * gets the list of countries in a continent.
     *
     * @return List of countries in a continent.
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * Returns the number of bonus armies a player gets each turn when the player occupy this continent.
     *
     * @return
     */
    public int getBonusArmies() {
        return bonusArmies;
    }

    /**
     * get the name of the continent.
     *
     * @return name of the continent.
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * gets the country by its specific name.
     * @param name, name of the country.
     * @return country.
     */
    public Country getCountry(String name) {
        for (Country c : countries) {
            if (c.getCountryName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * checks if a Continent is valid.
     *
     * @return true if it's a valid continent.
     */
    public boolean isValid() {
        // Check if all continents have at least one country
        if (this.getCountries().size() < 1) {
            return false;
        }
        return true;
    }

    /**
     * checks if a country is owned by specified player.
     * @param player to check
     * @return true if the player owns the country
     */
    public boolean isOwnedBy(final Player player) {
        for (Country country : countries) {
            if (!country.isOwnedBy(player)) {
                return false;
            }
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