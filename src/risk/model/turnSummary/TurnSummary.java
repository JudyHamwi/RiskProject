package risk.model.turnSummary;

import risk.view.RiskView;

import java.util.LinkedList;
import java.util.List;

/**
 * To record the summary of the user actions when the turn is ended
 * Specifically to display the actions of the AI player after each of their turn.
 */
public class TurnSummary {
    private LinkedList<DraftAction> draftActions;
    private LinkedList<AttackAction> attackSummaries;
    private LinkedList<FortifyAction> fortifySummaries;

    public TurnSummary() {
        this.draftActions = new LinkedList<>();
        this.attackSummaries = new LinkedList<>();
        this.fortifySummaries = new LinkedList<>();
    }

    public void recordDraft(DraftAction action) {
        draftActions.add(action);
    }

    public void recordAttack(AttackAction action) {
        attackSummaries.add(action);
    }

    public void recordFortify(FortifyAction action) {
        fortifySummaries.add(action);
    }

    public LinkedList<DraftAction> getDraftActions() {
        return draftActions;
    }
    public LinkedList<AttackAction> getAttackSummaries() {return attackSummaries;}
    public LinkedList<FortifyAction> getFortifySummaries() {return fortifySummaries;}

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

/*
In TurnSummary have a method called public String printSummary()
It will contain the logic of creating your summary.
You would add the TurnSummary object as a parameter of
the handleAISummary UI method. It would call turnSummary.printSummary
and pass it to the JOptionPane.showMessageDialog
 */
