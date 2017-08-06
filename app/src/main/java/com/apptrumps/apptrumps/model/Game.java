package com.apptrumps.apptrumps.model;

import java.util.LinkedHashMap;

/**
 * Created by David on 06-Aug-17.
 */

public class Game {

    private LinkedHashMap<String, Player> gamePlayers;
    private boolean isOver;

    public Game(LinkedHashMap<String, Player> gamePlayers, boolean isOver) {
        this.gamePlayers = gamePlayers;
        this.isOver = isOver;
    }

    public LinkedHashMap<String, Player> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(LinkedHashMap<String, Player> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }
}
