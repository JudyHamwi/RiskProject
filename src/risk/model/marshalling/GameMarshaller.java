package risk.model.marshalling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import risk.model.Game;

public class GameMarshaller {
    final private Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
            .enableComplexMapKeySerialization()
            .create();

    public String toJson(final Game game) {
        final SerializableGame serializableGame = SerializableGame.fromGame(game);
        return gson.toJson(serializableGame);

    }


    public Game fromJson(final String json) {
        final SerializableGame serializableGame = gson.fromJson(json, SerializableGame.class);
        return serializableGame.toGame();
    }
}
