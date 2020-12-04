package risk.model;

/**
 *  RISKModel.GameState
 *  The RISKModel.GameState class is an Enum class that is meant to show changes in the game’s
 *  progression. For our game we have game states INITIALIZING, IN_PROGRESS and COMPLETED to show if
 *  the game is still being played or if the game has been finished.
 */
public enum GameState {
    INITIALIZING,
    ATTACK_PHASE,
    DRAFT_PHASE,
    FORTIFY_PHASE,
    END_TURN,
    COMPLETED
}