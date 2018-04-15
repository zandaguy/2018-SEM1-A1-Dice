package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GameEngineImpl implements GameEngine{

    private Map<String, Player> players = new HashMap<>();
    private List<GameEngineCallback> callbacks = new ArrayList<>();

    @Override
    public boolean placeBet(Player player, int bet) {
        return player.placeBet(bet);
    }

    @Override
    public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {

        DicePair dice = rollDice(player, initialDelay, finalDelay, delayIncrement);

        // Update player with dice results for retrieval later
        player.setRollResult(dice);

        for (GameEngineCallback callback: callbacks) {
            callback.result(player, dice, this);
        }
    }

    @Override
    public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {

        DicePair dice = rollDice(null, initialDelay, finalDelay, delayIncrement);

        // Adjust points according to outcomes of rollDice()
        resolveBets(dice);

        for (GameEngineCallback callback: callbacks) {
            callback.houseResult(dice, this);
        }

    }

    /* Pauses thread execution for 'delay' milliseconds */
    private static void delayBy(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e){
            // Can ignore this exception
            Thread.currentThread().interrupt();
        }
    }

    /*
    Simulates dice rolls and logs each dice face total. Uses player callback methods if a player is entered,
    uses the house callback methods if null is entered for Player.
     */
    private DicePair rollDice(Player player, int initialDelay, int finalDelay, int delayIncrement) {

        DicePair dice;

        // Start at initialDelay and increment the delay by delayIncrement until it is >= to finalDelay
        // Retrieve a dice pair value at each increment and log it
        for(int delay = initialDelay; delay < finalDelay; delay +=delayIncrement) {


            dice = new DicePairImpl(randomDiceValue(NUM_FACES),
                    randomDiceValue(NUM_FACES), NUM_FACES);

            for(GameEngineCallback callback: callbacks) {
                if(player != null) {
                    callback.intermediateResult(player, dice, this);
                } else {
                    callback.intermediateHouseResult(dice, this);
                }
            }

            // Delay by 'delay' ms each time a new number is shown on the dice. I assumed 'initialDelay' was the delay
            // between the first two DicePair rolls and not the delay before the first roll. Otherwise there would
            // be no delay before the result values.

            delayBy(delay);
        }

        // Roll dice to return final result to update player or resolve bets
        return new DicePairImpl(randomDiceValue(NUM_FACES), randomDiceValue(NUM_FACES), NUM_FACES);
    }

    /*
    Checks all player dice results against house results and updates player points accordingly.
     */
    private void resolveBets(DicePair houseDice) {
        int houseResult = houseDice.getDice1() + houseDice.getDice2();

        for (Player player: getAllPlayers()) {
            int playerResult = player.getRollResult().getDice1() +
                    player.getRollResult().getDice2();

            if(playerResult < houseResult) {
                // House wins, player loses bet
                player.setPoints(player.getPoints() - player.getBet());
            } else if(playerResult > houseResult){
                // Player wins, receives bet as points
                player.setPoints(player.getPoints() + player.getBet());
            } else {
                //A draw, player points remain unchanged
            }
            // Clear all bets
            player.placeBet(0);
        }
    }

    /*
    Returns a pseudo random int between 1 (inclusive) and numfaces+1 (exclusive).
    */
    private static int randomDiceValue(int numfaces) {
        return ThreadLocalRandom.current().nextInt(1, numfaces+1);
    }

    @Override
    public void addPlayer(Player player) {
        players.put(player.getPlayerId(), player);
    }

    @Override
    public Player getPlayer(String id) {
        return players.get(id);
    }

    @Override
    public boolean removePlayer(Player player) {
        return players.remove(player.getPlayerId(), player);
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        callbacks.add(gameEngineCallback);
    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        return callbacks.remove(gameEngineCallback);
    }

    @Override
    public Collection<Player> getAllPlayers() {
        // values() returns a collection view of the values contained in this map
        return Collections.unmodifiableCollection(players.values());
    }

}
