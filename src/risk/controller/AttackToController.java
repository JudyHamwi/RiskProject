package risk.controller;

import risk.model.Game;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackToController implements ActionListener {


    private Game gameModel;
    private Country country;
    private RiskView riskView;
    private AttackPhase attackPhase;

    public AttackToController(RiskView riskView, Game game, Country country, AttackPhase attackPhase){
        this.country=country;
        this.riskView=riskView;
        this.attackPhase=attackPhase;
        gameModel=game;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(attackPhase.selectDefendingCountry(country)) {
            attackPhase.runAttack();
            attackPhase.reset();
        }
    }
}
