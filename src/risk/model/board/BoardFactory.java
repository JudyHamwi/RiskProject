package risk.model.board;

/**
 * Board Factory interface
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public interface BoardFactory {
    /**
     * factory method used to create a Board Object.
     *
     * @return a Board object
     */
    Board build();
}
