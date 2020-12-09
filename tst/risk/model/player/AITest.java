package risk.model.player;

import org.junit.Before;
import org.junit.Test;
import risk.model.board.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AITest {
    private static final int PLAYER_ID = 1;
    Player player;
    List<Country> countriesOwned;

    @Before
    public void setUp() {
        player = new AI(PLAYER_ID, null);
        countriesOwned = new ArrayList<>();
    }

    @Test
    public void testGetId() {
        assertEquals(PLAYER_ID, player.getId());
    }

    @Test
    public void testPerformDraft() {
    }

    @Test
    public void testPerformAttack() {
    }

    @Test
    public void testPerformFortify() {
    }
}