package client;

import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;


public class MyTestClient1
{
	public static void main(String args[])
	{
		// instantiate the GameEngine so we can make calls
		final GameEngine gameEngine = new GameEngineImpl();

		// create two test players (NOTE: you will need to implement the 3 arg contructor in SimplePlayer)
		Player[] players = new Player[]
		{ new SimplePlayer("1", "The Roller", 1000),
				new SimplePlayer("2", "The Loser", 500),
				new SimplePlayer("3", "The Stranger", 50)};

		// register the callback for notifications (all logging output is done by GameEngineCallbackImpl)
		// see provided skeleton class GameEngineCallbackImpl.java
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

		// main loop to add players place a bet and roll
		for (Player player : players)
		{
			gameEngine.addPlayer(player);
		}

		for (Player player : gameEngine.getAllPlayers()) {
			gameEngine.placeBet(player, 100);
			gameEngine.rollPlayer(player, 1, 3000, 1000);
		}

		// all players have rolled so now house rolls (GameEngineCallBack is
		// called) and results are calculated
		gameEngine.rollHouse(1, 3000, 1000);

		gameEngine.removePlayer(players[2]);

		for (Player player : gameEngine.getAllPlayers())
		{
			gameEngine.placeBet(player, 60);
			gameEngine.rollPlayer(player, 1, 100, 20);
		}

		gameEngine.rollHouse(1, 100, 20);

	}
}
