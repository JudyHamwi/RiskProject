package RiskView;

import RiskController.*;
import RiskModel.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The view of the Risk Game Board. It displays and keeps track of the view of the conntinents
 * and countries on the board.
 * @version 1.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class BoardView extends JPanel {

    public static final int CONTINENT_WIDTH=6;
    private static int colorCounter =0;
    private Color[] colorArray;
    private HashMap<Color, String> colors;
    private JPanel boardInformation;
    private Board board;
    private ArrayList<ContinentView> continentViews;
    private JPanel playerColorsPanel;
    private JButton endTurnButton;
    private JButton attackButton;
    private JButton attackPhaseButton;
    private JPanel inGamePanel;
    private Game game;
    private RiskView rv;
    private JButton fortifyPhaseButton;
    private JButton fortifyButton;
    private JLabel draftArmies;


    /**
     * creates the board view of the risk game
     * @param rv view of the main frame of the risk game
     * @param game model used for the logic of the game
     * @param board contains all the continents and countries of the game
     */
    public BoardView(RiskView rv,Game game, Board board){
        this.board=board;
        this.rv=rv;
        this.game=game;
        continentViews=new ArrayList<>();
        playerColorsPanel=new JPanel();
        playerColorsPanel.setLayout(new BoxLayout(playerColorsPanel, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(3,3,3,3));
        colors=new HashMap<>();
        colorArray=new Color[]{Color.magenta, Color.green, Color.blue, Color.orange, Color.pink, Color.red};
        createColors();
        boardInformation=new JPanel();
        initializeContinents();
        this.add(boardInformation);
        this.add(playerColorsPanel);
    }

    /**
     * Initializes the continents and their countries displayed on the board
     */
    public void initializeContinents(){
        for(Continent c:board.getContinents()){
            ContinentView continentview=new ContinentView(rv, game,this, c, colorArray[colorCounter]);
            this.add(continentview);
            continentViews.add(continentview);
            colorCounter++;
        }
    }

    /**
     * creates the colors used in the display of the board.
     */
    private void createColors(){
        colors.put(Color.magenta, "Purple");
        colors.put(Color.red, "Red");
        colors.put(Color.blue,"Blue");
        colors.put(Color.orange, "Orange");
        colors.put( Color.green, "Green");
        colors.put(Color.pink, "Pink");
        colors.put( Color.white, "White");
    }

    /**
     * Initializes the view of the board in the initialization phase
     * @param numPlayers number of players playing the game
     */
    public void InitializeBoard(int numPlayers) {
        for (ContinentView cv : continentViews) {
            cv.initializePlayerCountries();
        }
        initializePlayerInformationPanel(numPlayers);
    }

    /**
     * displays the player information containing the color for each player
     * @param numPlayers number of players playing the game
     */
    public void initializePlayerInformationPanel(int numPlayers){
        for(int i=0; i<numPlayers; i++){
            playerColorsPanel.add(new JLabel("Player"+(i+1)+" : "+ colors.get(colorArray[i])));
        }
    }

    /**
     * getter for the colors used in the display of the board
     * @return
     */
    public Color[] getColors(){ return colorArray;}

    /**
     * creates the buttons used to play the game, including the attack button,fortify button
     * and move button
     * @param game model that manipulates the logic of the fame
     * @param player with the current turn
     * @return panel to be displayed in the view
     */
    public JPanel inGamePanel(Game game, Player player) {
        inGamePanel = new JPanel();

        inGamePanel.setLayout(new GridLayout(3,3));

        attackButton = new JButton("Attack!");
        attackButton.setName("attackButton");
        attackButton.setEnabled(false);
        attackPhaseButton=new JButton("Attack Phase");
        attackPhaseButton.setEnabled(false);
        endTurnButton = new JButton("End turn");
        fortifyPhaseButton=new JButton("Fortify Phase");
        fortifyPhaseButton.setEnabled(false);;
        fortifyButton=new JButton("fortify");
        fortifyButton.setName("fortifyButton");
        fortifyButton.setEnabled(false);
        draftArmies=new JLabel("Draft Armies: ");
        draftArmies.setVisible(false);

        inGamePanel.add(attackPhaseButton);
        inGamePanel.add(attackButton);
        inGamePanel.add(fortifyButton);
        inGamePanel.add(fortifyPhaseButton);
        inGamePanel.add(endTurnButton);
        inGamePanel.add(draftArmies);

        endTurnButton.addActionListener(new EndTurnController(game));
        attackPhaseButton.addActionListener(new AttackPhaseController(game, this));
        attackButton.addActionListener(new AttackController(rv, game, null));
        fortifyButton.addActionListener(new FortifyController(rv,game,null));
        fortifyPhaseButton.addActionListener(new FortifyPhaseController(game, this));

        return inGamePanel;
    }

    /**
     * adds the game panel to the view
     * @param game model that manipulates the logic of the game
     * @param player with the current turn
     */
    public void addInGamePanel(Game game, Player player) {
        this.add(inGamePanel(game, player));
    }

    /**
     * highlights the country that the player chooses to attack from
     * @param country the player wants to attack from
     */
    public void highlightSelectedCountry(Country country){
        for(ContinentView cv:continentViews){
            if(cv.hasCountryButton(country) != null){
                cv.highlightButton(cv.hasCountryButton(country));
            }
        }
    }

    /**
     * highlight the countries adjacent to the country the player wants to
     * attack from
     * @param country the player wants to attack from
     */
    public void highlightAdjacentCountries(Country country) {
        highlightSelectedCountry(country);
        for (Country adjacentCountry : country.getAdjacentCountries()) {
            for (ContinentView cv : continentViews) {
                if (cv.hasCountryButton(adjacentCountry) != null){
                    cv.highlightButton(cv.hasCountryButton(adjacentCountry));
                }
            }
        }
    }

    /**
     * highlight the countries that the player can fortify to
     * @param connectedCountries that is selected to fortify armies from
     */
    public void highlightFortifyingCountries(ArrayList<Country> connectedCountries){
        for(Country country : connectedCountries) {
            for (ContinentView cv : continentViews) {
                if (cv.hasCountryButton(country) != null) {
                    cv.highlightButton(cv.hasCountryButton(country));
                }
            }
        }
    }



    /**
     * remove the highlight from the attacker country after the attack is complete
     * ot when the country is no longer selected to be the attacker country
     * @param country to remove the highlight country
     */
    public void removeHighlightCountry(Country country){
        for(ContinentView cv:continentViews){
            if(cv.hasCountryButton(country) != null){
                cv.removeHighlightButton(cv.hasCountryButton(country));
                removeHighLightAdjacentCountry(country);
            }
        }
    }

    /**
     * remove the highlight from the countries adjacent to the country that will not be used to attack
     * @param country that the adjacent countries highlight will be removed
     */
    public void removeHighLightAdjacentCountry(Country country){
        for(Country adjacentCountry: country.getAdjacentCountries()){
            for (ContinentView cv : continentViews){
                if (cv.hasCountryButton(adjacentCountry) != null){
                    cv.removeHighlightButton(cv.hasCountryButton(adjacentCountry));
                }
            }
        }
    }

    /**
     * remove the highlight from all the highlighted buttons after end turn
     */
    public void removeHighlightedButtons() {
        for(ContinentView cv:continentViews){
            cv.removeSelectedButtons();
        }
    }

    /**
     * update the ownership of the countries and number of armies on each country after the
     * attack is complete
     * @param attackerCountry the country that initiated the attack
     * @param defenderCountry the country that was attacked
     */
    public void TransferOwnership(Country attackerCountry, Country defenderCountry){
        JButton attacker=new JButton();
        JButton defender=new JButton();
        for (ContinentView cv:continentViews){
            if(cv.hasCountryButton(attackerCountry) != null){
                attacker=cv.hasCountryButton(attackerCountry);
                attacker.setForeground(getColors()[attackerCountry.getCurrentOwner().getPlayerID()-1]);
                attacker.setText(attackerCountry.getCountryName()+" "+ attackerCountry.getNumberOfArmies());
            }if(cv.hasCountryButton(defenderCountry) != null){
                defender=cv.hasCountryButton(defenderCountry);
                defender.setForeground(getColors()[defenderCountry.getCurrentOwner().getPlayerID()-1]);
                defender.setText(defenderCountry.getCountryName()+" "+ defenderCountry.getNumberOfArmies());
            }
        }


    }

    /**
     * get the attack button used during the attack phase
     * @return attack button
     */
    public JButton getAttackButton(){
        return attackButton;
    }

    /**
     * getter for the fortify button
     * @return
     */
    public JButton getFortifyButton(){
        return fortifyButton;
    }

    /**
     * geter for the attack phase button
     * @return
     */
    public JButton getAttackPhaseButton(){
        return attackPhaseButton;
    }

    /**
     * getter for fortify phase button
     * @return
     */
    public JButton getFortifyPhaseButton(){
        return fortifyPhaseButton;
    }

    /**
     * Add new army to country in draft phase
     * @param country that the army was added to
     */
    public void addArmyToCountry(Country country){
        for (ContinentView cv : continentViews){
            if (cv.hasCountryButton(country) != null){
                cv.addArmy(country);
            }
        }
        draftArmies.setText("Draft Armies: " + country.getNumberOfArmies());
    }

    /**
     * getter for the label for draft armies in draft phase
     */
    public JLabel getDraftArmies(){
        return draftArmies;
    }

    /**
     * updates the state of the board view after an AI turn
     */
    public void updateBoard(){
        for(ContinentView continentView:continentViews){
            continentView.initializePlayerCountries();
        }
    }
}
