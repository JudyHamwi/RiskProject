package risk.model.turnSummary;

import java.util.List;

/**
 * To record the summary of the user actions when the turn is ended
 * Specifically to display the actions of the AI player after each of there turn.
 */
public class TurnSummary {
    private List<DraftAction> draftActions;
    private List<AttackAction> attackSummaries;
    private List<FortifyAction> fortifySummaries;

    public void recordDraft(DraftAction action) {
        draftActions.add(action);
    }

    public List<DraftAction> getDraftActions() {
        return draftActions;
    }


    // Same for other 3
}

/*
In TurnSummary have a method called public String printSummary()
It will contain the logic of creating your summary.
You would add the TurnSummary object as a parameter of
the handleAISummary UI method. It would call turnSummary.printSumamry
and pass it to the JOptionPane.showMessageDialog
 */