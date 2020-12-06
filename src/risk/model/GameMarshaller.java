package risk.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import risk.model.player.Player;
import risk.view.RiskView;

public class GameMarshaller {
    private static final Gson MARSHALLER = new GsonBuilder().enableComplexMapKeySerialization()
            .registerTypeAdapter(Player.class, new InterfaceAdapter<Player>())
            .registerTypeAdapter(RiskView.class, new InterfaceAdapter<RiskView>())
            // add Type adapter for board
            .create();

    public String toJson(final Game game) {
        return MARSHALLER.toJson(game);
    }

    public Game fromJson(final String levelJson) {
        return MARSHALLER.fromJson(levelJson, Game.class);
    }
}
