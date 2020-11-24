package RiskController;

import RiskModel.Country;
import RiskModel.Game;
import RiskModel.GameState;
import RiskView.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Draft Phase Controller listens to player's moves in the draft phase
 * @version 1.0
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @author Walid Baitul
 */
public class DraftPhaseController implements ActionListener {

    private Game gameModel;
    private Country country;
    private RiskView riskView;

    /**
     * creates the attack phase controller to listen to entering  the attack phase
     * @param riskView the main view of the game
     * @param game model that deals with the logic of the game
     * @param country that id being drafted
     */
    public DraftPhaseController(RiskView riskView, Game game, Country country){
        this.gameModel = game;
        this.country=country;
        this.riskView=riskView;
    }

    /**
     * calls the game to deal with the country being chosen to draft
     * @param e country pressed to draft armies to
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameModel.getState()== GameState.DRAFT_PHASE){
            gameModel.draftNewArmy(country);
        }
    }
}
