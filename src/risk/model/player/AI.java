package risk.model.player;

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

public class AI implements Player {

    private final int id;
    private final Board board;
    private final TurnSummary AISummary;

    public AI(final int id, final Board board) {
        this.id = id;
        this.board = board;
        this.AISummary = new TurnSummary();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void performDraft(final DraftPhase draftPhase) {
        final Country countryToReinforce = getOwnedCountriesByArmySize().get(0);
        while(draftPhase.haveArmiesToPlace()) {
            draftPhase.placeArmy(countryToReinforce);
        }
        addDraftActionSummary(countryToReinforce);
    }

    @Override
    public void performAttack(final AttackPhase attackPhase) {
        boolean attackingCountryFound = trySelectingAnAttackingCountry(attackPhase);
        while (attackingCountryFound) {
            final Country defendingCountry = selectAdjacentEnemyCountry(attackPhase.getAttackerCountry());
            attackPhase.selectDefendingCountry(defendingCountry);

            attackPhase.runAttack();

            addAttackActionSummary(attackPhase.getAttackerCountry(), this, attackPhase.getAttackingArmiesLost(),
                    attackPhase.getDefenderCountry(), attackPhase.getDefendingArmiesLost(),
                    attackPhase.getDefenderCountry().getNumberOfArmies() < 1 );

            attackPhase.reset();
            attackingCountryFound = trySelectingAnAttackingCountry(attackPhase);

        }

    }

    @Override
    public void performFortify(final FortifyPhase fortifyPhase) {
        final List<Country> strongestToWeakest = new ArrayList<>();
        for (Country country : getOwnedCountriesByArmySize()) {
            strongestToWeakest.add(country);
        }
        strongestToWeakest.sort(Comparator.comparing(Country::getNumberOfArmies).reversed());

        for (final Country selectedFrom : strongestToWeakest) {
            if (!fortifyPhase.selectMovingFrom(selectedFrom)) {
                continue;
            }
            final Country weakestConnectedCountry = selectedFrom.getConnectedCountries().stream().min(Comparator.comparing(Country::getNumberOfArmies))
                    .get();
            fortifyPhase.selectMovingTo(weakestConnectedCountry);

            final int armiesToMove = selectedFrom.getNumberOfArmies() / 2;
            fortifyPhase.fortify(armiesToMove);

            addFortifyActionSummary(selectedFrom, weakestConnectedCountry); // Summarizes AI's drafting phase

            break;
        }
    }

    @Override
    public void performEndTurn(List<RiskView> views) {
        //for each view call handleAITurn. need to implement turn Summary object and populate it as we run the turns
        views.forEach(v -> v.handleAITurn(AISummary));

    }

    private List<Country> getOwnedCountriesByArmySize() {
        return board.getCountriesOwnedBy(this).stream()
                .sorted(Comparator.comparing(Country::getNumberOfArmies))
                .collect(Collectors.toList());
    }

    private boolean trySelectingAnAttackingCountry(final AttackPhase attackPhase) {
        for (final Country country : board.getCountriesOwnedBy(this)) {
            if (attackPhase.selectAttackingCountry(country)) {
                return true;
            }
        }
        return false;
    }

    private Country selectAdjacentEnemyCountry(final Country ownCountry) {
        return ownCountry.getAdjacentCountries().stream()
                .filter(adjCountry -> !Objects.equals(this, adjCountry.getCurrentOwner()))
                .findAny()
                .get();
    }

    public void addAttackActionSummary(Country attackerCountry, Player attacker, int attackingUnitsLost,
                                       Country defendingCountry, int defendingUnitsLost, boolean ifDefenderLost) {
        AttackAction aa = new AttackAction(attackerCountry, attacker, attackingUnitsLost, defendingCountry, defendingUnitsLost, ifDefenderLost);
        AISummary.recordAttack(aa);
    }

    public void addDraftActionSummary(Country draftingCountry) {
        DraftAction da = new DraftAction(draftingCountry);
        AISummary.recordDraft(da);
    }

    public void addFortifyActionSummary(Country moveFrom, Country moveTo) {
        FortifyAction fa = new FortifyAction(moveFrom, moveTo);
        AISummary.recordFortify(fa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AI ai = (AI) o;
        return id == ai.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AI_" + id;
    }
}