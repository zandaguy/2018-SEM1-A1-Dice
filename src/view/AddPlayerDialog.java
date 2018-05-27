package view;

import controller.ConfirmListener;
import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.event.*;

public class AddPlayerDialog extends JDialog {
    private JTextField textField1 = new JTextField(10);
    private JTextField textField2 = new JTextField(10);
    private JButton confirmBtn = new JButton("Confirm");

    public AddPlayerDialog(GameEngine gameEngine, JPanel infoPanel) {
        setModal(true);
        setTitle("Add Player");
        JPanel panel = new JPanel();
        panel.add(new JLabel("Player name: "));
        panel.add(textField1);
        panel.add(new JLabel("Player balance: "));
        panel.add(textField2);
        panel.add(confirmBtn);

        add(panel);
        pack();
        setLocationRelativeTo(infoPanel);

        ActionListener confirmListener = new ConfirmListener(gameEngine, this);
        confirmBtn.addActionListener(confirmListener); // add listener
        textField1.addActionListener(confirmListener );
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JButton getConfirmBtn() {
        return confirmBtn;
    }
}
