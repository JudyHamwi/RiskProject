package risk.model.player;

import risk.model.GameState;
import risk.model.board.Board;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.model.turnSummary.AttackAction;
import risk.model.turnSummary.DraftAction;
import risk.model.turnSummary.FortifyAction;
import risk.model.turnSummary.TurnSummary;
import risk.view.RiskView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class generates an AI's move in the drafting phase, attacking phase and fortifying phase
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class AI implements Player {

    private int id;
    private Board board;
    private final TurnSummary AISummary;

    /**
     * Constructs a new AI object
     *
     * @param id    int
     * @param board Board
     */
    public AI(final int id, Board board) {
        this.id = id;
        this.board = board;
        this.AISummary = new TurnSummary();
    }

    /**
     * Returns AI's id
     *
     * @return int AI's id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * While AI still has bonus armies to draft, AI will draft these armies to the first country
     * with the least amount of armies in the AI's owned countries' list.
     *
     * @param draftPhase
     * @return GameState the next state of the game
     */
    @Override
    public GameState performDraft(final DraftPhase draftPhase) {
        final Country countryToReinforce = getOwnedCountriesByArmySize().get(0);
        while (draftPhase.haveArmiesToPlace()) {
            draftPhase.placeArmy(countryToReinforce);
        }
        addDraftActionSummary(countryToReinforce);
        return GameState.ATTACK_PHASE;
    }

    /**
     * Finds an attacking country for the AI and keeps attacking until AI can no longer attack.
     *
     * @param attackPhase
     * @return GameState the next state of the game
     */
    @Override
    public GameState performAttack(final AttackPhase attackPhase) {
        boolean attackingCountryFound = trySelectingAnAttackingCountry(attackPhase);
        while (attackingCountryFound) {
            final Country defendingCountry = selectAdjacentEnemyCountry(attackPhase.getAttackerCountry());
            attackPhase.selectDefendingCountry(defendingCountry);

            attackPhase.runAttack();

            addAttackActionSummary(attackPhase.getAttackerCountry(), this, attackPhase.getDefenderCountry());

            attackPhase.reset();
            attackingCountryFound = trySelectingAnAttackingCountry(attackPhase);

        }
        return GameState.FORTIFY_PHASE;
    }

    /**
     * Finds a fortifying country with the greatest number of armies and moves half of its armies to a country
     * with the least number of armies.
     *
     * @param fortifyPhase
     */
    @Override
    public void performFortify(final FortifyPhase fortifyPhase) {
        final List<Country> strongestToWeakest = getOwnedCountriesByArmySize().stream()
                .sorted(Comparator.comparing(Country::getNumberOfArmies).reversed())
                .collect(Collectors.toList());

        for (final Country selectedFrom : strongestToWeakest) {
            if (fortifyPhase.selectMovingFrom(selectedFrom)) {
                final Country weakestConnectedCountry = selectedFrom.getConnectedCountries().stream()
                        .sorted(Comparator.comparing(Country::getNumberOfArmies))
                        .findFirst()
                        .get();
                fortifyPhase.selectMovingTo(weakestConnectedCountry);

                final int armiesToMove = selectedFrom.getNumberOfArmies() / 2;
                fortifyPhase.setArmiesToMove(armiesToMove);
                addFortifyActionSummary(selectedFrom, weakestConnectedCountry);
                fortifyPhase.fortify();
                break;
            }

            break;
        }
    }

    /**
     * Ends the AI's turn and notifies  RiskViewFrame
     *
     * @param views
     */
    @Override
    public void performEndTurn(List<RiskView> views) {
        //for each view call handleAITurn. need to implement turn Summary object and populate it as we run the turns
        views.forEach(v -> v.handleAITurn(AISummary));

    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Adds a summary of the moves the AI made during their attacking phase
     *
     * @param attackerCountry
     * @param attacker
     * @param defendingCountry
     */
    public void addAttackActionSummary(Country attackerCountry, Player attacker, Country defendingCountry) {
        AttackAction aa = new AttackAction(attackerCountry, attacker, defendingCountry);
        AISummary.recordAttack(aa);
    }

    /**
     * Adds a summary of the moves the AI made during their drafting phase
     *
     * @param draftingCountry
     */
    public void addDraftActionSummary(Country draftingCountry) {
        DraftAction da = new DraftAction(draftingCountry);
        AISummary.recordDraft(da);
    }

    /**
     * Adds a summary of the moves the AI made during their fortifying phase
     *
     * @param moveFrom
     * @param moveTo
     */
    public void addFortifyActionSummary(Country moveFrom, Country moveTo) {
        FortifyAction fa = new FortifyAction(moveFrom, moveTo);
        AISummary.recordFortify(fa);
    }

    /**
     * Checks if two AI objects are equal
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AI ai = (AI) o;
        return this.id == ai.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * A string representation of the AI
     */
    @Override
    public String toString() {
        return "AI_" + id;
    }

    /**
     * Returns the countries an AI owns in a sorted list according to the countries' number of armies.
     *
     * @return List<Country> a list of countries
     */
    private List<Country> getOwnedCountriesByArmySize() {
        List<Country> list = new ArrayList<>();
        for (Country country : board.getCountriesOwnedBy(this)) {
            list.add(country);
        }
        list.sort(Comparator.comparing(country -> country.getNumberOfArmies()));
        return list;
    }

    /**
     * Selects an attacking country for the AI
     *
     * @param attackPhase
     * @return boolean true if an attacking country was found, otherwise false
     */
    private boolean trySelectingAnAttackingCountry(final AttackPhase attackPhase) {
        for (final Country country : board.getCountriesOwnedBy(this)) {
            if (attackPhase.selectAttackingCountry(country)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Selects an enemy country adjacent to ownCountry
     *
     * @param ownCountry
     * @return Country a country owned by an enemy that is adjacent to one of AI's country
     */
    private Country selectAdjacentEnemyCountry(final Country ownCountry) {
        return ownCountry.getAdjacentCountries().stream()
                .filter(adjCountry -> !Objects.equals(this, adjCountry.getCurrentOwner()))
                .findAny()
                .get();
    }

}