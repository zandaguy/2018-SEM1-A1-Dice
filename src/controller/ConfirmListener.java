package controller;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.AddPlayerDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ConfirmListener implements ActionListener {

    private AddPlayerDialog addPlayerDialog;
    private GameEngine gameEngine;

    public ConfirmListener(GameEngine gameEngine, AddPlayerDialog addPlayerDialog) {
        this.addPlayerDialog = addPlayerDialog;
        this.gameEngine = gameEngine;
    }
    public void actionPerformed(ActionEvent e) {
        String text = addPlayerDialog.getTextField2().getText();
        if (isTextValid(text)) {
            gameEngine.addPlayer(new SimplePlayer(String.valueOf(gameEngine.getAllPlayers().size()+1),
                addPlayerDialog.getTextField1().getText(), Integer.parseInt(addPlayerDialog.getTextField2().getText())));

            addPlayerDialog.getTextField1().setText("");
            addPlayerDialog.getTextField2().setText("");
            addPlayerDialog.getTextField2().requestFocusInWindow();
            addPlayerDialog.setVisible(false);
        } else {
            // show warning
            String warning = "Data entered, \"" + text + "\", is invalid. Please enter a positive integer";
            JOptionPane.showMessageDialog(addPlayerDialog.getConfirmBtn(), warning, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            addPlayerDialog.getTextField2().setText("");
            addPlayerDialog.getTextField2().requestFocusInWindow();
        }
    }

    private boolean isTextValid(String text) {
        try {
            if(isInteger(text, 10)) {
                if(Integer.parseInt(text) > 0)
                return true;
            }
        } catch (NumberFormatException e) {
            // one of the few times it's OK to ignore an exception
        }
        return false;
    }

    public void setTextField(JTextField textField) {
        //this.textField = textField;
    }

    private static boolean isInteger(String s, int radix) {
        Scanner sc = new Scanner(s.trim());
        if(!sc.hasNextInt(radix)) return false;
        // we know it starts with a valid int, now make sure
        // there's nothing left!
        sc.nextInt(radix);
        return !sc.hasNext();
    }
}
