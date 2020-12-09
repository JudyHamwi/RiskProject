package risk.model;

import risk.model.board.Board;
import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.model.phase.PhaseFactory;
import risk.model.player.Player;
import risk.view.RiskView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Game that initializes the game, manages play and keeps track
 * of the turn of each player as well as the state of the game.
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */

public class Game {

    private final Board board;
    private final PhaseFactory phaseFactory;
    private final List<Player> players = new ArrayList<>();
    private final List<RiskView> riskViews = new ArrayList<>();

    private GameState gameState;
    private Player currentPlayer;

    /**
     * creates a new Game Object.
     */
    public Game(final Board board, final PhaseFactory phaseFactory) {
        this.board = board;
        this.phaseFactory = phaseFactory;
        this.gameState = GameState.INITIALIZING;
    }

    /**
     * Play loop of the game depending on the game's state.
     */
    public void play() {
        Thread gameLoop = new Thread("game loop") {
            @Override
            public void run() {
                while (GameState.COMPLETED != gameState) {
                    switch (gameState) {
                        case INITIALIZING:
                            startNewGame();
                            break;
                        case DRAFT_PHASE:
                            runDraft();
                            break;
                        case ATTACK_PHASE:
                            runAttack();
                            removeDeadPlayers();
                            if (foundWinner()) {
                                gameState = GameState.COMPLETED;
                            }
                            break;
                        case FORTIFY_PHASE:
                            runFortify();
                            break;
                        case END_TURN:
                            runEndTurn();
                            break;
                        default:
                            return;
                    }
                }
                runEndGame();
            }
        };
        gameLoop.start();
    }

    /**
     * starts a new game by assigning countries and distributing armies for the players in the game.
     */
    public void startNewGame() {
        board.assignCountries(players);
        board.distributeArmies(players);
        currentPlayer = players.get(0);
        riskViews.forEach(rv -> rv.handleInitialize(this));
        gameState = GameState.DRAFT_PHASE;
    }

    /**
     * creates a draft phase and perform draft action for the current player.
     */
    private void runDraft() {
        final DraftPhase draftPhase = phaseFactory.buildDraftPhase(this);
        gameState = currentPlayer.performDraft(draftPhase);
    }

    /**
     * creates a new attack phase and perform attack action for the current player.
     */
    private void runAttack() {
        final AttackPhase attackPhase = phaseFactory.buildAttackPhase(this);
        gameState = currentPlayer.performAttack(attackPhase);
    }

    /**
     * creates a new fortify phase and perform fortify action for the current player.
     */
    private void runFortify() {
        final FortifyPhase fortifyPhase = phaseFactory.buildFortifyPhase(this);
        currentPlayer.performFortify(fortifyPhase);
        gameState = GameState.END_TURN;
    }

    /**
     * ends the turn of the current player and passes the turn to the next player
     */
    private void runEndTurn() {
        currentPlayer.performEndTurn(riskViews);
        currentPlayer = getNextPlayer();
        riskViews.forEach(rv -> rv.handleEndTurn(currentPlayer));
        gameState = GameState.DRAFT_PHASE;
    }

    /**
     * Removes a player from the game if lost all their armies.
     */
    private void removeDeadPlayers() {
        final List<Player> deadPlayers = new ArrayList<>();
        for (Player p : players) {
            if (board.getCountriesOwnedBy(p).isEmpty()) {
                System.out.format("%s has lost\n", p);
                deadPlayers.add(p);
            }
        }

        players.removeAll(deadPlayers);
    }

    /**
     * checks if a player won the game if it conquered all the countries in the board
     */
    private boolean foundWinner() {
        return players.size() <= 1;
    }

    /**
     * checks if the game state is completed and displays the winner.
     */
    private void runEndGame() {
        gameState = GameState.COMPLETED;
        final Player winner = players.get(0);
        System.out.format("Congratulations %s you conquered the world!\n", winner);
        riskViews.forEach(rv -> rv.handleEndGame(winner));
    }

    /**
     * gets the board of the game.
     *
     * @return the board of the game.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * gets the current state of the game.
     *
     * @return the state of the game.
     */
    public GameState getState() {
        return this.gameState;
    }

    /**
     * set the state of the game.
     */
    public void setState(GameState state) {
        gameState = state;
    }

    /**
     * Gets the current player in the game.
     *
     * @return the current player in the game.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * adds a player to the game.
     *
     * @param player player to add.
     */
    public void addPlayer(final Player player) {
        players.add(player);
    }

    /**
     * gets the turn of the next player in the game.
     *
     * @return the next player.
     */
    private Player getNextPlayer() {
        final int currentPlayerPos = players.indexOf(currentPlayer);
        final int nextPlayerPos = (currentPlayerPos + 1) % players.size();
        return players.get(nextPlayerPos);
    }

    /**
     * gets the number of players in the game.
     *
     * @return number of players in the game.
     */
    public int getNumPlayers() {
        return players.size();
    }

    /**
     * adds the view to the list of viewers of the game model.
     *
     * @param rv view of the model.
     */
    public void addRiskView(RiskView rv) {
        riskViews.add(rv);
        rv.handleNewGame(this, board);
    }

    /**
     * gets the registered views in the game.
     *
     * @return list of views in the game.
     */
    public List<RiskView> getViews() {
        return riskViews;
    }

    /**
     * gets the players in the game.
     *
     * @return List of Players in the game.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * sets the specified player to a current player.
     *
     * @param currentPlayer current player.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * gets the player by it's ID.
     *
     * @param id id of the player.
     * @return the player with the specified ID.
     */
    public Player getPlayer(int id) {
        for (Player p : players) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * adds the loaded view to the list of views.
     *
     * @param riskView view to load.
     */
    public void addLoadView(RiskView riskView) {
        riskViews.add(riskView);
        riskView.handleLoadSavedGame(board, this);
        board.authorizingAdjacentCountries();
    }

    /**
     * Prints the initial state of the game after the initialization happens
     */
    private void printInitialState() {
        System.out.println("HERE IS THE INITIAL STATE OF THE MAP: ");
        for (Player p : players) {
            System.out.println(p);
            System.out.println("owns:");
            board.getCountriesOwnedBy(p).stream()
                    .map(c -> "\t" + c + ", Number of Armies: " + c.getNumberOfArmies())
                    .forEach(System.out::println);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return board.equals(game.board) &&
                players.equals(game.players)&&
                gameState == game.gameState &&
                currentPlayer.equals(game.currentPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, players, gameState, currentPlayer);
    }
}