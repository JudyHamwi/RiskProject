package risk.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Game Constants class.
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class GameConstants {
    private final Map<Integer, Integer> playerCountToArmyMap;

    /**
     * GameConstants Constructor.
     */
    public GameConstants() {
        this.playerCountToArmyMap = new HashMap<>();
        playerCountToArmyMap.put(2, 50);
        playerCountToArmyMap.put(3, 35);
        playerCountToArmyMap.put(4, 30);
        playerCountToArmyMap.put(5, 25);
        playerCountToArmyMap.put(6, 20);
    }

    /**
     * gets the initial number of armies to draft according to the number of players.
     *
     * @param numPlayers number of players.
     * @return number of armies for each player during the initial draft.
     */
    public int getInitialArmyDraft(final int numPlayers) {
        if (!playerCountToArmyMap.containsKey(numPlayers)) {
            throw new IllegalArgumentException("There cannot be " + numPlayers + " players");
        }

        return playerCountToArmyMap.get(numPlayers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameConstants that = (GameConstants) o;
        return Objects.equals(playerCountToArmyMap, that.playerCountToArmyMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerCountToArmyMap);
    }
}