package view;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DicePanel extends JPanel {

    private ArrayList<Image> images = new ArrayList();
    private JLabel die1;
    private JLabel die2;
    private JLabel houseDie1;
    private JLabel houseDie2;

    public DicePanel(GameEngine gameEngine) {

        setLayout(new GridLayout(1, 2));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        try
        {
            images.add(0, getScaledImage(ImageIO.read(new File("dice1.png")), 240, 240));
            images.add(1, getScaledImage(ImageIO.read(new File("dice2.png")), 240, 240));
            images.add(2, getScaledImage(ImageIO.read(new File("dice3.png")), 240, 240));
            images.add(3, getScaledImage(ImageIO.read(new File("dice4.png")), 240, 240));
            images.add(4, getScaledImage(ImageIO.read(new File("dice5.png")), 240, 240));
            images.add(5, getScaledImage(ImageIO.read(new File("dice6.png")), 240, 240));

        } catch (IOException e) {
            e.printStackTrace();
        }

            die1 = new JLabel(new ImageIcon(images.get(0)));
            die2 = new JLabel(new ImageIcon(images.get(0)));
            houseDie1 = new JLabel(new ImageIcon(images.get(0)));
            houseDie2 = new JLabel(new ImageIcon(images.get(0)));

            JPanel playerDicePanel = new JPanel();
            playerDicePanel.setLayout(new GridLayout(2, 1));
            playerDicePanel.add(die1);
            playerDicePanel.add(die2);
            playerDicePanel.setBackground(Color.WHITE);
            add(playerDicePanel);

            JPanel houseDicePanel = new JPanel();
            houseDicePanel.setLayout(new GridLayout(2, 1));
            houseDicePanel.add(houseDie1);
            houseDicePanel.add(houseDie2);
            houseDicePanel.setBackground(Color.LIGHT_GRAY);
            add(houseDicePanel);


    }

    private BufferedImage getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public void updatePlayer(DicePair dicePair) {
        die1.setIcon(new ImageIcon(images.get(dicePair.getDice1()-1)));
        die2.setIcon(new ImageIcon(images.get(dicePair.getDice2()-1)));
    }

    public void updateHouse(DicePair dicePair) {
        houseDie1.setIcon(new ImageIcon(images.get(dicePair.getDice1()-1)));
        houseDie2.setIcon(new ImageIcon(images.get(dicePair.getDice2()-1)));
    }

}