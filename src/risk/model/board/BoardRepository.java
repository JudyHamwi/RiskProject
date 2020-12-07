package risk.model.board;

import risk.model.marshalling.BoardMarshaller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BoardRepository {

    private static final String DEFAULT_MAP_ROOT = "maps/default";
    private static final String USER_MAP_ROOT = "maps/user";

    private static final BoardMarshaller BOARD_MARSHALLER = new BoardMarshaller();
    private static final String JSON_EXTENSION = ".json";

    private final String saveFolderRoot;

    public BoardRepository(String saveFolderRoot) {
        this.saveFolderRoot = saveFolderRoot;
    }

    public static BoardRepository getPremadeBoardRepository(){

        return new BoardRepository(DEFAULT_MAP_ROOT);
    }

    public static BoardRepository getUserBoardRepository(){

        return new BoardRepository(USER_MAP_ROOT);
    }

    public void saveMap(String name, Board board){
        String mapJson = BOARD_MARSHALLER.toJson(board);

        String filePath = getFilePath(name);

        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))){
            fileWriter.write(mapJson);
        } catch (IOException e){
            throw new RuntimeException("was not able to save the map: " + name, e);
        }
    }

    public Board loadMap ( String name){
        String filePath = getFilePath(name);

        String mapJson;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))){
            mapJson = fileReader.readLine(); //But it's more than one line
        } catch (IOException e) {
            throw new RuntimeException("Was not able to load map: " + name, e);
        }
        final Board board = BOARD_MARSHALLER.fromJson(mapJson);

        return board;
    }

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

    private String getFilePath(String name) {
        return new StringBuilder(saveFolderRoot)
                .append('/')
                .append(name)
                .append(JSON_EXTENSION)
                .toString();
    }
}
