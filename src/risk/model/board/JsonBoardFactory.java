package risk.model.board;

import risk.model.marshalling.BoardMarshaller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonBoardFactory implements BoardFactory {

    private  String filePath;
    private BoardMarshaller boardMarshaller = new BoardMarshaller();

    public JsonBoardFactory(final String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Board build() {
        final String boardJson;
        try {
            boardJson = Files.readString(Path.of(filePath));
        } catch (final IOException e) {
            throw new RuntimeException("Unable to read the json string form file at: " + filePath);
        }

        Board board = boardMarshaller.fromJson(boardJson);

        return board;
    }
}