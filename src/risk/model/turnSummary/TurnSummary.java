package risk.model.turnSummary;

import risk.view.RiskView;

import java.util.LinkedList;
import java.util.List;

/**
 * TurnSummary is a class that summarizes an AI's move in their attacking phase, drafting phase and fortifying phase
 * altogether. It is shown at the end of an AI's turn.
 */
public class TurnSummary {
    private LinkedList<DraftAction> draftActions;
    private LinkedList<AttackAction> attackSummaries;
    private LinkedList<FortifyAction> fortifySummaries;

    /**
     * Constructs a new summary
     */
    public TurnSummary() {
        this.draftActions = new LinkedList<>();
        this.attackSummaries = new LinkedList<>();
        this.fortifySummaries = new LinkedList<>();
    }

    /**
     * Adds a draft action to draftActions list
     *
     * @param action DraftAction
     */
    public void recordDraft(DraftAction action) {
        draftActions.add(action);
    }

    /**
     * Adds an attack action to attackSummaries list
     *
     * @param action AttackAction
     */
    public void recordAttack(AttackAction action) {
        attackSummaries.add(action);
    }

    /**
     * Adds a fortify action to fortifySummaries list
     *
     * @param action FortifyAction
     */
    public void recordFortify(FortifyAction action) {
        fortifySummaries.add(action);
    }

    /**
     * Returns the draftActions list
     *
     * @return LinkedList<DraftAction>
     */
    public LinkedList<DraftAction> getDraftActions() {
        return draftActions;
    }

    /**
     * Returns the attackSummaries list
     *
     * @return LinkedList<AttackAction>
     */
    public LinkedList<AttackAction> getAttackSummaries() {return attackSummaries;}

    /**
     * Returns the fortifySummaries list
     *
     * @return LinkedList<FortifyAction>
     */
    public LinkedList<FortifyAction> getFortifySummaries() {return fortifySummaries;}

    /**
     * A string representation of what the AI did in one turn.
     *
     * @return String
     */
    public String printSummary() {
        StringBuilder sb = new StringBuilder();

        for (DraftAction da : draftActions) {
            sb.append(da.toString());
        }

        for(AttackAction aa : attackSummaries) {
            sb.append(aa.toString());
        }

        for (FortifyAction fa : fortifySummaries) {
            sb.append(fa.toString());
        }

        return sb.toString();
    }
}
