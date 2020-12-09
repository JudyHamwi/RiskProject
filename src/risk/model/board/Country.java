package risk.model.board;

import risk.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Country in a risk game.
 *
 * @author Sarah Jaber
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
     * Constructor to create a new country with a specific name.
     *
     * @param name of the country
     */
    public Country(String name) {
        countryName = name;
        adjacentCountries = new ArrayList<>();
        numberOfArmies = 0;
    }

    /**
     * adds adjacent country to the specified country
     */
    public void addAdjacentCountry(Country country) {
        adjacentCountries.add(country);
    }

    /**
     * gets the name of the country.
     *
     * @return name of the country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * gets the number of Armies on a country.
     *
     * @return number of armies on a country
     */
    public int getNumberOfArmies() {
        return numberOfArmies;
    }

    /**
     * sets the number of armies on a country.
     *
     * @param numberOfArmies on a country.
     */
    public void setNumberOfArmies(final int numberOfArmies) {
        this.numberOfArmies = numberOfArmies;
    }

    /**
     * gets the adjacent countries to this country.
     *
     * @return List of adjacent countries to this country.
     */
    public List<Country> getAdjacentCountries() {
        return adjacentCountries;
    }

    /**
     * sets the adjacent countries of the country.
     *
     * @param countries list of adjacent countries.
     */
    public void setAdjacentCountries(List<Country> countries) {
        this.adjacentCountries = countries;
    }

    /**
     * clears the adjacent countries list of this country.
     */
    public void clearAdjacentCountries() {
        adjacentCountries.clear();
    }

    /**
     * gets the current owner of this country.
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
     * @param numberArmies that are added to conquer the country.
     */
    public void addArmies(int numberArmies) {
        this.numberOfArmies += numberArmies;
    }

    /**
     * checks if two countries are adjacent.
     *
     * @param country that is compared if it is adjacent to the current country.
     * @return true if the two countries are adjacent and false otherwise.
     */
    public boolean isAdjacent(Country country) {
        return adjacentCountries.contains(country);
    }

    /**
     * checks if a country have an adjacent country.
     *
     * @return true if a country has at least one adjacent country, and false otherwise.
     */
    public boolean hasAdjacent() {
        return getAdjacentCountries().size() > 0;
    }

    /**
     * checks if the country is owned by a player.
     *
     * @return true if the country is owned by a player, and false otherwise.
     */
    public boolean hasOwner() {
        return currentOwner != null;
    }

    /**
     * checks is a player owns this country.
     *
     * @param player to check
     * @return true if the player owns the country, and false otherwise.
     */
    public boolean isOwnedBy(final Player player) {
        return Objects.equals(currentOwner, player);
    }

    /**
     * builds connected countries.
     * used to fortify from a country to another connected country.
     *
     * @param current              country.
     * @param connectedCountryList connected country list.
     */
    private void buildConnectedCountries(final Country current, List<Country> connectedCountryList) {
        for (Country adjacent : current.getAdjacentCountries()) {
            System.out.println(adjacent + " " + adjacent.getCurrentOwner());
            System.out.println(current + " " + current.getCurrentOwner());
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

    /**
     * text representation of the country
     *
     * @return text representation of the country
     */
    @Override
    public String toString() {
        String country = countryName;
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
        return this.countryName.equals(country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName);
    }
}

