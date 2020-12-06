package risk.model.marshalling;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import risk.model.board.Board;
import risk.model.board.BoardMarshaller;

import java.io.IOException;

public class BoardSerializer extends TypeAdapter<Board> {
    private static final String JSON_FIELD = "boardJson";
    private final BoardMarshaller marshaller;

    public BoardSerializer(final BoardMarshaller marshaller) {
        this.marshaller = marshaller;
    }

    @Override
    public void write(JsonWriter jsonWriter, Board board) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name(JSON_FIELD);
        jsonWriter.value(marshaller.toJson(board));
        jsonWriter.endObject();
    }

    @Override
    public Board read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName(); // This is required. We already know there's only 1 field.
        final String boardJson = jsonReader.nextString();
        jsonReader.endObject();
        return marshaller.fromJson(boardJson);
    }
}
