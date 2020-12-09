package risk.model.marshalling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import risk.model.Game;
import risk.model.board.Board;
import risk.model.marshalling.BoardSerializer;
import risk.model.marshalling.InterfaceAdapter;
import risk.model.player.Player;
import risk.view.RiskView;

public class GameMarshaller {
   // private static final Gson MARSHALLER = new GsonBuilder().enableComplexMapKeySerialization()
     //       .registerTypeAdapter(Player.class, new InterfaceAdapter<Player>())
       //     .registerTypeAdapter(RiskView.class, new InterfaceAdapter<RiskView>())
            // add Type adapter for board
         //   .registerTypeAdapter(Board.class, new BoardSerializer(new BoardMarshaller()))
           // .create();

    final private Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
            .enableComplexMapKeySerialization()
            .create();

    public String toJson(final Game game) {
       // return MARSHALLER.toJson(game);
        final SerializeGame serializableGame =SerializeGame.fromGame(game);
        return gson.toJson(serializableGame);

    }


    public Game fromJson(final String json) {
        //return MARSHALLER.fromJson(levelJson, Game.class);
        final SerializeGame serializeGame =  gson.fromJson(json, SerializeGame.class);
        return serializeGame.toGame();
    }


}
