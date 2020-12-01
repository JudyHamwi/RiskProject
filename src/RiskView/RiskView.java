package RiskView;

import RiskModel.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Risk View listener of the model. Updates the view when the model applies a change.
 * @version 1.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public interface RiskView {
     JFrame getRiskFrame();
     void handleNewGame(Game game, Board board);
     void handleInitialization(Game game, GameState state, Player player, int numPlayers, int draftArmies);
     void handleEndTurn(Game game, Player currentPlayer, int draftArmies, boolean turnComplete);
     void handlePrintHelp(Game game, String pH);
     void handleCanAttackFrom(Game game, Country country);
     void handleCanNotAttackFrom(Game game);
     void handleNewAttack();
     BoardView getBoardView();
     void handleAttackPhase(Game game, Country attackerCountry, Country defenderCountry, boolean attackSuccess, boolean winner, Player playerRemoved);
     void handleNewFortifyPhase();
     void handleCanFortifyFrom(Game game, Country country, ArrayList<Country> connectedCountries);
     void handleCanNotFortifyArmies(Game game);
     void handleCanNotFortify(Game game);
     void handleFortifyPhase(Game game,Country movingFrom, Country movingTo);
     void handleAddedArmy(Game game, Country country, int draftArmies);
     void handleCanNotDraftFrom(Game game);
     void handleSetNumOfAIPlayers(int numPlayers);
}
