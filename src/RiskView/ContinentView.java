package RiskView;

import RiskController.AttackController;
import RiskController.MoveController;
import RiskModel.Continent;
import RiskModel.Country;
import RiskModel.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * View of the continents and their countries on the board.
 * @version 1.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class ContinentView extends JPanel {

    private ArrayList<JButton> countryButtons;
    private Continent continent;
    private Color color;
    private JLabel continentLabel;
    private BoardView boardView;
    private Game game;
    private RiskView rv;
    private ArrayList<JButton> selectedButtons;

    /**
     * creates the view of a continent
     * @param rv view of the main frame of the game
     * @param game model that deals with the logic of the game
     * @param bv view of the board of the game
     * @param continent created in the continent view
     * @param color of the displayed continent
     */
    public ContinentView(RiskView rv, Game game, BoardView bv, Continent continent, Color color) {
        this.selectedButtons = new ArrayList<>();
        this.rv = rv;
        countryButtons = new ArrayList<>();
        this.continent = continent;
        this.game = game;
        boardView = bv;
        continentLabel = new JLabel(continent.getContinentName());
        this.color = color;
        this.setLayout(new GridLayout(3, 3));
        this.add(continentLabel);
        createContinentView();
    }

    /**
     * creates the display of the view of the continent with its countries
     */
    private void createContinentView() {
        this.setBackground(color);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new GridLayout(5, 5, 5, 5));
        LinkedList<Country> continentCountries = continent.getContinentCountries();
        for (Country c : continentCountries) {
            JButton b = new JButton(c.getCountryName());
            b.setText(c.getCountryName());
            b.setName(c.getCountryName());
            b.setFont(new Font("Arial", Font.BOLD, 12));
            b.setBounds(100, 100, 100, 100);
            this.add(b);
            countryButtons.add(b);
            b.addActionListener(new AttackController(rv, game, c));
            b.addActionListener(new MoveController(rv,game,c));
        }
    }

    /**
     * Initializes the ownership of the countries and the number of countries in the
     * initialization phase
     */
    public void initializePlayerCountries() {
            for (JButton b : countryButtons) {
                Country country = continent.getCountryFromContinent(b.getName());
                    b.setForeground(boardView.getColors()[country.getCurrentOwner().getPlayerID() - 1]);
                    b.setText(country.getCountryName() + " " + country.getNumberOfArmies());

            }

    }

    /**
     * highlight the selected country button
     * @param countryButton to be highlighted
     */
    public void highlightButton(JButton countryButton) {
        countryButton.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
        selectedButtons.add(countryButton);
    }

    /**
     * remove the highlight from all the highlighted buttons
     */
    public void removeSelectedButtons() {
        for (JButton jb : selectedButtons) {
            removeHighlightButton(jb);
        }
    }

    /**
     * returns the list of the selected buttons
     * @return list of selected buttons
     */
    public ArrayList<JButton> getSelectedButtons() {
        return selectedButtons;
    }

    /**
     * checks if the continent has a country button
     * @param country to be checked in the continent
     * @return button country if the continent has the country and
     * null otherwise
     */
    public JButton hasCountryButton(Country country) {
        for (JButton b : countryButtons) {
            if (b.getName().equals(country.getCountryName())) {
                return b;
            }
        }
        return null;
    }

    /**
     * removes the highlight from the country button
     * @param countryButton to remove the highlight from
     */
    public void removeHighlightButton(JButton countryButton) {
        countryButton.setBorder(new JButton().getBorder());
    }

    /**
     * checks if the continent has the defender country
     * @param country to check if it is in the continent
     * @return button of the country if the continent has the country
     * and false otherwise
     */
    public JButton defenderCountryButton(Country country) {
        for (JButton b : countryButtons) {
            if (b.getName().equals(country.getCountryName())) {
                return b;
            }
        }
        return null;
    }

}
