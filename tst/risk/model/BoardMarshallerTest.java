package risk.model;

import org.junit.Test;
import risk.model.board.Board;
import risk.model.board.BoardMarshaller;
import risk.model.board.OriginalBoardFactory;

import static org.junit.Assert.assertEquals;

public class BoardMarshallerTest {

    final Board board = new OriginalBoardFactory().build();
    final BoardMarshaller marshaller = new BoardMarshaller();

    @Test
    public void testToAndFromJson() {
        final String boardJson = marshaller.toJson(board);

        final Board fromJson = marshaller.fromJson(boardJson);

        assertEquals(board, fromJson);
    }
}