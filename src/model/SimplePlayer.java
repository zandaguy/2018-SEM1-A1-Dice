package model;

import model.interfaces.Player;
import model.interfaces.DicePair;

public class SimplePlayer implements Player{

    private String playerName;
    private String playerId;
    private int points;
    private int bet;
    private DicePair dicePair;

    public SimplePlayer(String playerId, String playerName, int points) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = points;
        bet = 0;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String getPlayerId() {
        return playerId;
    }

    /* Place a bet only if the player has enough points to do so. Otherwise return false. */
    @Override
    public boolean placeBet(int bet) {
        if(bet <= points) {
            this.bet = bet;
            return true;
        } else {
            this.bet = 0;
            return false;
        }
    }

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public DicePair getRollResult() {
        if(dicePair == null) {
            throw (new NullPointerException("Player dicePair returns null. Must be set before being accessed."));
        }
        return dicePair;
    }

    @Override
    public void setRollResult(DicePair rollResult) {
        this.dicePair = rollResult;
    }

    @Override
    public String toString() {
        return String.format("Player: id=%s, name=%s, points=%d", playerId, playerName, points);
    }
}
