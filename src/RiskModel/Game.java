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
    public  LinkedList<Player> players;
    private int numPlayers;
    private int numAIPlayers;
    public Player currentPlayer;
    private ArrayList<RiskView> riskViews;
    private Country attackCountry;
    private HashMap<Integer, Integer> armiesForPlayers;
    private Country moveFromCountry;
    private int armiesFortify;
    private int draftArmies;
    boolean ifAI;

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
     * Initalizes the start of the RISKModel.Game
     *
     * @param numberOfPlayers that will play the game
     */
    public void initialize(int numberOfPlayers) {
        addPlayers(numberOfPlayers);
        setAIPlayers(numAIPlayers);
        initialArmyForPlayer();
        distributeCountries();
        distributeRandomArmyToCountry();
        currentPlayer = players.getFirst();
        if(currentPlayer.getIsAI()){
            AITurn();
        }
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

        for (RiskView rv : riskViews) {
            rv.handleSetNumOfAIPlayers(numPlayers);
        }
    }

    public void setNumberOfAIPlayers(int numberOfAIPlayers) {
        numAIPlayers = numberOfAIPlayers;

        if(numberOfAIPlayers != 0 ) {
            ifAI = true;
        } else {
            ifAI = false;
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
     * Initiates the attack phase of the game, which is entered when a player decided to attack
     *
     * @param defenderCountry the country that will be defending from the attack
     */
    public void attackPhase(Country defenderCountry) {
        if (currentPlayer.canAttack(attackCountry, defenderCountry)) {
            AttackPhase playerAttack = new AttackPhase(currentPlayer, attackCountry, defenderCountry);
            Boolean attackSuccess = playerAttack.attack();
            Player playerRemoved = removePlayer();
            boolean winner = checkWinner();
            setContinentsOwned();
            if(!currentPlayer.getIsAI()) {
                for (RiskView rv : riskViews) {
                    rv.handleAttackPhase(this, attackCountry, defenderCountry, attackSuccess, winner, playerRemoved);
                }
            }
        } else {
            if (!currentPlayer.getIsAI()) {
                for (RiskView rv : riskViews) {
                    rv.handleCanNotAttackFrom(this);
                }
            }
        }

    }

    public void fortifyPhase(Country movingTo) {
        if (currentPlayer.canMove(moveFromCountry, movingTo, listOfConnectedCountries(moveFromCountry))) {
            FortifyPhase playerFortify = new FortifyPhase(currentPlayer, moveFromCountry, movingTo);
            playerFortify.setNumOfArmiesToMove(armiesFortify);
            Boolean fortifySuccess = playerFortify.fortify();
            for (RiskView rv : riskViews) {
                rv.handleFortifyPhase(this, moveFromCountry, movingTo);
            }
            endTurn();

        }else {
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
        gameState=GameState.DRAFT_PHASE;
        draftPhase();
        for (RiskView rv : riskViews) {
            rv.handleInitialization(this, gameState, currentPlayer, numPlayers,draftArmies, ifAI);
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
        endTurnDraft();
    }

    /**
     * decides if it will be a regular players turn or an AI turn
     */
    public void endTurnDraft(){
        if(currentPlayer.getIsAI()){
         AITurn();
        } else {
            draftPhase();
            gameState=GameState.DRAFT_PHASE;
            for (RiskView rv : riskViews) {
                rv.handleEndTurn(this, currentPlayer, draftArmies);
            }
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

    public void checkFortifyCountry(Country moveFrom, int armiesMoved) {
        if(currentPlayer.ifPlayerOwns(moveFrom)) {
            if (armiesMoved < moveFrom.getNumberOfArmies() && armiesMoved > 0 && moveFrom.getNumberOfArmies()>1) {
                moveFromCountry = moveFrom;
                armiesFortify=armiesMoved;
                for (RiskView rv : riskViews) {
                    rv.handleCanFortifyFrom(this, moveFrom,listOfConnectedCountries(moveFrom) );
                }
            } else {
                //handle invalid number of armies
                for (RiskView rv : riskViews) {
                    rv.handleCanNotFortifyArmies(this);
                }
            }
        }else {
            for (RiskView rv : riskViews) {
                rv.handleCanNotFortify(this);
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

    public void draftNewArmy(Country country) {
        if (country.getCurrentOwner().equals(currentPlayer)) {
            country.addArmy(1);
            draftArmies--;
            if (draftArmies == 0) {
                gameState = GameState.ATTACK_PHASE;
            }
            for (RiskView rv : riskViews) {
                rv.handleAddedArmy(this, country, draftArmies);
            }
            if (draftArmies == 0) {
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
    public void setPhase(GameState state){
        gameState=state;
    }

    public void draftPhase(){
            DraftPhase playerDraft = new DraftPhase(currentPlayer);
            draftArmies= playerDraft.getTotalBonusArmies();
            currentPlayer.addPlayerArmy(draftArmies); //add the bonus army to the total number of armies the player has
    }
    public void connectedCountries(Country countryFrom, ArrayList<Country> connectedCountryList) {
        for (Country c : countryFrom.getAdjacentCountries()) {
            if (connectedCountryList.contains(c) == false && c.getCurrentOwner() == currentPlayer) {
                connectedCountryList.add(c);
                connectedCountries(c, connectedCountryList);
            }
        }
    }

    public ArrayList<Country> listOfConnectedCountries(Country countryFrom){
        ArrayList<Country> listConnectedCountries = new ArrayList<>();
        connectedCountries(countryFrom, listConnectedCountries);
        return listConnectedCountries;
    }


    public void setContinentsOwned(){
        for(int i = 0; i < board.getContinents().size(); i++){
            //check continent ownership and add it to the player's owned continents list
            if(currentPlayer.getCountriesOwned().containsAll(board.getContinents().get(i).getContinentCountries())){
                currentPlayer.addContinent(board.getContinents().get(i));
            }
        }
    }

    /**
     *
     * @param numAIPlayers
     */
    private void setAIPlayers(int numAIPlayers){
        if(!(numAIPlayers==0)) {
            for (int i = 0; i < numAIPlayers + 1; i++) {
                players.get(i).setAI();
            }
        }
    }

    public void AIDraft(){
        draftPhase();
        int lowestArmyCountryIndex = 0;
        Collections.shuffle(currentPlayer.getCountriesOwned()); //So it doesn't always choose the same country
        for (int i=1; i<currentPlayer.getTotalNumberOfCountries(); i++ ){
            if(currentPlayer.getCountriesOwned().get(i).getNumberOfArmies() < currentPlayer.getCountriesOwned().get(lowestArmyCountryIndex).getNumberOfArmies()){
                lowestArmyCountryIndex = i;
            }
        }
        currentPlayer.getCountriesOwned().get(lowestArmyCountryIndex).addArmy(currentPlayer.getPlayerArmy());
        currentPlayer.addPlayerArmy(-(currentPlayer.getPlayerArmy()));
    }

    /**
     * AI Attacking method
     * The AI attacks as much as possible with the following conditions
     * The country it attacks has less troops, or the attacking country has more than 3 troops
     */
    public int AIAttack() {
        int numberOfAttacks = 0;
        ArrayList<Country> copyOfCountriesOwned = new ArrayList<>();
        for (Country c: currentPlayer.getCountriesOwned()){
            copyOfCountriesOwned.add(c);
        }
        for (Country c: copyOfCountriesOwned){
            if(c.getNumberOfArmies()>1){
                for (Country ac : c.getAdjacentCountries()){
                    if (ac.getCurrentOwner() != currentPlayer){
                        if(c.getNumberOfArmies() > ac.getNumberOfArmies() || c.getNumberOfArmies() > 3){
                            AttackPhase aiAttack = new AttackPhase(currentPlayer, c, ac);
                            aiAttack.attack();
                            numberOfAttacks++;
                        }
                    }
                }
            }
        }
        return numberOfAttacks;

    }

    /**
     * Moves troops from one country to another
     * AI implementation splits the troops evenly between the two countries
     * If the number is odd, the extra troop is moved to countryTo
     * @param countryFrom is supplying the troops
     * @param countryTo is accepting the troops
     */
    public void moveTroopsAI(Country countryFrom, Country countryTo){
        int totalTroops = countryFrom.getNumberOfArmies() + countryTo.getNumberOfArmies();
        int extraTroop = totalTroops % 2; //Only equals 1 if odd number of troops
        int sharedTroop = (totalTroops - extraTroop)/2; //Always be an even number
        countryFrom.setArmy(sharedTroop);
        countryTo.setArmy(sharedTroop+extraTroop);
    }

    /**
     * The fortify method completed for an AI Player
     */
    public void AIFortify(){
        int highestArmyCountryIndex = 0;
        int lowestArmyConnectedCountryIndex = 0; //similar to the draft phase, searching for the country with lowest troops
        ArrayList<Country> fortCountries = new ArrayList<>();
        Collections.shuffle(currentPlayer.getCountriesOwned());
        for (int i = 1; i<currentPlayer.getTotalNumberOfCountries(); i++){
            if(currentPlayer.getCountriesOwned().get(i).getNumberOfArmies() > currentPlayer.getCountriesOwned().get(highestArmyCountryIndex).getNumberOfArmies()){
                highestArmyCountryIndex = i; //Index of country with the highest amount of Troops
            }
        }
        connectedCountries(currentPlayer.getCountriesOwned().get(highestArmyCountryIndex), fortCountries); //After this,the fortCountries List should contain all connected countries
        if(fortCountries.isEmpty()==false) { //Makes sure there are connected countries
            for (int i = 1; i < fortCountries.size(); i++) {
                if (fortCountries.get(i).getNumberOfArmies() < fortCountries.get(lowestArmyConnectedCountryIndex).getNumberOfArmies()) {
                    lowestArmyConnectedCountryIndex = i;
                }
            }
            moveTroopsAI(currentPlayer.getCountriesOwned().get(highestArmyCountryIndex), fortCountries.get(lowestArmyConnectedCountryIndex));
        }
    }

    /**
     * The complete turn of the AI player
     */
    public void AITurn(){
        AIDraft();
        int numberOfAttacks=AIAttack();
        AIFortify();
        for (RiskView rv : riskViews) {
            rv.handleAITurn(numberOfAttacks, currentPlayer);
        }
        endTurn();
    }


}
