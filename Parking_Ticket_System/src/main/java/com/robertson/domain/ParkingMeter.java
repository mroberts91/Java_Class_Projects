package com.robertson.domain;

/*
    ParkingMeter class is just a basic class to represent the amount of time a parked car has paid to park/
 */
public class ParkingMeter {
    private int minutesPurchased;

    // Constructor
    public ParkingMeter(int minutesPurchased){
        this.minutesPurchased = minutesPurchased;
    }

    // Getters
    public int getMinutesPurchased() {
        return minutesPurchased;
    }

}
