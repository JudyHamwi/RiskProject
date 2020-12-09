package risk.model.board;

import risk.model.marshalling.BoardMarshaller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * JSon Board Factory.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class JsonBoardFactory implements BoardFactory {

    private  String filePath;
    private BoardMarshaller boardMarshaller = new BoardMarshaller();

    /**
     * JsonBoardFactory Constructor
     *
     * @param filePath, the file path to build the board from.
     */
    public JsonBoardFactory(final String filePath) {
        this.filePath = filePath;
    }

    /**
     * builds a Board Object from a Json file.
     * @return, Board object.
     */
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