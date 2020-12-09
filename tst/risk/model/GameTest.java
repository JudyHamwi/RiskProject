package risk.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import risk.model.board.Board;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.model.phase.PhaseFactory;
import risk.model.player.Player;
import risk.view.RiskView;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

/**
 * One of the phases of the game. Player enters RISKModel.FortifyPhase after their RISKModel.AttackPhase.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * <p>
 * Test the Game class
 * @version 2.0
 */

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    private Country canada = new Country("Canada");
    private Country mexico = new Country("Mexico");

    @Mock
    private Player mockPlayer1;
    @Mock
    private Player mockPlayer2;
    @Mock
    private RiskView mockView;
    @Mock
    private DraftPhase mockDraft;
    @Mock
    private AttackPhase mockAttack;
    @Mock
    private FortifyPhase mockFortify;

    @Mock
    private Board mockBoard;
    @Mock
    private PhaseFactory mockPhaseFactory;

    @InjectMocks
    private Game game;

    private InOrder inOrder;

    @Before
    public void setup() {
        inOrder = inOrder(mockBoard, mockPlayer1, mockPlayer2, mockView);
        game.addRiskView(mockView);
        game.addPlayer(mockPlayer1);
        game.addPlayer(mockPlayer2);

        when(mockPhaseFactory.buildDraftPhase(game)).thenReturn(mockDraft);
        when(mockPhaseFactory.buildAttackPhase(game)).thenReturn(mockAttack);
        when(mockPhaseFactory.buildFortifyPhase(game)).thenReturn(mockFortify);
    }

    @Test
    public void testRunGame() {
        when(mockBoard.getCountriesOwnedBy(mockPlayer1))
                .thenReturn(List.of(canada))
                .thenReturn(List.of(canada))
                .thenReturn(List.of(canada, mexico));
        when(mockBoard.getCountriesOwnedBy(mockPlayer2))
                .thenReturn(List.of(mexico))
                .thenReturn(List.of(mexico))
                .thenReturn(List.of());

        game.play();

        assertEquals(GameState.INITIALIZING, game.getState());
    }

}