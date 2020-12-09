package risk.model.marshalling;

import risk.model.Game;
import risk.model.GameConstants;
import risk.model.GameState;
import risk.model.board.Board;
import risk.model.board.Continent;
import risk.model.board.Country;
import risk.model.phase.PhaseFactory;
import risk.model.player.Player;
import risk.model.player.PlayerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Serializable Game, responsible for the process of serializing the Game Object.
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class SerializableGame {
    private final List<Continent> continents;
    private final Map<Country, List<Country>> adjacencyMap;
    private final GameConstants gameConstants;
    private final Map<Country, Integer> countryOwnerMap;
    private final GameState gameState;
    private final Map<Integer, String> playerMap;
    private final int currentPlayer;
    private static final String DELIMITER = "_";
    private static final String AI = "AI";
    private static final int USER_ID = 1;
    private static final int USER_TYPE = 0;

    /**
     * Serializable Game Constructor.
     *
     * @param continents,      continents in the game play.
     * @param adjacencyMap,    adjacent countries.
     * @param gameConstants,   game constants.
     * @param countryOwnerMap, country owners.
     * @param gameState,       game state.
     * @param playerMap,       map of current player in game.
     * @param currentPlayer,   current turn in game.
     */
    private SerializableGame(List<Continent> continents, Map<Country, List<Country>> adjacencyMap, GameConstants gameConstants,
                             Map<Country, Integer> countryOwnerMap, GameState gameState, Map<Integer, String> playerMap, int currentPlayer) {
        this.continents = continents;
        this.adjacencyMap = adjacencyMap;
        this.gameConstants = gameConstants;
        this.countryOwnerMap = countryOwnerMap;
        this.gameState = gameState;
        this.playerMap = playerMap;
        this.currentPlayer = currentPlayer;
    }

    /**
     * Serializes a Game object
     *
     * @param game, game to serialize.
     * @return a serialized game.
     */
    public static SerializableGame fromGame(final Game game) {

        final List<Continent> continents = game.getBoard().getContinents();
        final Map<Country, List<Country>> adjacencyMap = new HashMap<>();
        final Map<Country, Integer> countryOwnerMap = new HashMap<>();
        final GameState gameState = game.getState();
        final Map<Integer, String> playerMap = new HashMap<>();
        final int currentPlayer = game.getCurrentPlayer().getId();

        for (Continent continent : continents) {
            List<Country> countries = continent.getCountries();
            for (Country country : countries) {
                countryOwnerMap.put(country, country.getCurrentOwner().getId());
                country.setCurrentOwner(null);
                adjacencyMap.put(country, List.copyOf(country.getAdjacentCountries()));
                country.clearAdjacentCountries();

            }
        }

        for (Player p : game.getPlayers()) {
            playerMap.put(p.getId(), p.toString());
        }

        return new SerializableGame(continents, adjacencyMap, game.getBoard().getGameConstants(), countryOwnerMap, gameState,
                playerMap, currentPlayer);
    }

    /**
     * converts a serialized game to a game object.
     *
     * @return deserialized Game Object.
     */
    public Game toGame() {
        for (Continent continent : continents) {
            List<Country> countries = continent.getCountries();
            for (Country country : countries) {
                country.setAdjacentCountries(adjacencyMap.get(country));

            }
        }

        Board board = new Board(continents, gameConstants);
        Game game = new Game(board, new PhaseFactory());
        game.setState(gameState);

        for (String p : playerMap.values()) {
            String[] playerName = p.split(DELIMITER);
            Player player;
            if (playerName[USER_TYPE].equals(AI)) {
                player = new PlayerFactory().createAI(game.getBoard());
            } else {
                player = new PlayerFactory().createUser(game.getViews());
            }
            player.setID(Integer.parseInt(playerName[USER_ID]));
            if (player.getId() == currentPlayer) {
                game.setCurrentPlayer(player);
            }
            game.addPlayer(player);
        }
        for (Country country : countryOwnerMap.keySet()) {
            Country c = board.getCountry(country.getCountryName());
            Player owner = game.getPlayer(countryOwnerMap.get(country));
            c.setCurrentOwner(owner);
        }

        return game;
    }
}