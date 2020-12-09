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

public class FortifyPhaseTest {
    private static final int MOVING_ARMIES = 2;

    private static final int CANADA_ARMIES = 4;
    private static final int US_ARMIES = 5;
    private static final int CONTINENT_BONUS = 3;

    private final Continent northAmerica = new Continent("NA", CONTINENT_BONUS);
    private final Country canada = new Country("Canada");
    private final Country unitedStates = new Country("UnitedStates");
    private final Continent southAmerica = new Continent("NA", CONTINENT_BONUS);
    private final Country brazil = new Country("Brazil");

    private final Player player = new User(0, null);
    private Board board;
    private FortifyPhase fortifyPhase;

    @Before
    public void setUp() {
        canada.addAdjacentCountry(unitedStates);
        unitedStates.addAdjacentCountry(canada);
        unitedStates.addAdjacentCountry(brazil);
        brazil.addAdjacentCountry(unitedStates);

        northAmerica.addCountry(canada);
        northAmerica.addCountry(unitedStates);
        southAmerica.addCountry(brazil);

        final List<Continent> continents = new LinkedList<>();
        continents.add(northAmerica);
        continents.add(southAmerica);

        board = new Board(continents, new GameConstants());
    }

    @Test
    public void testFortifyFrom_noOwner() {
        fortifyPhase = new FortifyPhase(board, player);
        boolean movingFrom = fortifyPhase.selectMovingFrom(canada);
        assertEquals(null, fortifyPhase.getMovingFrom());
        assertEquals(false, movingFrom);
    }

    @Test
    public void testFortifyFromAndTo_toNoOwner() {
        canada.setCurrentOwner(player);
        canada.setNumberOfArmies(CANADA_ARMIES);
        fortifyPhase = new FortifyPhase(board, player);

        fortifyPhase.selectMovingFrom(canada);

        boolean movingTo = fortifyPhase.selectMovingTo(unitedStates);

        assertEquals(canada, fortifyPhase.getMovingFrom());
        assertEquals(null, fortifyPhase.getMovingTo());
        assertEquals(false, movingTo);
    }

    @Test
    public void testFortify_canadaToUS() {
        canada.setCurrentOwner(player);
        canada.setNumberOfArmies(CANADA_ARMIES);
        unitedStates.setCurrentOwner(player);
        unitedStates.setNumberOfArmies(US_ARMIES);

        fortifyPhase = new FortifyPhase(board, player);

        fortifyPhase.selectMovingFrom(canada);
        fortifyPhase.selectMovingTo(unitedStates);
        fortifyPhase.setArmiesToMove(MOVING_ARMIES);

        assertEquals(true, fortifyPhase.fortify());
    }

    @Test
    public void testFortify_fromCanadaToBrazil() {
        canada.setCurrentOwner(player);
        canada.setNumberOfArmies(CANADA_ARMIES);
        unitedStates.setCurrentOwner(player);
        brazil.setCurrentOwner(player);

        fortifyPhase = new FortifyPhase(board, player);

        fortifyPhase.selectMovingFrom(canada);
        fortifyPhase.selectMovingTo(brazil);
        fortifyPhase.setArmiesToMove(MOVING_ARMIES);

        assertEquals(true, fortifyPhase.fortify());
    }

    @Test
    public void testExpectedTotalArmiesAfterFortify() {
        canada.setCurrentOwner(player);
        canada.setNumberOfArmies(CANADA_ARMIES);
        unitedStates.setCurrentOwner(player);
        unitedStates.setNumberOfArmies(US_ARMIES);

        fortifyPhase = new FortifyPhase(board, player);

        fortifyPhase.selectMovingFrom(canada);
        fortifyPhase.selectMovingTo(unitedStates);
        fortifyPhase.setArmiesToMove(MOVING_ARMIES);
        fortifyPhase.fortify();

        int totalUSArmies = MOVING_ARMIES + US_ARMIES;
        assertEquals(totalUSArmies, unitedStates.getNumberOfArmies());
    }


}