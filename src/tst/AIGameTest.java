package tst;
/**
 * One of the phases of the game. Player enters RISKModel.FortifyPhase after their RISKModel.AttackPhase.
 * @version 2.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 *
 * Test the AI methods in Game class
 */
import risk.model.board.Country;
import risk.model.Game;
import risk.model.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AIGameTest {
    Game game;
    Player AI;
    Player human;
    Country c1, c2, c3, c4;
    List<Integer> attackerDiceValues;
    List<Integer> defenderDiceValues;

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
        game.addPlayers(2);
        game.setNumberOfAIPlayers(1);
        this.AI = game.players.get(0);
        this.human = game.players.get(1);
        c1 = new Country("C1");
        c2 = new Country("C2");
        c3 = new Country("C3");
        c4 = new Country("C4");
        c1.setAdjacentCountry(c2);
        c2.setAdjacentCountry(c1);
        c2.setAdjacentCountry(c3);
        c3.setAdjacentCountry(c2);
        c3.setAdjacentCountry(c4);
        c4.setAdjacentCountry(c3);
        c1.addArmy(1);
        c2.addArmy(10);
        c3.addArmy(1);
        c4.addArmy(1);
        AI.addCountry(c1);
        AI.addCountry(c2);
        human.addCountry(c3);
        human.addCountry(c4);
        game.currentPlayer = game.players.getFirst();
    }

    /*@Test
    public void testSetAIPlayers(){
        int numberOfAIPlayers = 2;
        game.setNumberOfAIPlayers(numberOfAIPlayers);
        assertEquals(numberOfAIPlayers, game.getNumAIPlayers());
    }*/

    @Test
    public void testDraftAI(){
        game.AIDraft();
        int numberOfArmiesOnC1 = 4;
        assertEquals(numberOfArmiesOnC1, c1.getNumberOfArmies());
    }

    @Test
    public void testFortifyAI(){
        game.AIFortify();
        int numArmiesOnC1 = 6;
        int numArmiesOnC2 = 5;
        assertEquals(numArmiesOnC1, c1.getNumberOfArmies());
        assertEquals(numArmiesOnC2, c2.getNumberOfArmies());
    }

}