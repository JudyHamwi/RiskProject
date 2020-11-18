package RiskTestCases;

import RiskModel.*;
import RiskView.RiskView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class GameTest {

     private Game game;
     private Country attackingCountry;
     private Country defendingCountry;
     private int numPlayers;
     private Player currentPlayer;


    @Before
    public void setUp() throws Exception {
        game = new Game();
        currentPlayer = new Player();
        attackingCountry = new Country("Alaska");
        defendingCountry = new Country("Alberta");
        numPlayers = 2;
        currentPlayer.setPlayerCounter(1);

    }


    @Test
    public void testGame(){
        assertTrue(game.equals(game));
    }

    @Test
    public void testGetState(){
        assertEquals("Game is initializing",GameState.INITIALIZING, game.getState());
    }

    @Test
    public void testAddPlayers(){
        game.setNumberOfPlayers(2);
        game.addPlayers(numPlayers);
        assertEquals(2, game.getNumPlayers());
        assertEquals(2, game.players.size());
    }

    @Test
    public void testcheckWinner(){
        game.setNumberOfPlayers(numPlayers);
        game.addPlayers(numPlayers);
        game.players.get(0).addCountry(attackingCountry);
        game.players.get(0).addCountry(defendingCountry);
        Player p= game.removePlayer();
        assertTrue(game.checkWinner());
    }

    @Test
    public void testtheInitialState() {
        game.setNumberOfPlayers(numPlayers);
        game.theInitialState();
        assertEquals(2,numPlayers);
        assertEquals(21, game.currentPlayer.getTotalNumberOfCountries());
        assertEquals(GameState.IN_PROGRESS, game.getState());
        assertEquals(game.players.get(0), game.currentPlayer);
        assertEquals(1, game.currentPlayer.getPlayerID());
        game.initialArmyForPlayer();
        assertEquals(50, game.currentPlayer.getPlayerArmy());
    }

    @Test
    public void testremovePlayer(){
        game.setNumberOfPlayers(numPlayers);
        game.addPlayers(numPlayers);
        game.players.get(0).addCountry(attackingCountry);
        game.players.get(0).addCountry(defendingCountry);
        assertEquals(2,game.removePlayer().getPlayerID());
    }

    @Test
    public void testendTurn() {
        game.setNumberOfPlayers(numPlayers);
        game.theInitialState();
        game.endTurn();
        assertEquals(2,game.currentPlayer.getPlayerID());
        game.endTurn();
        assertEquals(1,game.currentPlayer.getPlayerID());

    }

    @Test
    public void testgetNumPlayers() {
        game.setNumberOfPlayers(numPlayers);
        assertEquals(2,game.getNumPlayers());
    }

    @org.junit.Test
    public void testcheckAttackingCountry() {
        game.setNumberOfPlayers(numPlayers);
        game.theInitialState();
        game.currentPlayer.addCountry(attackingCountry);
        game.checkAttackingCountry(attackingCountry);
        assertEquals("Alaska", game.getAttackingCountry().getCountryName());
    }
}