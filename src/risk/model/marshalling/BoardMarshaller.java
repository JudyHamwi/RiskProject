package risk.model.marshalling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import risk.model.board.Board;

public class BoardMarshaller {
    final private Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
            .enableComplexMapKeySerialization()
            .create();

    public Board fromJson(final String json) {
        final SerializableBoard serializableBoard =  gson.fromJson(json, SerializableBoard.class);
        return serializableBoard.toBoard();
    }

    public String toJson(final Board board) {
        final SerializableBoard serializableBoard = SerializableBoard.fromBoard(board);
        return gson.toJson(serializableBoard);
    }
}