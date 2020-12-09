package risk.model;

/**
 *  Game State.
 *
 *  an Enum class that is meant to show changes in the game’s progression.
 */
public enum GameState {
    INITIALIZING,
    ATTACK_PHASE,
    DRAFT_PHASE,
    FORTIFY_PHASE,
    END_TURN,
    COMPLETED
}