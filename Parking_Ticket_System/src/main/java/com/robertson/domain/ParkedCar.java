package com.robertson.domain;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*
    ParkedCar is used to represent a car that is parked at a parking meter and the time that the car parked.
 */
public class ParkedCar {
    private String make;
    private String model;
    private String color;
    private String licenseNumber;
    private Time timeOfPark;

    // Default Constructor
    public ParkedCar(){ }

    // Overloaded constructor that initializes all the the fields.
    public ParkedCar(String make, String model, String color, String licenseNumber){
        this.make = make;
        this.model = model;
        this.color = color;
        this.licenseNumber = licenseNumber;
    }

    // Getters
    public Time getTimeOfPark(){
        return timeOfPark;
    }
    public int getMinutesParked() {
        long currentTime = new Date().getTime();
        return  (int)TimeUnit.MILLISECONDS.toMinutes((currentTime - timeOfPark.getTime()));

    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public String getColor() {
        return color;
    }
    public String getModel() {
        return model;
    }
    public String getMake() {
        return make;
    }

    // Setters
    public void setTimeOfPark(long time){
        timeOfPark = new Time(time);
    }
}
