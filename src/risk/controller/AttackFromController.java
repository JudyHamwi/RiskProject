package risk.controller;

import risk.model.Game;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackFromController implements ActionListener {

    private Country country;
    private RiskView riskView;
    private AttackPhase attackPhase;

    public AttackFromController(RiskView riskView,Country country, AttackPhase attackPhase){
        this.country=country;
        this.riskView=riskView;
        this.attackPhase=attackPhase;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(attackPhase.isValidAttackingCountry(country)){
            riskView.handleCanAttackFrom(country);
            attackPhase.selectAttackingCountry(country);
            riskView.getBoardView().getAttackButton().setEnabled(true);
        } else{
            riskView.handleCanNotAttackFrom();
        }
    }
}
