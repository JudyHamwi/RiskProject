package risk.view;

import risk.controller.*;
import risk.model.*;
import risk.model.board.Board;
import risk.model.board.Continent;
import risk.model.board.Country;
import risk.model.phase.FortifyPhase;
import risk.model.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.function.Function;

/**
 * The view of the Risk Game Board. It displays and keeps track of the view of the conntinents
 * and countries on the board.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class BoardView extends JPanel {

    private static int colorCounter = 0;
    private Color[] colorArray;
    private HashMap<Color, String> colors;
    private JPanel boardInformation;
    private Board board;
    private List<ContinentView> continentViews;
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
     *
     * @param rv    view of the main frame of the risk game
     * @param game  model used for the logic of the game
     * @param board contains all the continents and countries of the game
     */
    public BoardView(RiskView rv, Game game, Board board) {
        this.board = board;
        this.rv = rv;
        this.game = game;
        continentViews = new ArrayList<>();
        playerColorsPanel = new JPanel();
        playerColorsPanel.setLayout(new BoxLayout(playerColorsPanel, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(3, 3, 3, 3));
        colors = new HashMap<>();
        colorArray = new Color[]{Color.magenta, Color.green, Color.blue, Color.orange, Color.pink, Color.red};
        createColors();
        boardInformation = new JPanel();
        initializeContinents();
        this.add(boardInformation);
        this.add(playerColorsPanel);
    }

    /**
     * Initializes the continents and their countries displayed on the board
     */
    public void initializeContinents() {
        for (Continent c : board.getContinents()) {
            ContinentView continentview = new ContinentView(rv,this, c, colorArray[colorCounter],game);
            this.add(continentview);
            continentViews.add(continentview);
            colorCounter++;
        }
    }

    /**
     * creates the colors used in the display of the board.
     */
    private void createColors() {
        colors.put(Color.magenta, "Purple");
        colors.put(Color.red, "Red");
        colors.put(Color.blue, "Blue");
        colors.put(Color.orange, "Orange");
        colors.put(Color.green, "Green");
        colors.put(Color.pink, "Pink");
        colors.put(Color.white, "White");
    }

    /**
     * Initializes the view of the board in the initialization phase
     *
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
     *
     * @param numPlayers number of players playing the game
     */
    public void initializePlayerInformationPanel(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            playerColorsPanel.add(new JLabel("Player" + i + " : " + colors.get(colorArray[i])));
        }
    }

    /**
     * getter for the colors used in the display of the board
     *
     * @return
     */
    public Color[] getColors() {
        return colorArray;
    }

    /**
     * creates the buttons used to play the game, including the attack button,fortify button
     * and move button
     *
     * @param game   model that manipulates the logic of the fame
     * @param player with the current turn
     * @return panel to be displayed in the view
     */
    public JPanel inGamePanel(Game game, Player player) {
        inGamePanel = new JPanel();

        inGamePanel.setLayout(new GridLayout(3, 3));

        attackButton = new JButton("Attack!");
        attackButton.setName("attackButton");
        attackButton.setEnabled(false);
        attackPhaseButton = new JButton("Attack Phase");
        attackPhaseButton.setEnabled(false);
        endTurnButton = new JButton("End turn");
        fortifyPhaseButton = new JButton("Fortify Phase");
        fortifyPhaseButton.setEnabled(false);
        
        fortifyButton = new JButton("fortify");
        fortifyButton.setName("fortifyButton");
        fortifyButton.setEnabled(false);
        draftArmies = new JLabel("Draft Armies: ");
        draftArmies.setVisible(false);

        inGamePanel.add(attackPhaseButton);
        inGamePanel.add(attackButton);
        inGamePanel.add(fortifyButton);
        inGamePanel.add(fortifyPhaseButton);
        inGamePanel.add(endTurnButton);
        inGamePanel.add(draftArmies);

        endTurnButton.addActionListener(new EndTurnController(game));
        attackPhaseButton.addActionListener(new AttackStartController(game));
        fortifyPhaseButton.addActionListener(new FortifyStartController(game));

        return inGamePanel;
    }

    public void setUpAttackListeners(){
        removeActionListeners(attackButton);
        attackButton.addActionListener(new AttackCommitFromController(game));
    }

    public void setUpFortifyListeners(FortifyPhase fortifyPhase){
        removeActionListeners(fortifyButton);
        fortifyButton.addActionListener(new FortifyCommitFromController(game, rv, fortifyPhase));
    }

    /**
     * adds the game panel to the view
     *
     * @param game   model that manipulates the logic of the game
     * @param player with the current turn
     */
    public void addInGamePanel(Game game, Player player) {
        this.add(inGamePanel(game, player));
    }

    public void setupCountryListeners(Function<Country, ActionListener> actionListenerFromCountry) {
        continentViews.forEach(cv -> {
            cv.clearCountryListeners();
            cv.setupCountryListener(actionListenerFromCountry);
        });
    }

    public void clearCountryListeners(){
        continentViews.forEach(cv -> cv.clearCountryListeners());
    }

    /**
     * highlights the country that the player chooses to attack from
     *
     * @param country the player wants to attack from
     */
    public void highlightSelectedCountry(Country country) {
        for (ContinentView cv : continentViews) {
            if (cv.getCountryButton(country) != null) {
                cv.highlightButton(cv.getCountryButton(country));
            }
        }
    }

    /**
     * highlight the countries adjacent to the country the player wants to
     * attack from
     *
     * @param country the player wants to attack from
     */
    public void highlightAdjacentCountries(Country country) {
        highlightSelectedCountry(country);
        for (Country adjacentCountry : country.getAdjacentCountries()) {
            for (ContinentView cv : continentViews) {
                if (cv.getCountryButton(adjacentCountry) != null) {
                    cv.highlightButton(cv.getCountryButton(adjacentCountry));
                }
            }
        }
    }

    /**
     * highlight the countries that the player can fortify to
     *
     * @param connectedCountries that is selected to fortify armies from
     */
    public void highlightFortifyingCountries(List<Country> connectedCountries) {
        for (Country country : connectedCountries) {
            for (ContinentView cv : continentViews) {
                if (cv.getCountryButton(country) != null) {
                    cv.highlightButton(cv.getCountryButton(country));
                }
            }
        }
    }

    private void removeActionListeners(JButton button) {
        ActionListener[] actionListenersArray = button.getActionListeners();
        for (ActionListener actionListener : actionListenersArray) {
            button.removeActionListener(actionListener);
        }
    }


    /**
     * remove the highlight from the attacker country after the attack is complete
     * ot when the country is no longer selected to be the attacker country
     *
     * @param country to remove the highlight country
     */
    public void removeHighlightCountry(Country country) {
        for (ContinentView cv : continentViews) {
            if (cv.getCountryButton(country) != null) {
                cv.removeHighlightButton(cv.getCountryButton(country));
                removeHighLightAdjacentCountry(country);
            }
        }
    }

    /**
     * remove the highlight from the countries adjacent to the country that will not be used to attack
     *
     * @param country that the adjacent countries highlight will be removed
     */
    private void removeHighLightAdjacentCountry(Country country) {
        for (Country adjacentCountry : country.getAdjacentCountries()) {
            for (ContinentView cv : continentViews) {
                if (cv.getCountryButton(adjacentCountry) != null) {
                    cv.removeHighlightButton(cv.getCountryButton(adjacentCountry));
                }
            }
        }
    }

    /**
     * remove the highlight from all the highlighted buttons after end turn
     */
    public void removeHighlightedButtons() {
        for (ContinentView cv : continentViews) {
            cv.removeSelectedButtons();
        }
    }

    /**
     * update the ownership of the countries and number of armies on each country after the
     * attack is complete
     */
    public void updateCountryButtons() {
        continentViews.forEach(ContinentView::initializePlayerCountries);
    }

    /**
     * get the attack button used during the attack phase
     *
     * @return attack button
     */
    public JButton getAttackButton() {
        return attackButton;
    }

    /**
     * getter for the fortify button
     *
     * @return
     */
    public JButton getFortifyButton() {
        return fortifyButton;
    }

    /**
     * geter for the attack phase button
     *
     * @return
     */
    public JButton getAttackPhaseButton() {
        return attackPhaseButton;
    }

    /**
     * getter for fortify phase button
     *
     * @return
     */
    public JButton getFortifyPhaseButton() {
        return fortifyPhaseButton;
    }

    /**
     * Add new army to country in draft phase
     *
     * @param country that the army was added to
     */
    public void addArmyToCountry(Country country) {
        for (ContinentView cv : continentViews) {
            if (cv.getCountryButton(country) != null) {
                cv.addArmy(country);
            }
        }
        draftArmies.setText("Draft Armies: " + country.getNumberOfArmies());
    }

    /**
     * getter for the label for draft armies in draft phase
     */
    public JLabel getDraftArmies() {
        return draftArmies;
    }

    /**
     * updates the state of the board view after an AI turn
     */
    public void updateBoardForAI() {
        for (ContinentView continentView : continentViews) {
            continentView.initializePlayerCountries();
        }
    }

    public JButton getEndTurnButton() {
        return this.endTurnButton;
    }
}