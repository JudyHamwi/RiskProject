package RiskController;

import RiskModel.Country;
import RiskModel.Game;
import RiskModel.GameState;
import RiskView.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DraftPhaseController implements ActionListener {

    private Game gameModel;
    private Country country;
    private RiskView riskView;

    public DraftPhaseController(RiskView riskView, Game game, Country country){
        this.gameModel = game;
        this.country=country;
        this.riskView=riskView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameModel.getState()== GameState.DRAFT_PHASE){
            gameModel.draftNewArmy(country);
        }
    }
}
