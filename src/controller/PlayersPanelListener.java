package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class PlayersPanelListener implements ActionListener, ListSelectionListener{

    private AddPlayerDialog addPlayerDialog;
    private GameEngine gameEngine;
    private PlayersPanel playersPanel;
    private AppFrame appFrame;

    public PlayersPanelListener(GameEngine gameEngine, AddPlayerDialog addPlayerDialog, AppFrame appFrame, PlayersPanel playersPanel) {
        this.addPlayerDialog = addPlayerDialog;
        this.gameEngine = gameEngine;
        this.appFrame =appFrame;
        this.playersPanel = playersPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!addPlayerDialog.isVisible()) {

            playersPanel.getPlayers().clearSelection();
            addPlayerDialog.setVisible(true);

            //TODO Prevent addition of identical players
            // Update JList with new player (if one was added)
            addToList(gameEngine.getAllPlayers());

            // Set JList selected player to the newly added player -> sets as the current active player
            playersPanel.getPlayers().setSelectedIndex(playersPanel.getPlayers().getModel().getSize()-1);
            appFrame.getStatusPanel().updateStatus(StatusPanel.PLAYER_ADDED);

        }
    }
    private void addToList(Collection players) {

        String[] playerArray = new String[players.size()];
        int i=0;

        for (Object player: players) {
            playerArray[i] = ((Player)player).getPlayerId() + ": " + ((Player)player).getPlayerName();
            i++;
        }
        playersPanel.getPlayers().setListData(playerArray);
        appFrame.getPlayerPanel().setVisible(true);
    }
    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(!(playersPanel.getPlayers().isSelectionEmpty())) {
            String[] activePlayer = split(playersPanel.getPlayers().getSelectedValue().toString());
            appFrame.setActivePlayer(new ActivePlayer(activePlayer[0], activePlayer[1]));
            int balance = gameEngine.getPlayer(appFrame.getActivePlayer().getPlayerId()).getPoints();
            appFrame.getPlayerPanel().update(balance);
            appFrame.getPlayerPanel().setCurrentPlayer(appFrame.getActivePlayer().getActivePlayer());
        }
    }

    private String[] split(String activePlayer) {
        return activePlayer.split(": ");
    }
}
