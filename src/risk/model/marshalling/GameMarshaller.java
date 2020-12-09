package risk.model.marshalling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import risk.model.Game;

/**
 * Game Marshaller, responsible for governing the process of serializing a Game Object back into Json data.
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class GameMarshaller {
    final private Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
            .enableComplexMapKeySerialization()
            .create();

    /**
     * serializes a Game object to json.
     *
     * @param game, game to marshall.
     * @return serialized Game object in json form.
     */
    public String toJson(final Game game) {
        final SerializableGame serializableGame = SerializableGame.fromGame(game);
        return gson.toJson(serializableGame);

    }

    /**
     * deserializes a json file to a Game Object.
     *
     * @param json, json file to convert.
     * @return a Game Object.
     */
    public Game fromJson(final String json) {
        final SerializableGame serializableGame = gson.fromJson(json, SerializableGame.class);
        return serializableGame.toGame();
    }
}
