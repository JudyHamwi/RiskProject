package RiskController;

import RiskModel.Game;
import RiskModel.GameState;
import RiskView.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackPhaseController implements ActionListener {

    private Game game;
    private BoardView boardView;

    public AttackPhaseController(Game game, BoardView boardView){
        this.game=game;
        this.boardView=boardView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        game.setPhase(GameState.ATTACK_PHASE);
        boardView.getAttackPhaseButton().setEnabled(false);
        boardView.getAttackButton().setEnabled(true);
        boardView.getFortifyPhaseButton().setEnabled(true);

    }
}
