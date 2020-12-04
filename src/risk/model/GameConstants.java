package risk.model;

import java.util.HashMap;
import java.util.Map;

public class GameConstants {
    private final Map<Integer, Integer> playerCountToArmyMap;

    public GameConstants() {
        this.playerCountToArmyMap = new HashMap<>();
        playerCountToArmyMap.put(2, 50);
        playerCountToArmyMap.put(3, 35);
        playerCountToArmyMap.put(4, 30);
        playerCountToArmyMap.put(5, 25);
        playerCountToArmyMap.put(6, 20);
    }

    public int getInitialArmyDraft(final int numPlayers) {
        if (!playerCountToArmyMap.containsKey(numPlayers)) {
            throw new IllegalArgumentException("There cannot be " + numPlayers + " players");
        }

        return playerCountToArmyMap.get(numPlayers);
    }
}