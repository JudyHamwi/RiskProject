package risk.controller;

import risk.model.Game;
import risk.model.board.Country;
import risk.model.phase.FortifyPhase;
import risk.model.player.User;
import risk.view.RiskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Fortify To controller listens to the user picking the country to fortify to
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
 */
public class FortifyToController implements ActionListener {
    private final RiskView view;
    private final FortifyPhase fortifyPhase;
    private final Country country;
    private Game game;

    /**
     * creates the listener to choose the fortufying to country
     * @param riskView view of the game
     * @param game model
     * @param country fortifying from
     * @param fortifyPhase of the game
     */
    public FortifyToController(final RiskView riskView, Game game, final Country country, final FortifyPhase fortifyPhase) {
        view = riskView;
        this.fortifyPhase = fortifyPhase;
        this.country = country;
        this.game = game;
    }

    /**
     * listens to the user choosing the country to fortify to
     * @param e pressing the country to fortify to
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (fortifyPhase.selectMovingTo(country)) {
            User user = (User) game.getCurrentPlayer();
            user.wakeUser(user);
        }else{
            view.handleCanNotFortify();
        }
    }
}
