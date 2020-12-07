package risk.model.board;

import risk.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * RISKModel.Country in the RISKModel.Board of RISK RISKModel.Game
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class Country {
    private String countryName;
    private List<Country> adjacentCountries;
    private int numberOfArmies;
    private Player currentOwner;

    /**
     * Constructor to create a new RISKModel.Country with a specific name
     *
     * @param name of the country
     */
    public Country(String name) {
        countryName = name;
        adjacentCountries = new ArrayList<>();
        numberOfArmies = 0;
    }

    /**
     * set the country adjacent to this country
     */
    public void addAdjacentCountry(Country country) {
        adjacentCountries.add(country);
    }

    /**
     * get the name of the country
     *
     * @return name of the country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * get the number of Armies occupying a country
     *
     * @return number of armies occupying a country
     */
    public int getNumberOfArmies() {
        return numberOfArmies;
    }

    public void setNumberOfArmies(final int numberOfArmies) {
        this.numberOfArmies = numberOfArmies;
    }

    /**
     * getter for the adjacent countries to this country
     *
     * @return List<Country> adjacent to this country
     */
    public List<Country> getAdjacentCountries() {
        return adjacentCountries;
    }

    public void clearAdjacentCountries() {
        adjacentCountries.clear();
    }

    /**
     * returns the current owner of the country
     *
     * @return player that owns the country
     */
    public Player getCurrentOwner() {
        return currentOwner;
    }

    /**
     * sets the owner of the country
     */
    public void setCurrentOwner(Player player) {
        currentOwner = player;
    }

    /**
     * adds a number of armies that conquer the country
     *
     * @param numberArmies that are added to conquer the country
     */
    public void addArmies(int numberArmies) {
        this.numberOfArmies += numberArmies;
    }

    /**
     * checks if the two countries are adjacent
     *
     * @param country that is compared if it is adjacent to the current country
     * @return true if the two countries are adjacent and false otherwise
     */
    public boolean isAdjacent(Country country) {
        return adjacentCountries.contains(country);
    }

    /**
     * checks if the country is owned by a player
     *
     * @return true if the country is owned by a player and false otherwise
     */
    public boolean hasOwner() {
        return currentOwner != null;
    }

    private void buildConnectedCountries(final Country current, List<Country> connectedCountryList) {
        for (Country adjacent : current.getAdjacentCountries()) {
            if (!connectedCountryList.contains(adjacent) && Objects.equals(adjacent.getCurrentOwner(), currentOwner)) {
                connectedCountryList.add(adjacent);
                buildConnectedCountries(adjacent, connectedCountryList);
            }
        }
    }

    public List<Country> getConnectedCountries() {
        List<Country> listConnectedCountries = new ArrayList<>();
        buildConnectedCountries(this, listConnectedCountries);
        return listConnectedCountries;
    }

    public boolean isOwnedBy(final Player player) {
        return Objects.equals(currentOwner, player);
    }

    /**
     * text representation of the country
     *
     * @return text representation of the country
     */
    @Override
    public String toString() {
        String country =  countryName;
        if (hasOwner()) {
            country += ", Owned by: " + getCurrentOwner();
        }
        if (!adjacentCountries.isEmpty()) {
            country += ", Adjacent Countries: " + adjacentCountries.stream()
                    .map(Country::getCountryName)
                    .collect(Collectors.joining(" "));
        }

        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return countryName.equals(country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName);
    }

    public void setAdjacentCountries(List<Country> countries) {
        this.adjacentCountries = countries;
    }
}

