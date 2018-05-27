package view;

import controller.InputPanelListener;
import controller.PlaceBetListener;
import controller.RollHouseListener;
import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel{

    private GameEngine gameEngine;
    private JLabel currentBalance;
    private ActivePlayer activePlayer;
    private AppFrame appFrame;
    private JLabel currentPlayer;
    private JTextField textPlaceBet;
    private JButton buttonRollHouse;
    private JButton buttonRollPlayer;

    public InputPanel(GameEngine gameEngine, AppFrame appFrame) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.appFrame = appFrame;
        this.gameEngine = gameEngine;
        setLayout(new GridBagLayout());
        setVisible(false);

        GridBagConstraints gbc = new GridBagConstraints();

        // Could potentially open dialogue box to enter number

        JLabel betLabel = new JLabel("Bet amount: ");
        JButton buttonPlaceBet = new JButton("Place a bet");
        buttonRollPlayer = new JButton("Roll the dice!");
        textPlaceBet = new JTextField(10);
        currentPlayer = new JLabel("Player");
        currentPlayer.setFont(new Font("Serif", Font.BOLD, 40));

        JLabel balance = new JLabel("Balance:");
        currentBalance = new JLabel("");
        buttonRollHouse = new JButton(("Roll house!"));
        buttonRollHouse.setVisible(false);

        gbc.ipady = 15;
        gbc.ipadx = 15;
        gbc.insets = new Insets(10,10,10,10);

        //Column 1, row 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(currentPlayer, gbc);

        // Column 1, row 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(balance, gbc);

        // Column 2, row 2
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(currentBalance,gbc);

        // Column 1, row 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(betLabel, gbc);

        // Column 2, row 3
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(textPlaceBet, gbc);

        // Column 3, row 3
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(buttonPlaceBet, gbc);

        // Column 2, row 4
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(buttonRollPlayer, gbc);

        // Column 3, row 4
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(buttonRollHouse, gbc);

        InputPanelListener inputPanelListener = new InputPanelListener(gameEngine, this, appFrame);
        buttonRollPlayer.addActionListener(inputPanelListener);
        buttonPlaceBet.addActionListener(new PlaceBetListener(gameEngine, this, appFrame));
        buttonRollHouse.addActionListener(new RollHouseListener(gameEngine, this, appFrame));

    }

    public void update(ActivePlayer activePlayer) {
        System.out.println(activePlayer.getPlayerId());
        setActivePlayer(activePlayer);

        int balance = gameEngine.getPlayer(this.activePlayer.getPlayerId()).getPoints();
        currentBalance.setText(String.valueOf(balance));
    }

    public void displayHouseRoll(boolean bool) {
        buttonRollHouse.setVisible(bool);
    }

    public void displayRollButton(boolean bool) {
        buttonRollPlayer.setVisible(bool);
    }

    public void update(int balance) {
        currentBalance.setText(String.valueOf(balance));
    }

    public void setActivePlayer(ActivePlayer activePlayer) {
        this.activePlayer = activePlayer;
    }

    public ActivePlayer getActivePlayer() {
        return activePlayer;
    }

    public JTextField getTextPlaceBet() {
        return textPlaceBet;
    }

    public void setCurrentPlayer(String playerName) {
        this.currentPlayer.setText(playerName);
    }
}
