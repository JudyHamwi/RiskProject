package risk.view;

import risk.controller.*;
import risk.model.*;
import risk.model.board.Board;
import risk.model.board.Country;
import risk.model.phase.AttackPhase;
import risk.model.phase.DraftPhase;
import risk.model.phase.FortifyPhase;
import risk.model.player.Player;
import risk.model.player.PlayerFactory;
import risk.model.player.User;
import risk.model.turnSummary.TurnSummary;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Risk View Frame that is the display of the Risk Game. It contains the board of the game
 * and necessary tools for an efficient display and use of the game.
 *
 * @author Sarah Jaber
 * @author Judy Hamwi
 * @author Diana Miraflor
 * @version 2.0
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
    private JMenuItem loadCustomMap;
    private JMenuItem quitGame;
    private JMenuItem helpMenuItem;
    private Game game;
    private BoardView boardView;
    private JMenu numberOfAIPlayers;
    private PlayerFactory playerFactory;
    private JMenuItem save;
    private JMenuItem loadSavedGame;


    /**
     * creates the View of the Risk Game
     */
    public RiskViewFrame() {
        super("RISK Game");
        playerFactory = new PlayerFactory();
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
        newGame.addActionListener(new OriginalMapController(this));
        loadCustomMap = new JMenuItem("Load Custom Map");
        loadCustomMap.addActionListener(new CustomMapController(this));
        quitGame = new JMenuItem("Quit Game");
        quitGame.addActionListener(new QuitGameController());
        helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(new HelpController(this));
        save = new JMenuItem("save");
        loadSavedGame = new JMenuItem("Load Saved Game");
        loadSavedGame.addActionListener(new LoadSavedGameController(this));

        menu.add(newGame);
        menu.add(loadCustomMap);
        menu.add(quitGame);
        menu.add(loadSavedGame);
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
        //final Board board = new OriginalBoardFactory().build();
        //Game game = new Game(board, new PhaseFactory());
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
        for (int i = 1; i <= MAX_NUM_PLAYERS; i++) {
            JMenuItem numPlayer = new JMenuItem(i + " Players");
            numPlayer.addActionListener(new InitializationController(this, game, i, playerFactory));
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
        for (int i = 0; i < MAX_NUM_PLAYERS - (numberOfAI - 1); i++) {
            JMenuItem numPlayer = new JMenuItem(i + " AI Players");
            numPlayer.addActionListener(new AIInitializationController(game, i, playerFactory));
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
        this.remove(mainMenuPanel);
        boardView = new BoardView(this, game, board);
        this.add(boardView, BorderLayout.CENTER);
        this.add(gameStatusPanel, BorderLayout.SOUTH);
        menuBar.add(numberOfPlayers);
        menu.setText("Menu");
        menu.remove(newGame);
        menu.remove(loadCustomMap);
        menu.add(helpMenuItem);
        menu.add(save);
        save.addActionListener(new SaveController(game));
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

    public void handleNewDraftPhase(User drafter, DraftPhase draftPhase) {
        gameStatus.setText(game.getState().toString());
        boardView.getDraftArmies().setVisible(true);
        boardView.getDraftArmies().setText("Draft Armies: " + draftPhase.getArmiesToPlace());
        boardView.getAttackPhaseButton().setEnabled(true);
        boardView.getEndTurnButton().setEnabled(false);
        boardView.getFortifyButton().setEnabled(false);
        boardView.getFortifyPhaseButton().setEnabled(false);
        boardView.getAttackButton().setEnabled(false);
        boardView.getDraftArmies().setVisible(true);
        boardView.setupCountryListeners(country -> new DraftPhaseController(this, drafter, country, game, draftPhase));
    }

    /**
     * updats the view when the player asks for help
     */
    public void handlePrintHelp() {
        String printHelp;
        printHelp = ("Aim to conquer enemy territories!" + "\n" + "\n" + "In game, you have choices to draft bonus armies, attack countries, move armies" +
                " to another country, and end your turn.\n" +
                "To draft bonus armies: \nPress the countries you want to draft you bonus armies to \n" +
                "To attack: \n1. Press the Attack Phase button followed by the country you want to attack from. " +
                "\nEnemy countries you are able to attack will be highlighted. " +
                "\n2. Press the Attack! button" +
                "\n3. Click on a highlighted country you wish to attack. " +
                "\n" + "To fortify: \n1. Enter the fortify phase by pressing the Fortify Phase button followed by the country to move armies from. " +
                "\nCountries you are able to move your armies to will be highlighted." +
                "\n2. Press the Fortify button." +
                "\n3. Click on a highlighted country you wish to move your armies to." +
                "\n" +
                "Pass your turn to another player by pressing" +
                " the end turn button." + "\n" + "\n" + "GOOD LUCK!");
        JOptionPane.showMessageDialog(this, printHelp);
    }


    /**
     * updates the view when the player chooses an invalid country to attack from
     */
    @Override
    public void handleCanNotAttackFrom() {
        JOptionPane.showMessageDialog(this, "Cannot attack from this country.\n Choose another" +
                " country to attack from");
    }

    /**
     * updates the view when the player chooses a valid country to attack from
     *
     * @param country that the player wants to attack from
     */
    @Override
    public void handleCanAttackFrom(Country country) {
        boardView.removeHighlightedButtons();
        boardView.highlightAdjacentCountries(country);
        boardView.getAttackButton().setEnabled(true);
        boardView.getEndTurnButton().setEnabled(false);
        boardView.getAttackPhaseButton().setEnabled(false);
    }

    /**
     * updates the view when its time for the user to choose to attack a country
     */
    public void handleNewAttack(AttackPhase attackPhase, User user) {
        boardView.getAttackButton().setEnabled(false);
        boardView.setupCountryListeners(country -> new AttackToController(country, attackPhase, user));
    }

    /**
     * updates the view when the user chooses a fortifying country
     *
     * @param country fortifying from
     */
    @Override
    public void handleFortifyFromSelected(Country country) {
        boardView.highlightFortifyingCountries(country.getConnectedCountries());
        boardView.getFortifyButton().setEnabled(true);
        System.out.println("View Fortify");
        // no connected countries
        for (Country c : country.getConnectedCountries()) {
            System.out.println(c.getCountryName());
        }
    }


    /**
     * warning message displayed if the player can not fortify from a country
     */
    @Override
    public void handleCanNotFortify() {
        JOptionPane.showMessageDialog(this, "Can not fortify !");
    }

    /**
     * view updated when the user fortifies to a country
     */
    @Override
    public void handleFortifyToSelected() {
        boardView.updateCountryButtons();
        boardView.getFortifyButton().setEnabled(false);
        boardView.removeHighlightedButtons();
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
     * @param attackerCountry country that the player attacked from
     * @param defenderLost    true of the player conquered the country and false otherwise
     */
    @Override
    public void handleAttackResult(Country attackerCountry, boolean defenderLost, AttackPhase attackPhase) {
        if (defenderLost) {
            JOptionPane.showMessageDialog(this, "You conquered the country!");
        } else {
            JOptionPane.showMessageDialog(this, "You did not conquer the country!");
        }
        boardView.getAttackButton().setEnabled(true);
        boardView.removeHighlightCountry(attackerCountry);
        boardView.updateCountryButtons();
        boardView.setupCountryListeners(country -> new AttackFromController(this, country, attackPhase));
    }

    /**
     * updates the ciew when the user enters the fortify phase
     *
     * @param fortifier
     * @param fortifyPhase
     */
    @Override
    public void handleNewFortifyPhase(Player fortifier, FortifyPhase fortifyPhase) {
        gameStatus.setText(game.getState().toString());
        boardView.getFortifyPhaseButton().setEnabled(false);
        boardView.removeHighlightedButtons();
        boardView.getAttackButton().setEnabled(false);
        boardView.getFortifyButton().setEnabled(true);
        boardView.getEndTurnButton().setEnabled(true);
        boardView.setUpFortifyListeners(fortifyPhase);
        boardView.setupCountryListeners(country -> new FortifyFromController(this, game, country, fortifyPhase));
    }

    /**
     * Update the view after the completion of the AI turn
     *
     * @param AISummary A class that summarizes an AI's turn.
     */
    @Override
    public void handleAITurn(TurnSummary AISummary) {
        JTextArea textArea = new JTextArea(AISummary.printSummary());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(textArea);
        sp.setPreferredSize(new Dimension(500, 500));
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        boardView.updateBoardForAI();
        JOptionPane.showMessageDialog(this, sp, "AI's Turn:",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * updates the view when the game is complete and a player has won
     *
     * @param winner
     */
    @Override
    public void handleEndGame(final Player winner) {
        JOptionPane.showMessageDialog(this, winner + " Conquered the World!");
    }

    /**
     * updates the view when the user enters the attack phase
     *
     * @param attackPhase
     */
    @Override
    public void handleNewAttackPhase(AttackPhase attackPhase) {
        gameStatus.setText(game.getState().toString());
        boardView.getAttackPhaseButton().setEnabled(false);
        boardView.getAttackButton().setEnabled(false);
        boardView.getFortifyPhaseButton().setEnabled(true);
        boardView.getEndTurnButton().setEnabled(true);
        boardView.setUpAttackListeners();
        boardView.setupCountryListeners(country -> new AttackFromController(this, country, attackPhase));
    }

    /**
     * updates the view when the user is ready to choose a country to fortify to
     *
     * @param fortifyPhase
     */
    @Override
    public void handleNewFortify(FortifyPhase fortifyPhase) {
        boardView.getFortifyButton().setEnabled(false);
        boardView.setupCountryListeners(country -> new FortifyToController(this, game, country, fortifyPhase));
    }

    /**
     * clears all the listeners of buttons
     */
    @Override
    public void clearCountryButtons() {
        boardView.clearCountryListeners();
    }

    /**
     * handles when a custom map is loaded
     *
     * @param game
     * @param board
     */
    @Override
    public void handleLoadMap(Game game, Board board) {
        this.game = game;
    }

    /**
     * response of the view when the user uploads an invalid map
     */
    @Override
    public void handleInvalidMap() {
        JOptionPane.showMessageDialog(this, "Invalid Map !, try again...");
    }

    /**
     * updates the view after the user uploads a saved game
     *
     * @param board of the saved game
     * @param game  saved game
     */
    @Override
    public void handleLoadSavedGame(Board board, Game game) {
        this.game = game;
        this.remove(mainMenuPanel);
        boardView = new BoardView(this, game, board);
        this.add(boardView, BorderLayout.CENTER);
        this.add(gameStatusPanel, BorderLayout.SOUTH);
        menu.remove(loadCustomMap);
        menu.add(helpMenuItem);
        menu.add(save);
        save.addActionListener(new SaveController(game));
        gameStatus.setText(game.getState().toString());
        currentPlayer.setText(game.getCurrentPlayer().toString());
        boardView.InitializeBoard(game.getNumPlayers());
        boardView.addInGamePanel(game, game.getCurrentPlayer());
    }
}