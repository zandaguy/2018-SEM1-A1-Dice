
package view;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import view.AppFrame;

import javax.swing.*;


public class GameEngineCallbackGUI implements GameEngineCallback {

    /* The AWT/SWING interface requires the following functionality:
        -Add players
        -Place a bet
        -Player rolls dice (per player) .. realtime
        -House rolls dice .. realtime
        -Display results including updated player balances after house roll
    */

    private AppFrame appFrame;

    public GameEngineCallbackGUI(GameEngine gameEngine) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                appFrame = new AppFrame(gameEngine);
            }
        });
    }

    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {
        appFrame.getDicePanel().updatePlayer(dicePair);
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine) {
        appFrame.getDicePanel().updatePlayer(result);
    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
        appFrame.getDicePanel().updateHouse(dicePair);
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {
        appFrame.getDicePanel().updateHouse(result);
    }
}

