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
    private final List<Continent> continents;
    private final List<Country> countries;
    private final GameConstants gameConstants;

    /**
     * Constructor of RISKModel.Board that creates a new RISKModel.Board
     */
    public Board(final List<Continent> continents, final GameConstants gameConstants) {
        this.continents = continents;
        this.countries = continents.stream()
                .map(Continent::getCountries)
                .flatMap(List::stream)
                .collect(Collectors.toList());
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

    public void distributeArmies(final List<Player> players) {
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
}