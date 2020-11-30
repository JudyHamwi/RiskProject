package RiskModel;

import RiskView.RiskView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIPlayer extends Player{

    private int numberOfAttacks;

    public AIPlayer(){
        super();
        numberOfAttacks=0;
    }

    public boolean draftPhase(){
        this.numberOfAttacks = 0;
        int lowestArmyCountryIndex = 0;
        Collections.shuffle(this.getCountriesOwned()); //So it doesn't always choose the same country
        for (int i = 1; i < this.getTotalNumberOfCountries(); i++) {
            if (this.getCountriesOwned().get(i).getNumberOfArmies() < this.getCountriesOwned().get(lowestArmyCountryIndex).getNumberOfArmies()) {
                lowestArmyCountryIndex = i;
            }
        }
        this.getCountriesOwned().get(lowestArmyCountryIndex).addArmy(this.getBonusArmies());
        this.placeBonusArmy();
        AttackPhase();
        return true;
    }

    public void AttackPhase(){
        for (int i = 0; i < this.getCountriesOwned().size(); i++) {
            if (this.getCountriesOwned().get(i).getNumberOfArmies() > 1) {
                for (Country ac : this.getCountriesOwned().get(i).getAdjacentCountries()) {
                    if (ac.getCurrentOwner() != this) {
                        if (this.getCountriesOwned().get(i).getNumberOfArmies() > ac.getNumberOfArmies() || this.getCountriesOwned().get(i).getNumberOfArmies() > 3) {
                            AttackPhase aiAttack = new AttackPhase(this, this.getCountriesOwned().get(i), ac);
                            aiAttack.attack();
                            numberOfAttacks++;
                        }
                    }
                }
            }
        }
        System.out.println(this+": " +numberOfAttacks );
        FortifyPhase();
    }

    public void FortifyPhase(){
        int highestArmyCountryIndex = 0;
        int lowestArmyConnectedCountryIndex = 0; //similar to the draft phase, searching for the country with lowest troops
        ArrayList<Country> fortCountries = new ArrayList<>();
        Collections.shuffle(this.getCountriesOwned());
        for (int i = 1; i < this.getTotalNumberOfCountries(); i++) {
            if (this.getCountriesOwned().get(i).getNumberOfArmies() > this.getCountriesOwned().get(highestArmyCountryIndex).getNumberOfArmies()) {
                highestArmyCountryIndex = i; //Index of country with the highest amount of Troops
            }
        }
        Country countryWithHighestNumberOfArmies = this.getCountriesOwned().get(highestArmyCountryIndex);
        countryWithHighestNumberOfArmies.connectedCountries(countryWithHighestNumberOfArmies, fortCountries); //After this,the fortCountries List should contain all connected countries
        if (fortCountries.isEmpty() == false) { //Makes sure there are connected countries
            for (int i = 1; i < fortCountries.size(); i++) {
                if (fortCountries.get(i).getNumberOfArmies() < fortCountries.get(lowestArmyConnectedCountryIndex).getNumberOfArmies()) {
                    lowestArmyConnectedCountryIndex = i;
                }
            }
            moveTroopsAI(this.getCountriesOwned().get(highestArmyCountryIndex), fortCountries.get(lowestArmyConnectedCountryIndex));
        }
    }

    /**
     * Moves troops from one country to another
     * AI implementation splits the troops evenly between the two countries
     * If the number is odd, the extra troop is moved to countryTo
     *
     * @param countryFrom is supplying the troops
     * @param countryTo   is accepting the troops
     */
    private void moveTroopsAI(Country countryFrom, Country countryTo) {
        int totalTroops = countryFrom.getNumberOfArmies() + countryTo.getNumberOfArmies();
        int extraTroop = totalTroops % 2; //Only equals 1 if odd number of troops
        int sharedTroop = (totalTroops - extraTroop) / 2; //Always be an even number
        countryFrom.setArmy(sharedTroop);
        countryTo.setArmy(sharedTroop + extraTroop);
    }

    /**
     * Text represenation of the player
     * @return String representation of the player
     */
    public String toString(){
        return "AI Player" + getPlayerID();
    }

}
