package risk.controller;

import risk.model.Game;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Attack From Controller listens to the player choosing the attacking country
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class AttackFromController implements ActionListener {

    private Country country;
    private RiskView riskView;
    private AttackPhase attackPhase;

    /**
     * creates the controller to listen to the player choosing the attacking country
     * @param riskView view of the game
     * @param country attack country chosen bu the player
     * @param attackPhase of the game
     */
    public AttackFromController(RiskView riskView,Country country, AttackPhase attackPhase){
        this.country=country;
        this.riskView=riskView;
        this.attackPhase=attackPhase;
    }

    /**
     * listens to the user choosing the atacking country
     * @param e press button of the attacking country
     */
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
