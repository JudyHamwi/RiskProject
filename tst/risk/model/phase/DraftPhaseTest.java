package risk.model.phase;

import org.junit.Before;
import org.junit.Test;
import risk.model.GameConstants;
import risk.model.board.Board;
import risk.model.board.Continent;
import risk.model.board.Country;
import risk.model.player.Player;
import risk.model.player.User;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * One of the phases of the game. Player enters RISKModel.FortifyPhase after their RISKModel.AttackPhase.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * <p>
 * Draft Phase test class.
 * @version 2.0
 */
public class DraftPhaseTest {
    private static final int CONTINENT_BONUS = 4;

    private final Continent northAmerica = new Continent("NA", CONTINENT_BONUS);
    private final Country canada = new Country("Canada");
    private final Country unitedStates = new Country("UnitedStates");
    private final Continent southAmerica = new Continent("NA", CONTINENT_BONUS);
    private final Country brazil = new Country("Brazil");

    private final Player player = new User(1, null);
    private Board board;
    private DraftPhase draftPhase;

    @Before
    public void setUp() {
        northAmerica.addCountry(canada);
        northAmerica.addCountry(unitedStates);
        southAmerica.addCountry(brazil);

        final List<Continent> continents = new LinkedList<>();
        continents.add(northAmerica);
        continents.add(southAmerica);

        board = new Board(continents, new GameConstants());
    }

    @Test
    public void testArmiesToPlace_nothingOwned() {
        draftPhase = new DraftPhase(board, player);

        final int totalBonus = draftPhase.getArmiesToPlace();

        assertEquals(DraftPhase.MIN_BONUS_ARMIES, totalBonus);
    }

    @Test
    public void testArmiesToPlace_oneContinentOwned() {
        brazil.setCurrentOwner(player);
        draftPhase = new DraftPhase(board, player);

        final int totalBonus = draftPhase.getArmiesToPlace();

        assertEquals(DraftPhase.MIN_BONUS_ARMIES + 4, totalBonus);
    }

    @Test
    public void testArmiesToPlace_bothContinentOwned() {
        canada.setCurrentOwner(player);
        unitedStates.setCurrentOwner(player);
        brazil.setCurrentOwner(player);
        draftPhase = new DraftPhase(board, player);

        final int totalBonus = draftPhase.getArmiesToPlace();

        assertEquals(DraftPhase.MIN_BONUS_ARMIES + 8, totalBonus);
    }

    @Test
    public void testArmiesToPlace_multipleCountries() {
        for (int i = 0; i < 14; i++) {
            final Country country = new Country(String.valueOf(i));
            country.setCurrentOwner(player);
            northAmerica.addCountry(country);
        }
        draftPhase = new DraftPhase(board, player);

        final int totalBonus = draftPhase.getArmiesToPlace();

        assertEquals(4, totalBonus);
    }
}