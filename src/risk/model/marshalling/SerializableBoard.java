package risk.model.marshalling;

import risk.model.GameConstants;
import risk.model.board.Board;
import risk.model.board.Continent;
import risk.model.board.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializableBoard {
    private final List<Continent> continents;
    private final Map<Country, List<Country>> adjacencyMap;
    private final GameConstants gameConstants;

    private SerializableBoard(List<Continent> continents, Map<Country, List<Country>> adjacencyMap, GameConstants constants) {
        this.continents = continents;
        this.adjacencyMap = adjacencyMap;
        this.gameConstants = constants;
    }

    public static SerializableBoard  fromBoard(final Board board) {
        // TODO implement deep copy so we don't modify the input board if the user wants to keep using it.
        final List<Continent> continents = board.getContinents();
        final Map<Country, List<Country>> adjacencyMap = new HashMap<>();

        for (Continent continent : continents) {
            List<Country> countries = continent.getCountries();
            for (Country country : countries) {
                adjacencyMap.put(country, List.copyOf(country.getAdjacentCountries()));
                country.clearAdjacentCountries();
            }
        }

        return new SerializableBoard(continents, adjacencyMap, board.getGameConstants());
    }

    // In order to test this, you need to make sure you have an equals and hashcode method implemented for board, continent, country, GameConstants
    public Board toBoard() {
        // Need to remove adjacent countries from equals and hashcode
        for (Continent continent : continents) {
            List<Country> countries = continent.getCountries();
            for (Country country : countries) {
                country.setAdjacentCountries(adjacencyMap.get(country));
            }
        }

        return new Board(continents, gameConstants);
    }
}