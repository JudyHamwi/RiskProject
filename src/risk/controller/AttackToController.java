package risk.controller;

import risk.model.GameState;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.model.player.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackToController implements ActionListener {


    private Country country;
    private AttackPhase attackPhase;
    private User user;

    public AttackToController(Country country, AttackPhase attackPhase, User user){
        this.country=country;
        this.attackPhase=attackPhase;
        this.user = user;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(attackPhase.selectDefendingCountry(country)) {
            user.wakeUser(GameState.ATTACK_PHASE);
        }
    }
}
