package risk.controller;

import risk.model.Game;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackFromController implements ActionListener {

    private Game gameModel;
    private Country country;
    private RiskView riskView;
    private AttackPhase attackPhase;

    public AttackFromController(RiskView riskView, Game game, Country country, AttackPhase attackPhase){
        this.country=country;
        this.riskView=riskView;
        this.attackPhase=attackPhase;
        gameModel=game;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        attackPhase.setRiskViews(gameModel.getViews());
        attackPhase.selectAttackingCountry(country);
    }
}
