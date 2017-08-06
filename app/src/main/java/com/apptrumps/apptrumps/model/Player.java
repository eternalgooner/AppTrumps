package com.apptrumps.apptrumps.model;

import java.util.LinkedList;

/**
 * Created by David on 06-Aug-17.
 */

public class Player {

    private String name;
    private String id;
    private LinkedList<Card> cards;
    private boolean hasTopTrump;
    private boolean isTheirTurn;
    private boolean isWinner;
    private boolean isConnected;

    public Player(String name, String id, LinkedList<Card> cards, boolean hasTopTrump, boolean isTheirTurn, boolean isWinner, boolean isConnected) {
        this.name = name;
        this.id = id;
        this.cards = cards;
        this.hasTopTrump = hasTopTrump;
        this.isTheirTurn = isTheirTurn;
        this.isWinner = isWinner;
        this.isConnected = isConnected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<Card> getCards() {
        return cards;
    }

    public void setCards(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public boolean isHasTopTrump() {
        return hasTopTrump;
    }

    public void setHasTopTrump(boolean hasTopTrump) {
        this.hasTopTrump = hasTopTrump;
    }

    public boolean isTheirTurn() {
        return isTheirTurn;
    }

    public void setTheirTurn(boolean theirTurn) {
        isTheirTurn = theirTurn;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
