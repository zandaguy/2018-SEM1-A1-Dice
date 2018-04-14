package model;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private Logger logger = Logger.getLogger("assignment1");

	public GameEngineCallbackImpl()
	{
	    // TODO Check if this is correct
		Logger.getGlobal().getParent().getHandlers()[0].setLevel(Level.FINE);

		// FINE shows rolling output, INFO only shows result
		logger.setLevel(Level.INFO);
	}

	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
	{
		// intermediate results logged at Level.FINE
		displayRoll("ROLLING", Level.FINE, player.getPlayerName(), dicePair.toString());
		// TO DO: complete this method to log results
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine)
	{
		// final results logged at Level.INFO
		displayRoll("*RESULT*", Level.INFO, player.getPlayerName(), result.toString());
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		displayRoll("ROLLING", Level.FINE, "House", dicePair.toString());
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		displayRoll("*RESULT*", Level.INFO, "House", result.toString());
		displayResult(gameEngine.getAllPlayers());
	}

	private void displayRoll(String rollType, Level level, String roller,
								String diceResults) {
		logger.log(level, String.format("%s: %s %s", roller, rollType,
				diceResults));
	}

	private void displayResult(Collection<Player> players) {
		for (Player player: players) {
			logger.log(Level.INFO, String.format("%s", player.toString()));
		}
	}
}
