package RiskView;

import RiskController.*;
import RiskModel.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Risk View Frame that is the display of the Risk Game. It contains the board of the game
 * and necessary tools for an efficient display and use of the game.
 * @version 1.0
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 */
public class RiskViewFrame extends JFrame implements RiskView {

    public static final int BOARD_HEIGHT = 1100 ;
    public static final int BOARD_WIDTH= 800;
    private static final int MAX_NUM_PLAYERS = 6;
    private static final File BG_IMAGE = new File("images/background.jpg");

    private JLabel background;
    private JPanel mainMenuPanel;
    private JPanel gameStatusPanel;
    private JLabel gameStatus;
    private JLabel currentPlayer;
    private JMenu numberOfPlayers;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newGame;
    private JMenuItem quitGame;
    private JMenuItem helpMenuItem;
    private Game gameModel;
    private BoardView boardView;
    private Country selectedAttackButton;
    private JMenu numberOfAIPlayers;

    /**
     * creates the View of the Risk Game
     */
    public RiskViewFrame() {
        super("RISK Game");
        gameModel = new Game();
        selectedAttackButton=null;
        this.setLayout(new BorderLayout());

        gameStatusPanel = new JPanel();
        gameStatusPanel.setLayout(new BorderLayout());
        gameStatus = new JLabel("Game Status: ");
        currentPlayer = new JLabel("Current Player: ");
        gameStatusPanel.add(gameStatus, BorderLayout.EAST);
        gameStatusPanel.add(currentPlayer, BorderLayout.WEST);
        menuBar = new JMenuBar();
        menu = new JMenu("Start");
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new NewGameController(this, gameModel));
        quitGame = new JMenuItem("Quit Game");
        quitGame.addActionListener(new QuitGameController());
        helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(new HelpController(gameModel));

        menu.add(newGame);
        menu.add(quitGame);
        menuBar.add(menu);
        startPanel();
        this.add(mainMenuPanel);
        this.add(menuBar, BorderLayout.NORTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(200, 0);
        this.setMinimumSize(new Dimension(BOARD_HEIGHT, BOARD_WIDTH));

    }

    public static void main(String[] args) {
        RiskViewFrame view = new RiskViewFrame();
    }

    /**
     * Creates the Initial page of the Risk Game
     * @return panel to be added to the main frame of the Risk Game
     */
    public JPanel startPanel(){
        this.mainMenuPanel = new JPanel(new BorderLayout());
        try {
            background = new JLabel(new ImageIcon(ImageIO.read(BG_IMAGE)));
        } catch (IOException e) {
            throw new IllegalStateException("Error loading the background image.", e);
        }
        mainMenuPanel.add(background);
        mainMenuPanel.setMinimumSize(new Dimension(BOARD_HEIGHT, BOARD_WIDTH));

        return mainMenuPanel;
    }

    /**
     * Creates the list of number of players the users can choose from to play the game
     */
    public void setNumberOfPlayersMenu(){
        this.numberOfPlayers = new JMenu("Players");
        numberOfPlayers.setName("Players");
        for(int i = 2; i <= MAX_NUM_PLAYERS; i++){
            JMenuItem numPlayer = new JMenuItem(i + " Players");
            numPlayer.addActionListener(new InitializationController(gameModel, i));
            numberOfPlayers.add(numPlayer);
        }
        menuBar.add(numberOfPlayers);
    }

    /**
     * Creates the list of number of AI players the users can choose from to play the game
     */
    public void setNumberOfAIPlayersMenu(){
        this.numberOfAIPlayers = new JMenu("AI Players");
        numberOfAIPlayers.setName("AIplayers");
        for(int i = 2; i < MAX_NUM_PLAYERS; i++){
            JMenuItem numPlayer = new JMenuItem(i + " AI Players");
            numPlayer.addActionListener(new AIInitializationController(gameModel, i));
            numberOfAIPlayers.add(numPlayer);
        }
        menuBar.add(numberOfAIPlayers);
    }

    /**
     * Updates the view when the player wants to play a new game by displaying the board and
     * allowing the user to choose the number of players.
     * @param game model that deals with the logic of the game
     * @param board that contains th continents and countries of the game
     */
    @Override
    public void handleNewGame(Game game, Board board) {
        setNumberOfPlayersMenu();
        setNumberOfAIPlayersMenu();
        this.remove(mainMenuPanel);
        boardView = new BoardView(this,game, board);
        this.add(boardView, BorderLayout.CENTER);
        this.add(gameStatusPanel, BorderLayout.SOUTH);
        menuBar.add(numberOfPlayers);
        menuBar.add(numberOfAIPlayers);
        menu.setText("Menu");
        menu.remove(newGame);
        menu.add(helpMenuItem);
        JOptionPane.showMessageDialog(this, "WELCOME TO RISK! \nPlease select the number of players" +
                " through the menu bar");
    }

    /**
     * Updates the view based on the initialization of the game
     * @param game model that deals with the logic of the game
     * @param state current state of the game
     * @param player with the current turn
     * @param numPlayers number of players playing the game
     */
    public void handleInitialization(Game game, GameState state, Player player, int numPlayers){
        gameStatus.setText(state.toString());
        currentPlayer.setText(player.toString());
        boardView.InitializeBoard(numPlayers);
        boardView.addInGamePanel(game, player);
        this.numberOfPlayers.setVisible(false);
        boardView.getAttackPhaseButton().setEnabled(true);
    }

    /**
     * updates the view when the player ends their turn
     * @param game model that deals with the logic of the game
     * @param player with the current turn
     */
    public void handleEndTurn(Game game, Player player) {
        currentPlayer.setText(player.toString());
        JOptionPane.showMessageDialog(this, player.toString() + ", it is your turn!");
        boardView.removeHighlightedButtons();
        boardView.getAttackPhaseButton().setEnabled(true);
        boardView.getFortifyButton().setEnabled(false);
        boardView.getFortifyPhaseButton().setEnabled(false);
        boardView.getAttackButton().setEnabled(false);
    }

    /**
     * updats the view when the player asks for help
     * @param game model that deals with the logic of the game
     * @param string help information
     */
    public void handlePrintHelp(Game game, String string) {
        JOptionPane.showMessageDialog(this, string);
    }

    /**
     * updates the view when the player chooses an invalid country to attack from
     * @param game model that deals with the logic of the game
     */
    @Override
    public void handleCanNotAttackFrom(Game game) {
        JOptionPane.showMessageDialog(this,"Can not attack Country");
    }

    /**
     * updates the view when the player chooses a valid country to attack from
     * @param game model that deals with the logic of the game
     * @param country that the player wants to attack from
     */
    @Override
    public void handleCanAttackFrom(Game game, Country country) {
        if(selectedAttackButton!=null) {
            boardView.removeHighlightCountry(selectedAttackButton);

        }
        selectedAttackButton=country;
        boardView.highlightAdjacentCountries(country);
        boardView.getAttackButton().setEnabled(true);
        boardView.getAttackPhaseButton().setEnabled(false);
    }

    /**
     * updates the view when its time for the user to choose to attack a country
    */
    public void handleNewAttack(){
        boardView.getAttackButton().setEnabled(false);
    }

    /**
     * updates the view when the user enters the fortify phase
     */
    public void handleNewFortifyPhase(){
        boardView.getFortifyPhaseButton().setEnabled(false);
        boardView.getFortifyButton().setEnabled(false);
    }

    @Override
    public void handleCanFortifyFrom(Game game, Country country) {
        boardView.removeAllHighlightCountries();
        boardView.highlightFortifyingCountries(country);
        boardView.getFortifyButton().setEnabled(true);
    }

    /**
     * warning message displayed when there are not enough armies to fortify from a country
     * @param game
     */
    @Override
    public void handleCanNotFortifyArmies(Game game) {
        JOptionPane.showMessageDialog(this,"Not enough armies to fortify !");

    }

    /**
     * warning message displayed if the player can not fortify
     * @param game
     */
    @Override
    public void handleCanNotFortify(Game game) {
        JOptionPane.showMessageDialog(this,"Can not fortify !");
    }

    /**
     * view updated when the fortiphy phase occurs sucessfully
     * @param game
     * @param movingFrom
     * @param movingTo
     */
    @Override
    public void handleFortifyPhase(Game game,Country movingFrom, Country movingTo) {
        boardView.TransferOwnership(movingFrom,movingTo);
    }

    /**
     * get the board view
     * @return
     */
    public BoardView getBoardView(){
        return boardView;
    }


    /**
     * update the view when the attack phase is complete
     * @param game model that deals with the logic of the game
     * @param attackerCountry country that the player attacked from
     * @param defenderCountry country that the player attacks
     * @param attackSuccess true of the player conquered the country and false otherwise
     */
    @Override
    public void handleAttackPhase(Game game, Country attackerCountry, Country defenderCountry, boolean attackSuccess, boolean winner, Player playerRemoved){
        if(attackSuccess) {
            JOptionPane.showMessageDialog(this, "You conquered the country!");
            if(playerRemoved!=null){
                JOptionPane.showMessageDialog(this, playerRemoved + "Lost !");
            }
            if(winner){
                JOptionPane.showMessageDialog(this, "You Conquered the World !");
            }
        }else {
            JOptionPane.showMessageDialog(this, "You did not conquer the country!");
        }
        boardView.getAttackButton().setEnabled(true);
        boardView.removeHighlightCountry(attackerCountry);
        boardView.TransferOwnership(attackerCountry, defenderCountry);
        selectedAttackButton=null;
    }
}
