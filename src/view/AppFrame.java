package view;

import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    private GameEngine gameEngine;
    private PlayersPanel playersPanel;
    private DicePanel dicePanel;
    private InputPanel playerPanel;
    private StatusPanel statusPanel;
    private AddPlayerDialog addPlayerDialog;

    private ActivePlayer activePlayer = new ActivePlayer("1", "");

    public AppFrame(GameEngine gameEngine) {

        super("Dice Game");
        setMinimumSize(new Dimension(800, 450));

        setBounds(100, 100, 1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create Swing Components
        dicePanel = new DicePanel(gameEngine);
        playerPanel = new InputPanel(gameEngine, this);
        statusPanel = new StatusPanel(gameEngine);
        playersPanel = new PlayersPanel(gameEngine, this);

        // Add Swing components to content pane

        gbc.weighty = 0.5;
        gbc.insets = new Insets(1, 1, 1 ,1);


        // Row 1, column 1
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        add(playersPanel, gbc);

        // Row 1, column 2
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        add(statusPanel, gbc);


        // Row 2, column 1
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.WEST;
        add(playerPanel, gbc);

        // Row 2, column 2
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        add(dicePanel, gbc);
    }


    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public PlayersPanel getPlayersPanel() {
        return playersPanel;
    }

    public DicePanel getDicePanel() {
        return dicePanel;
    }

    public InputPanel getPlayerPanel() {
        return playerPanel;
    }

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }

    public AddPlayerDialog getAddPlayerDialog() {
        return addPlayerDialog;
    }

    public ActivePlayer getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(ActivePlayer activePlayer) {
        this.activePlayer = activePlayer;
    }

}
