package RiskController;

import RiskModel.Game;
import RiskModel.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackPhaseController implements ActionListener {

    public AttackPhaseController(Game game){
        this.game=game;
    }
    private Game game;
    @Override
    public void actionPerformed(ActionEvent e) {
        game.setPhase(GameState.ATTACK_PHASE);
    }
}
