package view;

import controller.PlayersPanelListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;


public class PlayersPanel extends JPanel {
    private JButton showDialogBtn = new JButton("Add Player");
    private AddPlayerDialog addPlayerDialog;
    private JList players;
    private DefaultListModel model = new DefaultListModel();

    public PlayersPanel(GameEngine gameEngine, AppFrame appFrame) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        addPlayerDialog = new AddPlayerDialog(gameEngine, this);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        players = new JList(model);
        players.setVisibleRowCount(10);
        players.setMinimumSize(new Dimension(200, 200));
        players.setPreferredSize(new Dimension(200, 200));

        JScrollPane scrollPane = new JScrollPane(players);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(scrollPane, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(showDialogBtn, gbc);

        PlayersPanelListener playersPanelListener = new PlayersPanelListener(gameEngine, addPlayerDialog, appFrame, this);
        showDialogBtn.addActionListener(playersPanelListener);
        players.addListSelectionListener(playersPanelListener);
    }

    public JList getPlayers() {
        return players;
    }

}
