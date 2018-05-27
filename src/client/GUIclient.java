package client;

import model.interfaces.GameEngineCallback;
import view.GameEngineCallbackGUI;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackImpl;

public class GUIclient {

    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngineImpl();

        gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameEngine));
        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
    }
}
