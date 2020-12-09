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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializeGame {
    private final List<Continent> continents;
    private final Map<Country, List<Country>> adjacencyMap;
    private final GameConstants gameConstants;
    private final Map<Country,Integer> countryOwnerMap;
    private final GameState gameState;
    private final Map<Integer,String> playerMap;
    private final int currentPlayer;

    private SerializeGame(List<Continent> continents, Map<Country, List<Country>> adjacencyMap, GameConstants constants,
                        Map<Country,Integer> countryOwnerMap,GameState gameState, Map<Integer,String> playerMap,int currentPlayer) {
        this.continents = continents;
        this.adjacencyMap = adjacencyMap;
        this.gameConstants = constants;
        this.countryOwnerMap=countryOwnerMap;
        this.gameState=gameState;
        this.playerMap=playerMap;
        this.currentPlayer=currentPlayer;
    }

    public static SerializeGame  fromGame(final Game game) {
        // TODO implement deep copy so we don't modify the input board if the user wants to keep using it.
        final List<Continent> continents = game.getBoard().getContinents();
        final Map<Country, List<Country>> adjacencyMap = new HashMap<>();
        final Map<Country, Integer> countryOwnerMap = new HashMap<>();
        final GameState gameState=game.getState();
        final Map<Integer, String> playerMap=new HashMap<>();
        final int currentPlayer=game.getCurrentPlayer().getId();


        for (Continent continent : continents) {
            List<Country> countries = continent.getCountries();
            for (Country country : countries) {
                countryOwnerMap.put(country,country.getCurrentOwner().getId());
                country.setCurrentOwner(null);
                adjacencyMap.put(country, List.copyOf(country.getAdjacentCountries()));
                country.clearAdjacentCountries();

            }
        }

        for(Player p:game.getPlayers()){
            playerMap.put(p.getId(),p.toString());
        }

        return new SerializeGame(continents, adjacencyMap, game.getBoard().getGameConstants(),countryOwnerMap,gameState,
                playerMap,currentPlayer);
    }

    // In order to test this, you need to make sure you have an equals and hashcode method implemented for board, continent, country, GameConstants
    public Game toGame() {
        // Need to remove adjacent countries from equals and hashcode
        for (Continent continent : continents) {
            List<Country> countries = continent.getCountries();
            for (Country country : countries) {
                country.setAdjacentCountries(adjacencyMap.get(country));

            }
        }

        Board board=new Board(continents,gameConstants);
        Game game=new Game(board, new PhaseFactory());
        game.setState(gameState);

        for(String p:playerMap.values()){
            String[] playerName=p.split("_");
            if(playerName[0].equals("AI")){
                Player player=new PlayerFactory().createAI(game.getBoard());
                player.setID(Integer.parseInt(playerName[1]));
                if(player.getId()==currentPlayer){
                    game.setCurrentPlayer(player);
                }
               game.addPlayer(player);
            }else {
                //add set views method in user
                Player player=new PlayerFactory().createUser(game.getViews());
                player.setID(Integer.parseInt(playerName[1]));
                if(player.getId()==currentPlayer){
                    game.setCurrentPlayer(player);
                }
                game.addPlayer(player);
            }

        }

        for(Country country:countryOwnerMap.keySet()){
            Country c=board.getCountry(country.getCountryName());
            Player owner=game.getPlayer(countryOwnerMap.get(country));
            c.setCurrentOwner(owner);
        }
        return  game;
    }
}