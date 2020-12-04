package risk.model.player;

import risk.model.board.Board;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AI implements Player {

    private final int id;
    private final Board board;

    public AI(final int id, final Board board) {
        this.id = id;
        this.board = board;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void performDraft(final DraftPhase draftPhase) {
        while(draftPhase.haveArmiesToPlace()) {
            final Country countryToReinforce = getOwnedCountriesByArmySize().get(0);

            draftPhase.placeArmy(countryToReinforce);
        }
    }

    @Override
    public void performAttack(final AttackPhase attackPhase) {
        boolean attackingCountryFound = trySelectingAnAttackingCountry(attackPhase);
        while (attackingCountryFound) {
            final Country defendingCountry = selectAdjacentEnemyCountry(attackPhase.getAttackerCountry());
            attackPhase.selectDefendingCountry(defendingCountry);

            attackPhase.runAttack();

            attackPhase.reset();
            attackingCountryFound = trySelectingAnAttackingCountry(attackPhase);
        }
    }

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
                fortifyPhase.fortify(armiesToMove);
                break;
            }
        }
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