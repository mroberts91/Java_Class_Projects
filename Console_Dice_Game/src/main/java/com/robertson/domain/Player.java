package com.robertson.domain;

import com.robertson.domain.interfaces.IPlayer;
import com.robertson.domain.interfaces.IRollable;

/*
    Purpose of the Player class is to represent a player of the First to One game, each player has their rollable object
    that they use during each game round.
 */
public class Player implements IPlayer {

    private String name;
    private int points;
    private IRollable die;

    // Constructor
    public Player(String name, IRollable die){
        this.name = name;
        this.points = 50;
        this.die = die;
    }

    // Player roles their die and then sets their new points value after the roll.
    @Override
    public void roll(){
        int rollValue;
        int potentialPointValue;
        die.roll();
        rollValue  = die.getLastRolledValue();
        potentialPointValue = this.points - rollValue;
        if (potentialPointValue < 1){
            this.points += rollValue;
        } else {
            this.points -= rollValue;
        }
    }

    // Getters
    @Override
    public int getPoints(){
        return this.points;
    }
    @Override
    public String getName() {
        return this.name;
    }

    // Setters
    protected void setPoints(int num){
        this.points = num;
    }
}
