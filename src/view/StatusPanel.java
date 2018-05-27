package view;

import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    public static final int PLAYER_ADDED = 0;
    public static final int BET_MADE = 1;

    private JLabel status = new JLabel("Waiting for players...");

    StatusPanel(GameEngine gameEngine) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints gbc = new GridBagConstraints();

        status.setFont(new Font("Mono", Font.BOLD,20));


        add(status, gbc);

    }

    public void updateStatus(int statusNum) {
        if(statusNum == 0)
        this.status.setText("Make your bets and roll...");
    }
}
