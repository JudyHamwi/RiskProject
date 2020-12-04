package tst;

import risk.model.board.Country;
import risk.model.phase.FortifyPhase;
import risk.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FortifyPhaseTest {
private Player player;
private Country countryFrom;
private Country countryTo;
private FortifyPhase fortifyPhase;
private int numOfArmies = 4;

    @Before
    public void setUp() throws Exception {
        player = new Player();
        countryFrom = new Country("country1");
        countryTo = new Country("country2");
        player.addCountry(countryFrom);
        player.addCountry(countryTo);
        countryFrom.setAdjacentCountry(countryTo);
        countryFrom.addArmy(10);
        countryTo.addArmy(1);
        fortifyPhase = new FortifyPhase(player, countryFrom, countryTo);
        fortifyPhase.setNumOfArmiesToMove(numOfArmies);
    }

    @Test
    public void checkNumOfArmies() {
        assertTrue(fortifyPhase.checkNumOfArmies());
        countryFrom.addArmy(-9);
        assertFalse(fortifyPhase.checkNumOfArmies());
    }

    @Test
    public void fortify() {
        assertTrue(fortifyPhase.fortify());
    }
}