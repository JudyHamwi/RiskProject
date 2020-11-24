package RiskModel;

import RiskView.RiskView;
import RiskView.RiskViewFrame;

import javax.naming.ldap.Control;
import java.util.Collections;
import java.util.Random;
import java.util.*;
/**
 * Constant risk board for testing
 * Gives each player in the list players one continent, the same players the same continents
 * Also gives every country 1 troop, and then places the rest of the troop on a SINGLE border country
 * Border Country a country connected to another continent
 *
 * Player1: NorthAmerica, Border country: Alaska, 12 Troops, Bonus Armies= 8
 * Player2: Europe, Border Country: Iceland, 14 Troops, Bonus Armies = 8
 * Player3: Asia, Border Country: Afghanistan, 9 Troops, Bonus Armies = 11
 * Player4: SouthAmerica, Border Country: Brazil, 17 Troops, Bonus Armies = 5
 * Player5: Africa, Border Country: EastAfrica, 15 Troops, Bonus Armies = 6
 * Player6: Australia, Border Country: Indonesia: 17 Troops, Bonus Armies = 5
 *
 * Will make: Player1 and Player2 as AIPlayers
 *
 * USED FOR TESTING - WILL ALWAYS BE THE SAME
 * Not used anywhere, just for testing purposes
 */

public class ControlGame extends Game {

    int AIPlayers;

    public ControlGame(){
        super();
        initializeControlGame(6,3);

    }

    private void initializeControlGame(int numberOfPlayers, int numberOfAIPlayers){
        addPlayers(numberOfPlayers);
        setAIPlayers(numberOfAIPlayers);
        initialArmyForPlayer();
        controlDistributeCountries();
        distributeArmyToBorderCountry();
        currentPlayer=players.getFirst();

    }

    /**
     * Gives each player a whole continent
     * 6 Players, 6 continents
     */
    private void controlDistributeCountries(){
        int contentIndex = 0;
        for (Player p: players){
            for (Country c: board.getTestContinents().get(contentIndex).getContinentCountries()){
                p.addCountry(c);
            }
            contentIndex ++;
        }
    }


    private boolean isBorderCountry(Country country){
        boolean isBorderCountry = false;
        for (Country c: country.getAdjacentCountries()){
            if(c.getCurrentOwner() != country.getCurrentOwner()){
                isBorderCountry = true;
            }
        }
        return isBorderCountry;
    }

    public void initialArmyForPlayer() {
        for (Player p : players) {
            p.addPlayerArmy(20);
        }
    }

    private void distributeArmyToBorderCountry(){
        distributeOneArmyToCountry();
        for (Player p: players){
            for(Country c: p.getCountriesOwned()){
                if (isBorderCountry(c)){
                    c.addArmy(p.getPlayerArmy());
                    p.addPlayerArmy(-(p.getPlayerArmy()));
                }
            }
        }
    }

    private void printState() {
        System.out.println("HERE IS THE STATE OF THE MAP: ");
        for (Player p : players) {
            System.out.println(p);
            System.out.println("owns: " + p.getCountriesOwned());
            for (Country c : p.getCountriesOwned()) {
                System.out.println(" " + c + " Number of Armies: " + c.getNumberOfArmies());
            }
        }
        System.out.println("THERE ARE" + AIPlayers + "PLAYERS");
        printAIPlayers();
    }

    private void printAIPlayers(){
        for (Player p : players) {
            if (p.getIsAI()) {
                System.out.println("This Player is an AI: " + p);
            }
        }
    }

    public static void main(String[] args) {
        ControlGame game = new ControlGame();
        game.printState();
        game.draftPhase();
        System.out.println("Draft troops: " + game.currentPlayer.getPlayerArmy());
    }
}
