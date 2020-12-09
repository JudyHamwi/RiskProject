package risk.model;

import risk.model.marshalling.GameMarshaller;

import java.io.*;

public class GameRepository {

    private static final GameMarshaller GAME_MARSHALLER = new GameMarshaller();
    private static final String JSON_EXTENSION = ".json";
    private static final String SAVE_GAME_FILE = "saveGame" ;

    private final String saveFolderRoot;

    public GameRepository(String saveFolderRoot) {
        this.saveFolderRoot = saveFolderRoot;
    }

    public void saveGame(Game game){
        String gameJson = GAME_MARSHALLER.toJson(game);

        String filePath = getFilePath();

        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))){
            fileWriter.write(gameJson);
        } catch (IOException e){
            throw new RuntimeException("was not able to save the game." ,e);
        }
    }


    public Game loadGame(){
        String filePath = getFilePath();

        String gameJson;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))){
            gameJson = fileReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Was not able to load game." , e);
        }
        final Game game = GAME_MARSHALLER.fromJson(gameJson);

        return game;
    }



    private String getFilePath() {
        return new StringBuilder(saveFolderRoot)
                .append('/')
                .append(SAVE_GAME_FILE)
                .append(JSON_EXTENSION)
                .toString();
    }
}
