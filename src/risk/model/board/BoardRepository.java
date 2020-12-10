package risk.model.board;

import risk.model.board.customMaps.AvatarBoardFactory;
import risk.model.board.customMaps.GameOfThronesBoardFactory;
import risk.model.board.customMaps.HeroesMap;
import risk.model.board.customMaps.InvalidBoardFactory;
import risk.model.marshalling.BoardMarshaller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Board Repository class, to save and load a Board object.
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class BoardRepository {

    private static final String DEFAULT_MAP_ROOT = "maps";

    private static final BoardMarshaller BOARD_MARSHALLER = new BoardMarshaller();
    private static final String JSON_EXTENSION = ".json";

    private final String saveFolderRoot;

    /**
     * BoardRepository constructor.
     *
     * @param saveFolderRoot the root of the saved map file.
     */
    public BoardRepository(String saveFolderRoot) {
        this.saveFolderRoot = saveFolderRoot;
    }

    /**
     * gets the pre-made custom map from the maps file.
     *
     * @return the pre-made map.
     */
    public static BoardRepository getPremadeBoardRepository() {

        return new BoardRepository(DEFAULT_MAP_ROOT);
    }

    /**
     * saves a custom map to a .json file from a specified file path.
     *
     * @param name,  name of the file.
     * @param board, the costumed map.
     */
    public void saveMap(String name, Board board) {
        String mapJson = BOARD_MARSHALLER.toJson(board);

        String filePath = getFilePath(name);

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            fileWriter.write(mapJson);
        } catch (IOException e) {
            throw new RuntimeException("was not able to save the map: " + name, e);
        }
    }

    /**
     * loads a custom map from a .json file.
     *
     * @param name, name of the file.
     * @return a costumed map.
     */
    public Board loadMap(String name) {
        String filePath = getFilePath(name);

        String mapJson;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            mapJson = fileReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Was not able to load map: " + name, e);
        }
        final Board board = BOARD_MARSHALLER.fromJson(mapJson);

        return board;
    }

    /**
     * retrieves a list of costumed map names.
     *
     * @return a list of costumed map names.
     */
    public List<String> getMapNames() {
        List<String> mapNames = new ArrayList<>();
        File levelFolder = new File(saveFolderRoot);

        if (levelFolder.exists() && levelFolder.isDirectory()) {
            final File[] levelFiles = levelFolder.listFiles((dir, name) -> name.endsWith(JSON_EXTENSION));
            List<String> toSort = new ArrayList<>();
            for (File levelFile : levelFiles) {
                String fileName = levelFile.getName();
                String replace = fileName.replace(JSON_EXTENSION, "");
                toSort.add(replace);
            }
            toSort.sort(null);
            for (String replace : toSort) {
                mapNames.add(replace);
            }
        } else {
            throw new IllegalStateException("The configured map folder is not a directory: " + levelFolder.getAbsolutePath());
        }

        return mapNames;
    }

    /**
     * builds the string name of the file path.
     *
     * @param name, name of the file.
     * @return a string representation of the file path, name and .json extension
     */
    private String getFilePath(String name) {
        return new StringBuilder(saveFolderRoot)
                .append('/')
                .append(name)
                .append(JSON_EXTENSION)
                .toString();
    }

    /**
     * this is to create the custom maps in the maps folder.
     */
    public static void main(String[] args) {
        final Board originalMap = new OriginalBoardFactory().build();
        final Board heroesMap = new HeroesMap().build();
        final Board gameOfThronesMap = new GameOfThronesBoardFactory().build();
        final Board avatarMap = new AvatarBoardFactory().build();
        final Board invalidMap = new InvalidBoardFactory().build();
        BoardRepository boardRepository = new BoardRepository("src/maps");
        boardRepository.saveMap("original", originalMap);
        boardRepository.saveMap("heroes", heroesMap);
        boardRepository.saveMap("gameOfThrones", gameOfThronesMap);
        boardRepository.saveMap("avatar", avatarMap);
        boardRepository.saveMap("invalidMap", invalidMap);
    }
}
