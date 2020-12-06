package risk.model.phase;

import risk.model.board.Board;
import risk.model.board.Continent;
import risk.model.board.Country;
import risk.model.player.Player;
import risk.view.RiskView;

import java.util.List;

public class DraftPhase {
    public static final int MIN_BONUS_ARMIES = 3;
    public static final int DEFAULT_NUMBER_OF_COUNTRIES = 12;

    private final Board board;
    private final Player player;
    private int armiesToPlace;
    private List<RiskView> riskViews;

    public DraftPhase(final Board board, final Player player) {
        this.board = board;
        this.player = player;
        this.armiesToPlace = getTotalBonusArmies();
    }

    public boolean haveArmiesToPlace() {
        return armiesToPlace > 0;
    }

    public boolean placeArmy(final Country selectedCountry) {
        if (board.getCountriesOwnedBy(player).contains(selectedCountry)) {
            selectedCountry.addArmies(1);
            armiesToPlace--;
            return true;
        } else {
            System.out.format("Cannot place army on %s and the player doesn't own it.\n", selectedCountry);

            return false;
        }
    }

    public int getArmiesToPlace() {
        return armiesToPlace;
    }

    /**
     * gets the sum of total bonus armies from occupied countries and continents at the start of each turn for every player.
     * @return the total number of bonus armies for the current player
     */
    private int getTotalBonusArmies() {
        return getBonusArmiesForOccupiedContinents() + getBonusArmiesForOccupiedCountries();
    }

    /**
     * Get the number of bonus armies that a player receives at the beginning of each turn.
     * A player receives 3 bonus armies if they have less than 12 occupied countries
     * Otherwise it counts the number of countries that the player occupies and then divides it
     * by 3 and it ignoring any fraction.
     */
    private int getBonusArmiesForOccupiedCountries(){
        int numOfOccupiedCountries = board.getCountriesOwnedBy(player).size();
        if (numOfOccupiedCountries < DEFAULT_NUMBER_OF_COUNTRIES ){
            return MIN_BONUS_ARMIES;
        }
        return Math.floorDiv(numOfOccupiedCountries , MIN_BONUS_ARMIES);
    }

    /**
     * setup the number of bonus armies at the start of each turn that a player receives if they occupy all countries in a continent
     * each continent have specific number of bonus armies.
     */
    private int getBonusArmiesForOccupiedContinents() {
        int sum = 0;
        for (Continent continent : board.getContinentsOwnedBy(player)) {
            int bonusArmies = continent.getBonusArmies();
            sum += bonusArmies;
        }
        return sum;
    }

    /**
     * set the risk views of the model
     */
    public void setRiskViews(List<RiskView> riskViews){
        this.riskViews=riskViews;

    }
}