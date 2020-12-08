package risk.controller;

import risk.model.GameState;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.model.player.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Attack To Controller listens to the user choosing the defending country
 *  @author Sarah Jaber
 *  @author Judy Hamwi
 *  @author Diana Miraflor
 *  @version 2.0
 */
public class AttackToController implements ActionListener {


    private Country country;
    private AttackPhase attackPhase;
    private User user;

    /**
     * creates the listener to the player choosing the defender country
     * @param country to be attacked
     * @param attackPhase of the game
     * @param user that is attacking
     */
    public AttackToController(Country country, AttackPhase attackPhase, User user){
        this.country=country;
        this.attackPhase=attackPhase;
        this.user = user;
    }

    /**
     * listens to the press of country to attack
     * @param e country to be attacked pressed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(attackPhase.selectDefendingCountry(country)) {
            user.wakeUser(GameState.ATTACK_PHASE);
        }
    }
}
