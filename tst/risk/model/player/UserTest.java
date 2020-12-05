package risk.model.player;

import org.junit.Before;
import org.junit.Test;
import risk.model.board.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {
    private static final int PLAYER_ID = 1;
    Player player;
    List<Country> countriesOwned;

    @Before
    public void setUp() {
       player = new User(PLAYER_ID, null);
       countriesOwned = new ArrayList<>();
    }

    @Test
    public void testPlayer(){
        assertTrue(player.equals(player));
    }

    @Test
    public void testGetPlayerID() {
        assertEquals(PLAYER_ID, player.getId());
    }
}