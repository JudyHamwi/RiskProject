package risk.model;

import org.junit.Test;
import risk.model.Game;
import risk.model.GameConstants;
import risk.model.GameState;
import risk.model.board.Board;
import risk.model.board.Continent;
import risk.model.board.Country;
import risk.model.board.OriginalBoardFactory;
import risk.model.marshalling.GameMarshaller;
import risk.model.phase.PhaseFactory;
import risk.model.player.Player;
import risk.model.player.PlayerFactory;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameMarshallerTest {



    @Test
    public void testToAndFromJson(){

        GameMarshaller marshaller = new GameMarshaller();
        Country country1=new Country("C1");
        Country country2=new Country("C2");
        Country country3=new Country("C3");
        Continent continent1=new Continent("continent1",5);
        ArrayList<Continent> continents= new ArrayList<>();
        ArrayList<Country> countries= new ArrayList<>();
        continents.add(continent1);
        continent1.addCountry(country1);
        continent1.addCountry(country2);
        continent1.addCountry(country3);
        countries.add(country1);
        countries.add(country2);
        countries.add(country3);
        final Board board = new Board(continents, new GameConstants());
        Game game=new Game(board,new PhaseFactory());
        game.setState(GameState.FORTIFY_PHASE);
        PlayerFactory playerFactory=new PlayerFactory();
        Player player1=playerFactory.createUser(null);
        game.addPlayer(player1);
        Player player2=playerFactory.createUser(null);
        game.addPlayer(player2);
        Player player3=playerFactory.createAI(board);
        game.addPlayer(player3);
        game.setCurrentPlayer(player2);
        country1.setCurrentOwner(player1);
        country2.setCurrentOwner(player2);
        country3.setCurrentOwner(player3);

        final String gameToJson=marshaller.toJson(game);
        final Game gameFromJson=marshaller.fromJson(gameToJson);

        assertEquals(game,gameFromJson);
    }


}