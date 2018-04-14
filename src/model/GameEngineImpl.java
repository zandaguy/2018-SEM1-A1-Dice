package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GameEngineImpl implements GameEngine{

    private Map<String, Player> players = new HashMap<>();
    private ArrayList<GameEngineCallback> callbacks = new ArrayList<>();

    //TODO Finish placebet in SimplePlayer
    @Override
    public boolean placeBet(Player player, int bet) {
        return player.placeBet(bet);
    }

    //TODO Add delay too rollPlayer
    @Override
    public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
        // Start at initialDelay and increment by delayIncrement until finalDelay
        // Retrieve a dice pair value at each increment
        // TODO Create helper function for this loop
        for(int i = initialDelay; i <= finalDelay; i+=delayIncrement) {
            DicePair dice = new DicePairImpl(randomDiceValue(NUM_FACES),
                    randomDiceValue(NUM_FACES), NUM_FACES);
            for(GameEngineCallback callback: callbacks) {
                callback.intermediateResult(player, dice, this);
            }
        }

        // Roll dice to generate dice pair value
        DicePair dice = new DicePairImpl(randomDiceValue(NUM_FACES),
                randomDiceValue(NUM_FACES), NUM_FACES);

        // Update player with dice results for retrieval later
        player.setRollResult(dice);

        for (GameEngineCallback callback: callbacks) {
            callback.result(player, dice, this);
        }
    }

    // returns a pseudo random int between 1 (inclusive) and numfaces+1 (exclusive).
    private static int randomDiceValue(int numfaces) {
        return ThreadLocalRandom.current().nextInt(1, numfaces+1);
    }

    //TODO Implement rollHouse function
    @Override
    public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {

        for(int i = initialDelay; i <= finalDelay; i+=delayIncrement) {
            DicePair dice = new DicePairImpl(randomDiceValue(NUM_FACES),
                    randomDiceValue(NUM_FACES), NUM_FACES);
            for(GameEngineCallback callback: callbacks) {
                callback.intermediateHouseResult(dice, this);
            }
        }

        DicePair dice = new DicePairImpl(randomDiceValue(NUM_FACES),
                randomDiceValue(NUM_FACES), NUM_FACES);

        resolveBets(dice);
        for (GameEngineCallback callback: callbacks) {
            callback.houseResult(dice, this);
        }

    }

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
        }
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

    //TODO Implement addGameEngineCallback, create necessary date structures.
    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        callbacks.add(gameEngineCallback);
    }

    //TODO Implement removeGameEngineCallback
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
