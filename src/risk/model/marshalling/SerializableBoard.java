package risk.model.marshalling;

import risk.model.GameConstants;
import risk.model.board.Board;
import risk.model.board.Continent;
import risk.model.board.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serializable Board, responsible for the process of serializing the Board Object.
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class SerializableBoard {
    private final List<Continent> continents;
    private final Map<Country, List<Country>> adjacencyMap;
    private final GameConstants gameConstants;

    /**
     * SerializableBoard Constructor.
     *
     * @param continents,   all continents in the board.
     * @param adjacencyMap, map of adjacent countries.
     * @param constants,    game constants.
     */
    private SerializableBoard(List<Continent> continents, Map<Country, List<Country>> adjacencyMap, GameConstants constants) {
        this.continents = continents;
        this.adjacencyMap = adjacencyMap;
        this.gameConstants = constants;
    }

    /**
     * Serializes a board object.
     *
     * @param board, board to serialize.
     * @return a serializable board.
     */
    public static SerializableBoard fromBoard(final Board board) {
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

    /**
     * to convert a serialized board to a Board object.
     *
     * @return deserialized Board object
     */
    public Board toBoard() {
        for (Continent continent : continents) {
            List<Country> countries = continent.getCountries();
            for (Country country : countries) {
                country.setAdjacentCountries(adjacencyMap.get(country));
            }
        }
        return new Board(continents, gameConstants);
    }
}