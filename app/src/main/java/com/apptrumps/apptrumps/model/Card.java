package com.apptrumps.apptrumps.model;

import java.io.Serializable;

/**
 * Created by David on 30-Jul-17.
 */

public class Card implements Serializable{
    private int id;
    private String name;
    private int height;
    private int weapons;
    private int humour;
    private int intelligence;
    private int athleticism;
    private boolean isTopTrump;
    private String info;

    public Card(int id, String name, int height, int weapons, int humour, int intelligence, int athleticism, boolean isTopTrump, String info) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weapons = weapons;
        this.humour = humour;
        this.intelligence = intelligence;
        this.athleticism = athleticism;
        this.isTopTrump = isTopTrump;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeapons() {
        return weapons;
    }

    public int getHumour() {
        return humour;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAthleticism() {
        return athleticism;
    }

    public boolean isTopTrump() {
        return isTopTrump;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weapons=" + weapons +
                ", humour=" + humour +
                ", intelligence=" + intelligence +
                ", athleticism=" + athleticism +
                ", isTopTrump=" + isTopTrump +
                ", info='" + info + '\'' +
                '}';
    }
}
