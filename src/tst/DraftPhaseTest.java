package tst;

import risk.model.board.Continent;
import risk.model.board.Country;
import org.junit.Before;
import org.junit.Test;
import risk.model.phase.DraftPhase;
import risk.model.player.Player;

import static org.junit.Assert.*;
/**
 * One of the phases of the game. Player enters RISKModel.FortifyPhase after their RISKModel.AttackPhase.
 * @version 2.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 *
 * Draft Phase test class.
 */
public class DraftPhaseTest {
    private static final int CONTINENT_BOUNUS = 4;
private Player currentPlayer;
private Country country;
private Continent continent;
private DraftPhase draftPhase;

    @Before
    public void setUp() throws Exception {
        this.currentPlayer = new Player();
        country = new Country("TestCountry");
        continent = new Continent("TestContinent", CONTINENT_BOUNUS);
        continent.addCountry(country);
        currentPlayer.addCountry(country);
        currentPlayer.addContinent(continent);
        draftPhase = new DraftPhase(currentPlayer);
    }

    @Test
    public void setupBonusArmiesForOccupiedCountries() {
        int bonusArmies = draftPhase.getBonusArmiesForOccupiedCountries();
        assertEquals(3, bonusArmies);
    }

    @Test
    public void setupBonusArmiesForOccupiedContinents() {
        int bonusArmies = draftPhase.getBonusArmiesForOccupiedContinents();
        assertEquals(CONTINENT_BOUNUS, bonusArmies);
    }

    @Test
    public void getTotalBonusArmies() {
        int totalBonus = draftPhase.getTotalBonusArmies();
        assertEquals(CONTINENT_BOUNUS + 3, totalBonus);

    }
}