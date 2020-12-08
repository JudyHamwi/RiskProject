package risk.model;

import risk.model.marshalling.GameMarshaller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    private static final String DEFAULT_MAP_ROOT = "maps/default";
    private static final String USER_MAP_ROOT = "maps/user";

    private static final GameMarshaller GAME_MARSHALLER = new GameMarshaller();
    private static final String JSON_EXTENSION = ".json";

    private final String saveFolderRoot;

    public GameRepository(String saveFolderRoot) {
        this.saveFolderRoot = saveFolderRoot;
    }

    public void saveMap(String name, Game game){
        String mapJson = GAME_MARSHALLER.toJson(game);

        String filePath = getFilePath(name);

        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))){
            fileWriter.write(mapJson);
        } catch (IOException e){
            throw new RuntimeException("was not able to save the game: " + name, e);
        }
    }

    public Game loadMap ( String name){
        String filePath = getFilePath(name);

        String mapJson;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))){
            mapJson = fileReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Was not able to load map: " + name, e);
        }
        final Game game = GAME_MARSHALLER.fromJson(mapJson);

        return game;
    }

    public List<String> getGameNames() {
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
            throw new IllegalStateException("The configured game folder is not a directory: " + levelFolder.getAbsolutePath());
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
