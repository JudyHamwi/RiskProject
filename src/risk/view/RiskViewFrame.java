package risk.view;

import risk.controller.*;
import risk.model.*;
import risk.model.board.Board;
import risk.model.board.Country;
import risk.model.board.OriginalBoardFactory;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.model.phase.PhaseFactory;
import risk.model.player.Player;
import risk.model.player.PlayerFactory;
import risk.model.player.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Risk View Frame that is the display of the Risk Game. It contains the board of the game
 * and necessary tools for an efficient display and use of the game.
 *
 * @author Sarah Jaber
 * @author Walid Baitul Islam
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 1.0
 */
public class RiskViewFrame extends JFrame implements RiskView {

    public static final int BOARD_HEIGHT = 1100;
    public static final int BOARD_WIDTH = 800;
    private static final int MAX_NUM_PLAYERS = 6;
    private static final File BG_IMAGE = new File("images/background.jpg");//TODO

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
    private PlayerFactory playerFactory;

    /**
     * creates the View of the Risk Game
     */
    public RiskViewFrame() {
        super("RISK Game");
        final Board board = new OriginalBoardFactory().build();
        gameModel = new Game(board, new PhaseFactory());
        playerFactory = new PlayerFactory();
        selectedAttackButton = null;
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

    public JFrame getRiskFrame() {
        return this;
    }

    /**
     * Creates the Initial page of the Risk Game
     *
     * @return panel to be added to the main frame of the Risk Game
     */
    public JPanel startPanel() {
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
    public void setNumberOfPlayersMenu() {
        this.numberOfPlayers = new JMenu("Players");
        numberOfPlayers.setName("Players");
        for (int i = 2; i <= MAX_NUM_PLAYERS; i++) {
            JMenuItem numPlayer = new JMenuItem(i + " Players");
            numPlayer.addActionListener(new InitializationController(this, gameModel, i, playerFactory));
            numberOfPlayers.add(numPlayer);
        }
        menuBar.add(numberOfPlayers);
    }

    /**
     * Creates the list of number of AI players the users can choose from to play the game
     *
     * @param numberOfAI players than can be chosen as options based on the number of
     *                   players in the game
     */
    public void handleSetNumOfAIPlayers(int numberOfAI) {
        this.numberOfAIPlayers = new JMenu("AI Players");
        numberOfAIPlayers.setName("AIplayers");
        for (int i = 0; i < MAX_NUM_PLAYERS - numberOfAI; i++) {
            JMenuItem numPlayer = new JMenuItem(i + " AI Players");
            numPlayer.addActionListener(new AIInitializationController(gameModel, i, playerFactory));
            numberOfAIPlayers.add(numPlayer);
        }
        this.numberOfPlayers.setVisible(false);
        menuBar.add(numberOfAIPlayers);
    }

    /**
     * Updates the view when the player wants to play a new game by displaying the board and
     * allowing the user to choose the number of players.
     *
     * @param game  model that deals with the logic of the game
     * @param board that contains th continents and countries of the game
     */
    @Override
    public void handleNewGame(Game game, Board board) {
        setNumberOfPlayersMenu();
        //setNumOfAIPlayers();
        this.remove(mainMenuPanel);
        boardView = new BoardView(this, game, board);
        this.add(boardView, BorderLayout.CENTER);
        this.add(gameStatusPanel, BorderLayout.SOUTH);
        menuBar.add(numberOfPlayers);
        //menuBar.add(numberOfAIPlayers);
        menu.setText("Menu");
        menu.remove(newGame);
        menu.add(helpMenuItem);
        JOptionPane.showMessageDialog(this, "WELCOME TO RISK! \nPlease select the number of players and the " +
                "number of AI players" +
                " through the menu bar");
    }

    /**
     * Updates the view based on the initialization of the game
     *
     * @param game model that deals with the logic of the game
     */
    public void handleInitialize(final Game game) {
        gameStatus.setText(game.getState().toString());
        currentPlayer.setText(game.getCurrentPlayer().toString());
        boardView.InitializeBoard(game.getNumPlayers());
        boardView.addInGamePanel(game, game.getCurrentPlayer());
        numberOfAIPlayers.setVisible(false);
    }

    /**
     * updates the view when the player ends their turn
     *
     * @param player with the current turn
     */
    public void handleEndTurn(Player player) {
        currentPlayer.setText(player.toString());
        boardView.removeHighlightedButtons();
        JOptionPane.showMessageDialog(this, player.toString() + ", it is your turn!");
    }

    public void handleNewDraftPhase(final User drafter, final DraftPhase draftPhase) {
        boardView.getDraftArmies().setText("Draft Armies: " + draftPhase.getArmiesToPlace());
        boardView.getAttackPhaseButton().setEnabled(true);
        boardView.getFortifyButton().setEnabled(false);
        boardView.getFortifyPhaseButton().setEnabled(false);
        boardView.getAttackButton().setEnabled(false);
        boardView.getDraftArmies().setVisible(true);

        boardView.setupCountryListeners(country -> new DraftPhaseController(this, drafter, draftPhase, country));
    }

    /**
     * updats the view when the player asks for help
     *
     * @param game   model that deals with the logic of the game
     * @param string help information
     */
    public void handlePrintHelp(Game game, String string) {
        JOptionPane.showMessageDialog(this, string);
    }

    /**
     * updates the view when the player chooses an invalid country to attack from
     *
     * @param game model that deals with the logic of the game
     */
    @Override
    public void handleCanNotAttackFrom(Game game) {
        JOptionPane.showMessageDialog(this, "Can not attack Country");
    }

    /**
     * updates the view when the player chooses a valid country to attack from
     *
     * @param game    model that deals with the logic of the game
     * @param country that the player wants to attack from
     */
    @Override
    public void handleCanAttackFrom(Game game, Country country) {
        if (selectedAttackButton != null) {
            boardView.removeHighlightCountry(selectedAttackButton);
        }
        selectedAttackButton = country;
        boardView.highlightAdjacentCountries(country);
        boardView.getAttackButton().setEnabled(true);
        boardView.getAttackPhaseButton().setEnabled(false);
    }

    /**
     * updates the view when its time for the user to choose to attack a country
     */
    public void handleNewAttack() {
        boardView.getAttackButton().setEnabled(false);
    }

    /**
     * updates the view when the user enters the fortify phase
     */
    public void handleNewFortifyPhase(final User fortifier, final FortifyPhase fortifyPhase) {
        boardView.getFortifyPhaseButton().setEnabled(false);
        boardView.getFortifyButton().setEnabled(false);

        boardView.setupCountryListeners(country -> new FortifyFromController(this, fortifier, fortifyPhase, country));

    }

    public void handleSelectFortifyTo(final User fortifier, final FortifyPhase fortifyPhase) {
        boardView.setupCountryListeners(country -> new FortifyToController(this, fortifier, fortifyPhase, country));
    }

    @Override
    public void handleFortifyFromSelected(Country country) {
        boardView.highlightFortifyingCountries(country.getConnectedCountries());
        boardView.getFortifyButton().setEnabled(true);

    }

    /**
     * warning message displayed when there are not enough armies to fortify from a country
     *
     * @param game model that deals with the logic of the game
     */
    @Override
    public void handleCanNotFortifyArmies(Game game) {
        JOptionPane.showMessageDialog(this, "Not enough armies to fortify !");

    }

    /**
     * warning message displayed if the player can not fortify from a country
     *
     * @param game model that deals with the logic of the game
     */
    @Override
    public void handleCanNotFortify(Game game) {
        JOptionPane.showMessageDialog(this, "Can not fortify !");
    }

    /**
     * view updated when the fortify phase occurs successfully
     */
    @Override
    public void handleFortifyToSelected() {
        boardView.updateCountryButtons();
    }

    /**
     * updates the view of the board when a player adds an army to country in the draft phase
     *
     * @param country that the play added an army to
     */
    @Override
    public void handleAddedArmy(final Country country, final int armiesLeftToDraft) {
        boardView.addArmyToCountry(country);
        boardView.getDraftArmies().setText("Draft Armies: " + armiesLeftToDraft);
        if (armiesLeftToDraft == 0) {
            boardView.getDraftArmies().setVisible(false);
        }
    }

    /**
     * updates an error message when a player tries to draft an army to country
     * they do not own
     */
    @Override
    public void handleCanNotDraftFrom() {
        JOptionPane.showMessageDialog(this, "You do not own this country !");
    }

    /**
     * get the board view
     *
     * @return Boarview of the view
     */
    public BoardView getBoardView() {
        return boardView;
    }


    /**
     * update the view when the attack phase is complete
     *
     * @param game            model that deals with the logic of the game
     * @param attackerCountry country that the player attacked from
     * @param defenderCountry country that the player attacks
     * @param attackSuccess   true of the player conquered the country and false otherwise
     */
    @Override
    public void handleAttackResult(Game game, Country attackerCountry, Country defenderCountry, boolean attackSuccess, boolean winner, Player playerRemoved) {
        if (attackSuccess) {
            JOptionPane.showMessageDialog(this, "You conquered the country!");
            if (playerRemoved != null) {
                JOptionPane.showMessageDialog(this, playerRemoved + "Lost !");
            }
            if (winner) {
                JOptionPane.showMessageDialog(this, "You Conquered the World !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "You did not conquer the country!");
        }
        boardView.getAttackButton().setEnabled(true);
        boardView.removeHighlightCountry(attackerCountry);
        boardView.updateCountryButtons();
        selectedAttackButton = null;
    }

    /**
     * Update the view after the completion of the AI turn
     *
     * @param numberOfAttacks that the AI player atacked
     * @param player          the AI player
     */
    @Override
    public void handleAITurn(int numberOfAttacks, Player player) {
        JOptionPane.showMessageDialog(this, "AI " + player + " attacked " + numberOfAttacks + "times");
        boardView.updateBoardForAI();
    }

    @Override
    public void handleEndGame(final Player winner) {
        JOptionPane.showMessageDialog(this, winner + " Conquered the World !");
    }

    @Override
    public int getNumber(final int min, final int max, final String message) {
        final String number = JOptionPane.showInputDialog(this, "Number of armies to move");
        return Integer.parseInt(number);
    }


}