package risk.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import risk.model.board.Board;
import risk.model.board.BoardMarshaller;
import risk.model.board.OriginalBoardFactory;
import risk.model.marshalling.BoardSerializer;

import static org.junit.Assert.*;

public class BoardSerializerTest {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Board.class, new BoardSerializer(new BoardMarshaller()))
            .create();

    @Test
    public void testWriteAndRead() {
        final Board board = new OriginalBoardFactory().build();

        final String json = gson.toJson(board);
        final Board fromJson = gson.fromJson(json, Board.class);

        assertEquals(board, fromJson);
    }
}