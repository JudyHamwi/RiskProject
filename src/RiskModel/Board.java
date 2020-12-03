package RiskModel;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The RISKModel.Board of the RISK RISKModel.Game.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class Board {
    public static final String BOARD_TAG = "board";

    private final LinkedList<Continent> continents;
    private ArrayList<Country> countries;
    private ArrayList<Continent> testContinents;

    /**
     * Constructor of RISKModel.Board that creates a new RISKModel.Board
     */
    public Board() {
        continents = new LinkedList<>();
        testContinents = new ArrayList<>();
        countries = new ArrayList<>();
    }

    /**
     * add a new continent to the RISKModel.Board
     *
     * @param continent added to the board
     */
    public void addContinent(Continent continent) {
        continents.add(continent);
    }

    public void addCountryToBoard(Country country) {
        countries.add(country);
    }

    public Country getCountry(String name) {
        for (Country c : countries) {
            if (c.getCountryName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public Continent getContinent(String continent) {
        for (Continent c : continents) {
            if (c.getContinentName().equals(continent)) {
                return c;
            }
        }
        return null;
    }

    public LinkedList<Continent> getContinents() {
        return continents;
    }


    /**
     * The textual representation of the RISKModel.Board. Contains information about every continent, country
     * and the player that owns the country
     *
     * @return the textual representation of the board.
     */
    public String toString() {
        String Board = "RISK BOARD: \n" + "Continents: \n";
        for (Continent c : continents) {
            Board = Board.concat(c.toString());
        }
        return Board;
    }

    /**
     * Retrieves all the countries in the RISKModel.Board
     *
     * @return List of countries in the RISKModel.Board
     */
    public ArrayList<Country> getCountries() {
        return countries;
    }


    private void fillTestContinents(){
        for (Continent c: this.continents){
            testContinents.add(c);
        }
    }

    public ArrayList<Continent> getTestContinents(){
        return testContinents;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<" + BOARD_TAG + ">");

        for(int i = 0; i < continents.size(); i++) {
            sb.append(continents.get(i).toXMLContinent());
        }

        sb.append("</" + BOARD_TAG + ">");

        return sb.toString();
    }

}