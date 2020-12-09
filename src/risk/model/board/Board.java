package risk.model.board;

import risk.model.GameConstants;
import risk.model.player.Player;

import java.util.*;

/**
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class Board {
    private List<Continent> continents;
    private List<Country> countries;
    private GameConstants gameConstants;

    /**
     * Constructor of the Board, it sets the continents and the countries of the board.
     */
    public Board(List<Continent> continents, GameConstants gameConstants) {
        this.continents = continents;
        List<Country> list = new ArrayList<>();
        for (Continent continent : continents) {
            List<Country> continentCountries = continent.getCountries();
            for (Country country : continentCountries) {
                list.add(country);
            }
        }
        this.countries = list;
        this.gameConstants = gameConstants;
    }

    /**
     * gets the country by providing its name.
     *
     * @param name, the name of a country.
     * @return a country
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
     * retrieves all the continents in the board.
     *
     * @return list of the continents.
     */
    public List<Continent> getContinents() {
        return continents;
    }


    /**
     * Retrieves all the countries in the board.
     *
     * @return List of countries.
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * retrieves the game constants.
     *
     * @return game constants.
     */
    public GameConstants getGameConstants() {
        return gameConstants;
    }

    /**
     * retrieves the list of countries that a player owns
     *
     * @param player in the game
     * @return list of countries owned by a player.
     */
    public List<Country> getCountriesOwnedBy(final Player player) {
        List<Country> countriesList = new ArrayList<>();
        for (Country country : countries) {
            if (player.equals(country.getCurrentOwner())) {
                countriesList.add(country);
            }
        }
        return countriesList;
    }

    /**
     * retrieves the list of continents that a player owns
     *
     * @param player in the game
     * @return list of continents owned by a player.
     */
    public List<Continent> getContinentsOwnedBy(final Player player) {
        List<Continent> continentsList = new ArrayList<>();
        for (Continent c : continents) {
            if (c.isOwnedBy(player)) {
                continentsList.add(c);
            }
        }
        return continentsList;
    }

    /**
     * assigns random countries for each player in the game.
     *
     * @param players, list of players in the game.
     */
    public void assignCountries(final List<Player> players) {
        final LinkedList<Country> countriesToAssign = new LinkedList<>();
        countriesToAssign.addAll(countries);
        Collections.shuffle(countriesToAssign);

        int playerToAssign = 0;
        while (!countriesToAssign.isEmpty()) {
            final Country country = countriesToAssign.pop();

            final Player player = players.get(playerToAssign);

            country.setCurrentOwner(player);
            playerToAssign = ++playerToAssign % players.size();
        }
    }

    /**
     * distributes armies for eah player on their owned countries.
     * The number of initial armies according to the number of players playing is specified in gameConstants.
     *
     * @param players, list of players in the game.
     */
    public void distributeArmies(List<Player> players) {
        players.forEach(player -> {
            final List<Country> ownedCountries = getCountriesOwnedBy(player);
            int armiesToPlace = gameConstants.getInitialArmyDraft(players.size());

            for (Country ownedCountry : ownedCountries) {
                ownedCountry.addArmies(1);
                armiesToPlace--;
            }

            while (armiesToPlace > 0) {
                final int randomPos = new Random().nextInt(ownedCountries.size());
                final Country country = ownedCountries.get(randomPos);
                country.addArmies(1);
                armiesToPlace--;
            }
        });
    }

    /**
     * checks if the custom map loaded by the user is valid.
     *
     * @return true if the custom map is valid.
     */
    public boolean isValidMap() {

        Set<Continent> continentSet = new HashSet<>(getContinents());
        if (continentSet.size() < getContinents().size()) {
            return false;
        }

        for (Continent continent : continents) {
            //Check in continent if every continent have at least one country
            if (!continent.isValid()) return false;

            //checks if a continent and a country have the same name
            for (Country c : continent.getCountries()) {
                if (continent.getContinentName().equals(c.getCountryName())) {
                    return false;
                }
            }
        }

        //check if a country has adjacent countries
        for (Country country : getCountries()) {
            if (!country.hasAdjacent()) {
                return false;
            }
        }

        return true;
    }

    /**
     * saves and sets the adjacent countries in a list
     */
    public void authorizingAdjacentCountries() {
        for (Country c : countries) {
            ArrayList<Country> list = new ArrayList<>();
            for (Country ac : c.getAdjacentCountries()) {
                list.add(getCountry(ac.getCountryName()));
            }
            c.setAdjacentCountries(list);
        }
    }

    /**
     * The textual representation of the board, Contains information about every continent, country
     * and the player that owns the country
     *
     * @return the textual representation of the board.
     */
    @Override
    public String toString() {
        String Board = "RISK BOARD: \n" + "Continents: \n";
        for (Continent c : continents) {
            Board = Board.concat(c.toString() + "\n");
        }
        return Board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return continents.equals(board.continents) &&
                countries.equals(board.countries) &&
                gameConstants.equals(board.gameConstants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(continents, countries, gameConstants);
    }

}