package risk.view;

import risk.model.*;
import risk.model.board.Board;
import risk.model.board.Country;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.model.player.Player;
import risk.model.player.User;

import javax.swing.*;
import java.util.List;

/**
 * Risk View listener of the model. Updates the view when the model applies a change.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public interface RiskView {
     JFrame getRiskFrame();

     void handleNewGame(Game game, Board board);

     void handleInitialize(Game game);

     void handleEndTurn(Player currentPlayer);

     void handlePrintHelp(Game game, String pH);

     void handleCanAttackFrom(Country country);

     void handleCanNotAttackFrom( );

     void handleNewAttack();

     BoardView getBoardView();

     void handleAttackResult(Country attackerCountry, Country defenderCountry, boolean attackSuccess, boolean winner, Player playerRemoved);

     void handleNewFortifyPhase(Player fortifier);

     void handleFortifyFromSelected(Country country);

     void handleCanNotFortifyArmies(Game game);

     void handleCanNotFortify();

     void handleFortifyToSelected();

     void handleAddedArmy(Country country, int armiesLeftToDraft);

     void handleCanNotDraftFrom();

     void handleSetNumOfAIPlayers(int numPlayers);

     void handleAITurn(int numberOfCountries, Player player);

     void handleEndGame(Player winner);

     void handleNewDraftPhase(User drafter, DraftPhase draftPhase);

     int getNumber(int min, int max, String message);

     void handleNewAttackPhase();

}