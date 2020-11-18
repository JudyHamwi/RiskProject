package RiskView;

import RiskModel.*;

/**
 * Risk View listener of the model. Updates the view when the model applies a change.
 * @version 1.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public interface RiskView {
     void handleNewGame(Game game, Board board);
     void handleInitialization(Game game, GameState state, Player player, int numPlayers);
     void handleEndTurn(Game game, Player currentPlayer);
     void handlePrintHelp(Game game, String pH);
     void handleCanAttackFrom(Game game, Country country);
     void handleCanNotAttackFrom(Game game);
     void handleNewAttack();
     BoardView getBoardView();
     void handleAttackPhase(Game game, Country attackerCountry, Country defenderCountry, boolean attackSuccess, boolean winner, Player playerRemoved);
}
