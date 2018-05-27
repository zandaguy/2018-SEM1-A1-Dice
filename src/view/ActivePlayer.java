package view;

import java.util.Observable;

public class ActivePlayer {

    private String activePlayer;
    private String playerId;

    public ActivePlayer(String id, String name) {
        this.playerId = id;
        this.activePlayer = name;
    }

    public String getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(String activePlayer) {
        this.activePlayer = activePlayer;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

}
