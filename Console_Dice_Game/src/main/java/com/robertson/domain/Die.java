package com.robertson.domain;

import com.robertson.domain.interfaces.IRollable;

import java.util.Random;

/*
    Purpose of the Die class is to represent a single die in that a player of the game will use to roll
 */
public class Die implements IRollable {

    private int lastRolledValue;

    // Constructor
    public Die(){
        this.lastRolledValue = 0;
    }

    // Generate a random value for the rolling of the die
    @Override
    public void roll() {
        Random random = new Random();
        this.lastRolledValue = random.nextInt(5) + 1;
    }

    // Getters
    @Override
    public int getLastRolledValue() {
        return this.lastRolledValue;
    }
}
