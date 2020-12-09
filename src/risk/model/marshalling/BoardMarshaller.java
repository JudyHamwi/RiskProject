package risk.model.marshalling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import risk.model.board.Board;

/**
 * Board Marshaller, responsible for governing the process of serializing a Board Object back into Json data.
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class BoardMarshaller {
    final private Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
            .enableComplexMapKeySerialization()
            .create();

    /**
     * serializes a board to json.
     *
     * @param board, board to serialize.
     * @return serialized board in json form.
     */
    public String toJson(final Board board) {
        final SerializableBoard serializableBoard = SerializableBoard.fromBoard(board);
        return gson.toJson(serializableBoard);
    }

    /**
     * converts from a json to a Board object.
     *
     * @param json , json file to convert.
     * @return Board Object from a json file.
     */
    public Board fromJson(final String json) {
        final SerializableBoard serializableBoard = gson.fromJson(json, SerializableBoard.class);
        return serializableBoard.toBoard();
    }

}