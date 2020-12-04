package risk.model.board;

import com.google.gson.Gson;

public class BoardMarshaller {
    final private Gson gson = new Gson();

    public Board fromJson(final String json) {
        return gson.fromJson(json, Board.class);
    }

    public String toJson(final Board board) {
        return gson.toJson(board);
    }
}
