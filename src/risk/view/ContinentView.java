package risk.view;

import risk.model.board.Continent;
import risk.model.board.Country;
import risk.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

/**
 * View of the continents and their countries on the board.
 * @version 1.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class ContinentView extends JPanel {

    private Map<Country, JButton> countryButtons;
    private Continent continent;
    private Color color;
    private JLabel continentLabel;
    private BoardView boardView;
    private ArrayList<JButton> selectedButtons;
    private RiskView riskView;
    private Game game;

    /**
     * creates the view of a continent
     * @param bv view of the board of the game
     * @param continent created in the continent view
     * @param color of the displayed continent
     */
    public ContinentView(RiskView riskView,BoardView bv, Continent continent, Color color,Game game) {
        this.selectedButtons = new ArrayList<>();
        this.riskView=riskView;
        countryButtons = new HashMap<>();
        this.continent = continent;
        this.game=game;
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
        List<Country> continentCountries = continent.getCountries();
        for (Country c : continentCountries) {
            JButton b = new JButton(c.getCountryName());
            b.setText(c.getCountryName());
            b.setName(c.getCountryName());
            b.setFont(new Font("Arial", Font.BOLD, 12));
            b.setBounds(100, 100, 100, 100);
            this.add(b);
            countryButtons.put(c, b);
        }
    }

    public void clearCountryListeners() {
        countryButtons.values().forEach(b -> {
             ActionListener[] listeners = b.getActionListeners();
            for (ActionListener listener : listeners) {
                b.removeActionListener(listener);
            }
        });
    }


    public void setupCountryListener(Function<Country, ActionListener> actionListenerFromCountry) {
        countryButtons.forEach((country, button) -> {
            ActionListener listener = actionListenerFromCountry.apply(country);
            button.addActionListener(listener);
        });
    }


    /**
     * Initializes the ownership of the countries and the number of countries in the
     * initialization phase
     */
    public void initializePlayerCountries() {
        countryButtons.forEach((country, button) -> {
            button.setForeground(boardView.getColors()[country.getCurrentOwner().getId()]);
            button.setText(country.getCountryName() + " " + country.getNumberOfArmies());
        });
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
     * checks if the continent has a country button
     * @param country to be checked in the continent
     * @return button country if the continent has the country and
     * null otherwise
     */
    public JButton getCountryButton(Country country) {
        return countryButtons.get(country);
    }

    /**
     * removes the highlight from the country button
     * @param countryButton to remove the highlight from
     */
    public void removeHighlightButton(JButton countryButton) {
        countryButton.setBorder(new JButton().getBorder());
    }

    /**
     * updates the country button when an army is added to a country
     * in the draft phase
     * @param country the army was added to
     */
    public void addArmy(Country country){
        final JButton button = countryButtons.get(country);
        button.setText(country.getCountryName() + " " + country.getNumberOfArmies());
    }
}