package risk.model;

import risk.model.board.Board;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.model.phase.PhaseFactory;
import risk.model.player.AI;
import risk.model.player.Player;
import risk.model.player.User;
import risk.view.RiskView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The RISK RISKModel.Game that initializes the game, manages the Attack Phase, and keeps track
 * of the turn of each player and winning player
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */

public class Game {

    private final Board board;
    private final PhaseFactory phaseFactory;
    private final List<Player> players = new ArrayList<>();
    private final List<RiskView> riskViews = new ArrayList<>();

    private GameState gameState = GameState.INITIALIZING;
    private Player currentPlayer;

    /**
     * Starts a new RISKModel.Game
     */
    public Game(final Board board, final PhaseFactory phaseFactory) {
        this.board = board;
        this.phaseFactory = phaseFactory;
    }

    /**
     * Play loop of the game that responds to the player's turns commands
     */
    public void play() {
            runDraft();
            runAttack();
            removeDeadPlayers();
            if (foundWinner()) {
                runEndGame();
            }else {
                runFortify();
                runEndTurn();
            }

    }

    public void startNewGame() {
        gameState = GameState.INITIALIZING;
        board.assignCountries(players);
        board.distributeArmies(players);
        currentPlayer = players.get(0);
        riskViews.forEach(rv -> rv.handleInitialize(this));
        startDraft();
    }

    private void runDraft() {
        gameState = GameState.DRAFT_PHASE;
        final DraftPhase draftPhase = phaseFactory.buildDraftPhase(this);
        currentPlayer.performDraft(draftPhase);
    }

    private void runAttack() {
        gameState = GameState.ATTACK_PHASE;
        final AttackPhase attackPhase = phaseFactory.buildAttackPhase(this);
        currentPlayer.performAttack(attackPhase);
    }

    private void runFortify() {
        gameState = GameState.FORTIFY_PHASE;
        final FortifyPhase fortifyPhase = phaseFactory.buildFortifyPhase(this);
        currentPlayer.performFortify(fortifyPhase);
    }

    /**
     * ends the turn of the current player and passes the turn to the next player
     */
    public void runEndTurn() {
        gameState = GameState.END_TURN;
        currentPlayer = getNextPlayer();
        riskViews.forEach(rv -> rv.handleEndTurn(currentPlayer));
        if(currentPlayer instanceof AI){
            play();
        }else {
            startDraft();
        }
    }

    /**
     * Removes a player from the game if lost all their armies
     */
    private void removeDeadPlayers() {
        final List<Player> deadPlayers = players.stream()
                .filter(p -> board.getCountriesOwnedBy(p).isEmpty())
                .peek(p -> System.out.format("%s has lost\n", p))
                .collect(Collectors.toList());

        players.removeAll(deadPlayers);
    }

    /**
     * checks if a player won the game if it conquered all the countries in the board
     */
    private boolean foundWinner() {
        return players.size() <= 1;
    }

    private void runEndGame() {
        gameState = GameState.COMPLETED;
        final Player winner = players.get(0);
        System.out.format("Congratulations %s you conquered the world!\n", winner);
        riskViews.forEach(rv -> rv.handleEndGame(winner));
    }


    /**
     * getter for the board
     *
     * @return the board of the game
     */
    public Board getBoard() {
        return board;
    }

    /**
     * gets the current state of the game
     *
     * @return RISKModel.GameState of the game
     */
    public GameState getState() {
        return this.gameState;
    }

    /**
     * set the phase of the game state
     */
    public void setState(GameState state) {
        gameState = state;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void addPlayer(final Player player) {
        players.add(player);
    }


    private Player getNextPlayer() {
        final int currentPlayerPos = players.indexOf(currentPlayer);
        final int nextPlayerPos =  (currentPlayerPos + 1) % players.size();
        return players.get(nextPlayerPos);
    }

    /**
     * returns the number of players in the game
     *
     * @return number of players in the game
     */
    public int getNumPlayers() {
        return players.size();
    }

    /**
     * adds the view to the list of viewers of the game model
     *
     * @param rv view of the model
     */
    public void addRiskView(RiskView rv) {
        riskViews.add(rv);
        rv.handleNewGame(this, board);
    }

    public List<RiskView> getViews() {
        return riskViews;
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

    /**
     * Prints the help information when the player requests help
     */
    public void printHelp() {
        String pH;
        pH = ("Aim to conquer enemy territories!" + "\n" + "\n" + "In game, you have choices to attack countries and end your turn."
                + "\n" + "To attack, press the country you want to attack with, then press on the attack button followed by a country you wish to attack " +
                "\n" + "Press the attack button to determine" +
                " if you can successfully attack your enemy's territory." + "\n" + "Pass your turn to another player by pressing" +
                " the end turn button." + "\n" + "\n" + "GOOD LUCK!");

        riskViews.forEach(rv -> rv.handlePrintHelp(this, pH));
    }

    private void startDraft(){
        gameState = GameState.DRAFT_PHASE;
        DraftPhase draftPhase=new DraftPhase(board,currentPlayer);
        for(RiskView rv:riskViews){
            User user=(User) currentPlayer;
            rv.handleNewDraftPhase(user,draftPhase);
        }
    }

}