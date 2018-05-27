package controller;


import model.interfaces.GameEngine;
import view.AppFrame;
import view.InputPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollHouseListener implements ActionListener {

    private GameEngine gameEngine;
    private InputPanel inputPanel;
    private AppFrame appFrame;

    public RollHouseListener(GameEngine gameEngine, InputPanel inputPanel, AppFrame appFrame) {
        this.gameEngine = gameEngine;
        this.inputPanel = inputPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new Thread()
        {
            @Override
            public void run()
            {
                gameEngine.rollHouse(1, 1000, 200);
                inputPanel.displayHouseRoll(false);
                inputPanel.update(gameEngine.getPlayer(appFrame.getActivePlayer().getPlayerId()).getPoints());
            }
        }.start();
    }
}
