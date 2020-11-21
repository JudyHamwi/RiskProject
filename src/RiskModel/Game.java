package RiskModel;

import RiskView.RiskView;
import RiskView.RiskViewFrame;

import javax.swing.*;
import java.util.Collections;
import java.util.Random;
import java.util.*;


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

    public static String testVariable = "testV2";

    private Board board;
    private GameState gameState;
    private PhaseState phaseState;
    private boolean finished;
    private int playerArmy;
    public  LinkedList<Player> players;
    private int numPlayers;
    public Player currentPlayer;
    private ArrayList<RiskView> riskViews;
    private Country attackCountry;
    private HashMap<Integer, Integer> armiesForPlayers;

    /**
     * Starts a new RISKModel.Game
     */
    public Game() {
        players = new LinkedList<>();
        board = new Board();
        riskViews = new ArrayList<>();
        this.gameState = GameState.INITIALIZING;
        this.phaseState = PhaseState.DRAFT_PHASE;
        this.armiesForPlayers = new HashMap<>();
        setArmiesForPlayers();
    }

    /**
     * Initalizes the start of the RISKModel.Game
     *
     * @param numberOfPlayers that will play the game
     */
    public void initialize(int numberOfPlayers) {
        addPlayers(numberOfPlayers);
        initialArmyForPlayer();
        distributeCountries();
        distributeRandomArmyToCountry();
        currentPlayer = players.getFirst();
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
     * gets the current phase state of the player
     * @return PhaseSate of the player
     */
    public PhaseState getPhaseSate(){ return this.phaseState;}

    /**
     * Adds a number of players to the game
     *
     * @param numberOfPlayers that will play the game
     */
    public void addPlayers(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player());
        }
    }

    /**
     * Distribute equal amount of random countries to each player
     */
    private void distributeCountries() {
        Collections.shuffle(board.getCountries());
        int totalCountries = board.getCountries().size();
        int leftovers = totalCountries % players.size();
        int countryLoop = totalCountries - leftovers;
        for (int i = 0; i < countryLoop; i += players.size()) {
            for (int j = 0; j < players.size(); j++) {
                players.get(j).addCountry(board.getCountries().get(i + j));
            }
        }

        if (leftovers > 0) {
            int i = totalCountries - leftovers;
            for (int j = 0; j < leftovers; j++) {
                players.get(j).addCountry(board.getCountries().get(i + j));
            }
        }
    }

    public void setNumberOfPlayers(int numberOfPlayers){
        numPlayers=numberOfPlayers;
    }

    /**
     * sets the number of initial armies according to the number of players
     */
    public void setArmiesForPlayers() {
        armiesForPlayers.put(2, 50);
        armiesForPlayers.put(3, 35);
        armiesForPlayers.put(4, 30);
        armiesForPlayers.put(5, 25);
        armiesForPlayers.put(6, 20);
    }

    /**
     * Calculates the number of armies that will be assigned to every player
     */
    public void initialArmyForPlayer() {
        playerArmy = armiesForPlayers.get(players.size());
        for (Player p : players) {
            p.addPlayerArmy(playerArmy);
        }
    }

    /**
     * Distributes one army to every country owned by the players
     */
    private void distributeOneArmyToCountry() {
        for (Player p : players) {
            for (Country c : p.getCountriesOwned()) {
                c.addArmy(1);
                p.addPlayerArmy(-1);
            }
        }
    }

    /**
     * distributes a random number of armies to every country of the players
     */
    private void distributeRandomArmyToCountry() {
        distributeOneArmyToCountry();
        Random r = new Random();
        for (Player p : players) {
            for (Country c : p.getCountriesOwned()) {
                if (p.getPlayerArmy() > 0) {
                    int randomArmy = (r.nextInt(p.getPlayerArmy())) + 1;
                    c.addArmy(randomArmy);
                    p.addPlayerArmy(-randomArmy);
                }
            }
        }
    }

    public void draftPhase(Country countryToReinforce){
        if( phaseState == PhaseState.DRAFT_PHASE && countryToReinforce.getCurrentOwner().equals(currentPlayer) ){
            DraftPhase playerDraft = new DraftPhase(currentPlayer);
            int totalBonusArmies = playerDraft.getTotalBonusArmies();
            currentPlayer.addPlayerArmy(totalBonusArmies); //add the bonus army to the total number of armies the player has
            while(totalBonusArmies > 0){
               int armiesToPlace = Integer.parseInt(JOptionPane.showInputDialog("How many bonus armies would you like to add to "+countryToReinforce.getCountryName() +"?"));
                if (armiesToPlace <= totalBonusArmies){
                    countryToReinforce.addArmy(armiesToPlace);
                    totalBonusArmies -= armiesToPlace;
                }
            }
            if (totalBonusArmies == 0) { phaseState = PhaseState.ATTACK_PHASE;}
        }
    }
    /**
     * Initiates the attack phase of the game, which is entered when a player decided to attack
     *
     * @param defenderCountry the country that will be defending from the attack
     */
    public void attackPhase(Country defenderCountry) {
        if (currentPlayer.canAttack(attackCountry, defenderCountry)) {
            AttackPhase playerAttack = new AttackPhase(currentPlayer, attackCountry, defenderCountry);
            Boolean attackSuccess = playerAttack.attack();
            Player playerRemoved=removePlayer();
            boolean winner = checkWinner();
            setContinentsOwned();
            for (RiskView rv : riskViews) {
                rv.handleAttackPhase(this, attackCountry, defenderCountry, attackSuccess, winner, playerRemoved);
            }
        } else {
            for (RiskView rv : riskViews) {
                rv.handleCanNotAttackFrom(this);
            }
        }
    }

    /**
     * Removes a player from the game if lost all their armies
     */
    public Player removePlayer() {
        for (Player p : players) {
            if (p.getCountriesOwned().size() == 0) {
                players.remove(p);
                System.out.println(p + " has lost.");
                return p;
            }
        }
        return null;
    }

    /**
     * checks if a player won the game if it conquered all the countries in the board
     */
    public boolean checkWinner() {
        if (players.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initialzes the state of the game at the start of the game
     */
    public void theInitialState() {
        initialize(numPlayers);
        this.gameState = GameState.IN_PROGRESS;
        for (RiskView rv : riskViews) {
            rv.handleInitialization(this, gameState, currentPlayer, numPlayers);
        }
    }

    /**
     * Play loop of the game that responds to the player's turns commands
     */
    public void play() {
        theInitialState();
        while (gameState == GameState.IN_PROGRESS) {
            System.out.println(currentPlayer + ", it is your turn.");
            try {
            } catch (Exception e) {
                System.out.println("Exception Occured: " + e);
                System.out.println("Please enter command again...");
            }
        }
    }

    /**
     * ends the turn of the current player and passes the turn to the next player
     */
    public void endTurn() {
        gameState = GameState.COMPLETED;
        Player p = currentPlayer;
        if (players.getLast().equals(p)) {
            currentPlayer = players.getFirst();
        } else {
            int i = players.indexOf(p);
            currentPlayer = players.get(i + 1);
        }
        gameState = GameState.IN_PROGRESS;
        // call for the draftphase

        for (RiskView rv : riskViews) {
            rv.handleEndTurn(this, currentPlayer);
        }

    }

    /**
     * Prints the initial state of the game after the initialization happens
     */
    private void printInitialState() {
        System.out.println("HERE IS THE INITIAL STATE OF THE MAP: ");
        for (Player p : players) {
            System.out.println(p);
            System.out.println("owns: " + p.getCountriesOwned());
            for (Country c : p.getCountriesOwned()) {
                System.out.println(" " + c + " Number of Armies: " + c.getNumberOfArmies());
            }
        }
        printHelp();
    }

    /**
     * returns the number of players in the game
     *
     * @return number of players in the game
     */
    public int getNumPlayers() {
        return numPlayers;
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

        for (RiskView rv : riskViews) {
            rv.handlePrintHelp(this, pH);
        }
    }

    /**
     * Responds to the command of the player to attack
     *
     * @param p player that wants to attack
     */
    public void attack(Player p) {

        String attackingCountry = null;
        String defendingCountry = null;
        Country attackingC = null;
        Country defendingC = null;
        this.gameState = GameState.IN_PROGRESS;
        for (Country c : board.getCountries()) {
            if (c.getCountryName().equals(attackingCountry)) {
                attackingC = c;
            }
        }

        for (Country c : board.getCountries()) {
            if (c.getCountryName().equals(defendingCountry)) {
                defendingC = c;
            }
        }

        attackPhase(defendingC);
    }

    /**
     * adds the view to the list of viewers of the game model
     *
     * @param rv view of the model
     */
    public void addRiskView(RiskView rv) {
        riskViews.add(rv);
        for (RiskView rv2 : riskViews) {
            rv2.handleNewGame(this, board);
        }
    }

    /**
     * removes a view from the viewers of the game model
     *
     * @param rv view to be removed from the viewers of the model
     */
    public void removeRiskView(RiskViewFrame rv) {
        riskViews.remove(rv);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    /**
     * checks of the attacker country chosen by the player is a valid country
     * that the play can attack from, following the rules of the attack
     *
     * @param attackCountry that the player wants to attack from in the attack phase
     */
    public void checkAttackingCountry(Country attackCountry) {
        if (currentPlayer.canAttackFrom(attackCountry)) {
            this.attackCountry = attackCountry;
            for (RiskView rv : riskViews) {
                rv.handleCanAttackFrom(this, attackCountry);
            }
        } else {
            for (RiskView rv : riskViews) {
                rv.handleCanNotAttackFrom(this);
            }
        }
    }

    /**
     * getter for the board
     * @return the board of the game
     */
    public Board getBoard() {
        return board;
    }

    /**
     * getter for the attacker country
     */
    public Country getAttackingCountry(){
        return attackCountry;
    }


    public void setContinentsOwned(){
        for(int i = 0; i < board.getContinents().size(); i++){
            //check continent ownership and add it to the player's owned continents list
            if(currentPlayer.getCountriesOwned().containsAll(board.getContinents().get(i).getContinentCountries())){
                currentPlayer.addContinent(board.getContinents().get(i));
            }
        }
    }
}
