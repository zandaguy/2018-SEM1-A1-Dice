package controller;

import model.interfaces.GameEngine;
import view.AppFrame;
import view.InputPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class PlaceBetListener implements ActionListener {

    private GameEngine gameEngine;
    private InputPanel inputPanel;
    private AppFrame appFrame;

    public PlaceBetListener(GameEngine gameEngine, InputPanel inputPanel, AppFrame appFrame) {
        this.gameEngine = gameEngine;
        this.inputPanel = inputPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String bet = inputPanel.getTextPlaceBet().getText();
        if(isInteger(bet, 10)) {
            gameEngine.placeBet(gameEngine.getPlayer(appFrame.getActivePlayer().getPlayerId()), Integer.parseInt(bet));
        } else {
            JOptionPane.showMessageDialog(inputPanel, "Please enter a positive integer");
        }
    }

     public static boolean isInteger(String s, int radix) {
        Scanner sc = new Scanner(s.trim());
        if(!sc.hasNextInt(radix)) return false;
        // we know it starts with a valid int, now make sure
        // there's nothing left!
        sc.nextInt(radix);
        return !sc.hasNext();
    }

}
