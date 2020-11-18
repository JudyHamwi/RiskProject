package RiskTestCases;

import RiskModel.Country;
import RiskModel.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;
    List<Country> countriesOwned;

    @Before
    public void setUp() throws Exception {
       player = new Player();
       countriesOwned = new ArrayList<>();
       player.setPlayerCounter(1);
    }

    @Test
    public void testPlayer(){
        assertTrue(player.equals(player));
    }

    @Test
    public void testGetCountriesOwned() {
        Country country = new Country("Alaska");
        player.addCountry(country);
        countriesOwned.add(country);
        assertEquals(countriesOwned,player.getCountriesOwned());
    }

    @Test
    public void testGetNextPlayerId() {
        int playerID = 1;
        assertEquals(1,player.getNextPlayerId());
        assertEquals(2,player.getNextPlayerId());
    }

    @Test
    public void testGetPlayerID() {
        assertEquals(1, player.getPlayerID());
    }

    @Test
    public void testaddPlayerArmy() {
        int army = 4;
        player.addPlayerArmy(army);
        assertEquals(4,player.getPlayerArmy());
    }

    @Test
    public void getTotalNumberOfCountries() {
        player.addCountry(new Country("Madagascar"));
        player.addCountry(new Country("SouthAfrica"));
        assertEquals(2, player.getTotalNumberOfCountries());
    }

    @Test
    public void testaddCountry() {
        player.addCountry(new Country("Alaska"));
        assertEquals(1,player.getTotalNumberOfCountries());
    }

    @Test
    public void testremoveCountry() {
        Country madagascar = new Country("Madagascar");
        player.addCountry(madagascar);
        player.addCountry(new Country("SouthAfrica"));
        player.addCountry(new Country("Alaska"));
        player.removeCountry(madagascar);
        assertEquals(2, player.getTotalNumberOfCountries());
    }

    @Test
    public void testcanAttack() {
        Player player2 = new Player();
        Country attackFrom = new Country("Alaska");
        Country attackTo = new Country("India");
        player.addCountry(attackFrom);
        player2.addCountry(attackTo);
        assertFalse(player.canAttack(attackFrom, attackTo));
    }

    @Test
    public void testcanAttackFrom() {
        Country china = new Country("China");
        player.addCountry(china);
        player.addPlayerArmy(4);
        assertTrue(player.canAttackFrom(china));
    }
}