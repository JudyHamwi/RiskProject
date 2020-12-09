package risk.model.board;

import risk.model.GameConstants;
import risk.model.player.Player;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The RISKModel.Board of the RISK RISKModel.Game.
 *
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
     * Constructor of RISKModel.Board that creates a new RISKModel.Board
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

    public Country getCountry(String name) {
        for (Country c : countries) {
            if (c.getCountryName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public List<Continent> getContinents() {
        return continents;
    }


    /**
     * The textual representation of the RISKModel.Board. Contains information about every continent, country
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

    /**
     * Retrieves all the countries in the RISKModel.Board
     *
     * @return List of countries in the RISKModel.Board
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * create the RISKModel.Board for the RISK RISKModel.Game
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

    public GameConstants getGameConstants() {
        return gameConstants;
    }

    public List<Country> getCountriesOwnedBy(final Player player) {
        return countries.stream()
                .filter(country -> player.equals(country.getCurrentOwner()))
                .collect(Collectors.toList());
    }

    public List<Continent> getContinentsOwnedBy(final Player player) {
        return continents.stream()
                .filter(c -> c.isOwnedBy(player))
                .collect(Collectors.toList());


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


    public boolean isValidMap() {
        // Check if two continents have the same name
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

        //check if country has adjacent countries
        for (Country country : getCountries()) {
            if (!country.hasAdjacent()) {
                return false;
            }
        }

        return true;
    }

    public void authorizingAdjacentCountries() {
        for (Country c : countries) {
            ArrayList<Country> list = new ArrayList<>();
            for (Country ac : c.getAdjacentCountries()) {
                list.add(getCountry(ac.getCountryName()));
            }
            c.setAdjacentCountries(list);
        }
    }

}