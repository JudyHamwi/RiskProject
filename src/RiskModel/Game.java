package RiskModel;

import RiskView.RiskView;
import RiskView.RiskViewFrame;

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

    private Board board;
    private GameState gameState;
    private boolean finished;
    private int playerArmy;
    public LinkedList<Player> players;
    private int numPlayers;
    public Player currentPlayer;
    private ArrayList<RiskView> riskViews;
    private Country attackCountry;
    private HashMap<Integer, Integer> armiesForPlayers;
    private Country moveFromCountry;
    private int armiesFortify;

    /**
     * Starts a new RISKModel.Game
     */
    public Game() {
        players = new LinkedList<>();
        board = new Board();
        riskViews = new ArrayList<>();
        this.armiesForPlayers = new HashMap<>();
        setArmiesForPlayers();
    }

    /**
     * Initializes the start of the RISKModel.Game
     *
     * */
    public void initialize(int numberOfPlayers) {
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
     * Adds a number of players to the game
     *
     * @param numberOfPlayers that will play the game
     */
    public void addPlayers(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player());
        }
        for(RiskView rv:riskViews){
            rv.handleSetNumOfAIPlayers(numberOfPlayers);
        }

    }

    /**
     * add the selectd number of AI players
     * @param numberOfAIPlayers
     */
    public void addAIPlayers(int numberOfAIPlayers){
        for (int i=0; i<numberOfAIPlayers;i++){
            players.add(new AIPlayer());
        }
        numPlayers=players.size();
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

    /**
     * attack phase for the player that attacks a country from an adjacent country
     * while validating the rules of the game.
     */
    public void attack(Country defenderCountry){
        if(currentPlayer.canAttack(attackCountry,defenderCountry)) {
            boolean attackSuccess = currentPlayer.attackPhase(defenderCountry, attackCountry);
            Player playerRemoved = removePlayer();
            boolean winner = checkWinner();
            setContinentsOwned();
            for (RiskView rv : riskViews) {
                rv.handleAttackPhase(this, attackCountry, defenderCountry, attackSuccess, winner, playerRemoved);
            }
        }else {
            for (RiskView rv : riskViews) {
                rv.handleCanNotAttackFrom(this);
            }
        }

    }

    /**
     * Implementation of the fortify phase of the game. It fortifies the armies from a country to another
     * country owned by the player and is following the adjacent route of the countries.
     *
     * @param movingTo country being fortified to
     */
    public void fortifyPhase(Country movingTo) {
        if (currentPlayer.canMove(moveFromCountry, movingTo, listOfConnectedCountries(moveFromCountry))) {
            currentPlayer.fortifyPhase(moveFromCountry, movingTo, armiesFortify);
            for (RiskView rv : riskViews) {
                rv.handleFortifyPhase(this, moveFromCountry, movingTo);
            }
            endTurn();

        } else {
            for (RiskView rv : riskViews) {
                rv.handleCanNotFortify(this);
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
     * Initializes the state of the game at the start of the game
     */
    public void theInitialState() {
        initialize(numPlayers);
        gameState = GameState.DRAFT_PHASE;
        currentPlayer.draftPhase();
        for (RiskView rv : riskViews) {
            rv.handleInitialization(this, gameState, currentPlayer, numPlayers, currentPlayer.getBonusArmies());
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
        //starts the draft phase of the next player
        endTurnDraft();
    }

    /**
     * Initates the draft phase and providies the number of armies to draft
     */
    public void endTurnDraft() {
        gameState = GameState.DRAFT_PHASE;
        Boolean turnComplete = currentPlayer.draftPhase();
        int draftArmies = currentPlayer.getBonusArmies();
            for (RiskView rv : riskViews) {
                rv.handleEndTurn(this, currentPlayer, draftArmies, turnComplete);

            }
            if(turnComplete){
                endTurn();
            }

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
        if (currentPlayer.ifPlayerOwns(attackCountry)) {
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
     * checks if the fortifying country the player chose is valid and also checks if the
     * number of armies being fortified with is also valid
     *
     * @param moveFrom    country being fortified from
     * @param armiesMoved number of armies to be fortified with
     */
    public void checkFortifyCountry(Country moveFrom, int armiesMoved) {
        if (currentPlayer.ifPlayerOwns(moveFrom)) {
            if (armiesMoved < moveFrom.getNumberOfArmies() && armiesMoved > 0 && moveFrom.getNumberOfArmies() > 1) {
                moveFromCountry = moveFrom;
                armiesFortify = armiesMoved;
                for (RiskView rv : riskViews) {
                    rv.handleCanFortifyFrom(this, moveFrom, listOfConnectedCountries(moveFrom));
                }
            } else {
                //handle invalid number of armies
                for (RiskView rv : riskViews) {
                    rv.handleCanNotFortifyArmies(this);
                }
            }
        } else {
            for (RiskView rv : riskViews) {
                rv.handleCanNotFortify(this);
            }
        }
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
     * getter for the attacker country
     */
    public Country getAttackingCountry() {
        return attackCountry;
    }

    public void draftNewArmy(Country country) {
        if (country.getCurrentOwner().equals(currentPlayer)) {
            country.addArmy(1);
            currentPlayer.placeBonusArmy();
            if (currentPlayer.getBonusArmies() == 0) {
                gameState = GameState.ATTACK_PHASE;
            }
            for (RiskView rv : riskViews) {
                rv.handleAddedArmy(this, country, currentPlayer.getBonusArmies());
            }
            if (currentPlayer.getBonusArmies() == 0) {
                gameState = GameState.IN_PROGRESS;
            }
        } else {
            for (RiskView rv : riskViews) {
                rv.handleCanNotDraftFrom(this);
            }
        }
    }

    /**
     * set the phase of the game state
     */
    public void setPhase(GameState state) {
        gameState = state;
    }

    /**
     * retrieves the valid connected countries that the player can fortify to from the
     * country chosen
     *
     * @param countryFrom          country to fortify from
     * @param connectedCountryList list to be populated with the connected countries
     */
    public void connectedCountries(Country countryFrom, ArrayList<Country> connectedCountryList) {
        for (Country c : countryFrom.getAdjacentCountries()) {
            if (connectedCountryList.contains(c) == false && c.getCurrentOwner() == currentPlayer) {
                connectedCountryList.add(c);
                connectedCountries(c, connectedCountryList);
            }
        }
    }

    /**
     * returns the list of connected countries to the country being fortified from
     *
     * @param countryFrom country to fortify from
     * @return list of connected countries to the country fortify from
     */
    public ArrayList<Country> listOfConnectedCountries(Country countryFrom) {
        ArrayList<Country> listConnectedCountries = new ArrayList<>();
        connectedCountries(countryFrom, listConnectedCountries);
        return listConnectedCountries;
    }


    /**
     * set the continents owned by the player
     */
    public void setContinentsOwned() {
        for (int i = 0; i < board.getContinents().size(); i++) {
            //check continent ownership and add it to the player's owned continents list
            if (currentPlayer.getCountriesOwned().containsAll(board.getContinents().get(i).getContinentCountries())) {
                currentPlayer.addContinent(board.getContinents().get(i));
            }
        }
    }

}