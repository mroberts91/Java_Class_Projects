package com.robertson.domain;

/*
    PoliceOfficer class is used to represent an officer that is checking if a parked car is over on the time paid on the
    meter and also to issue a ticket if the car is over the time.
 */
public class PoliceOfficer {
    private String name;
    private int badgeNumber;
    private ParkedCar parkedCar;
    private ParkingMeter parkingMeter;

    // Constructor
    public PoliceOfficer(String name, int badgeNumber, ParkedCar parkedCar, ParkingMeter parkingMeter){
        this.name = name;
        this.badgeNumber = badgeNumber;
        this.parkedCar = parkedCar;
        this.parkingMeter = parkingMeter;
    }

    // Checks if the parked car is actually over the time paid and breaking the law.
    public boolean checkForOffender(){
        return parkedCar.getMinutesParked() > parkingMeter.getMinutesPurchased();
    }

    // Calculates how much time over the car is and tells the ticketing manager to create a new parking ticked with
    // the supplied information.
    public boolean issueParkingTicket(){
        int timeOverPaidTime = parkedCar.getMinutesParked() - parkingMeter.getMinutesPurchased();
        return ParkingTicketManager.createNewParkingTicket(
                this.parkedCar, this, timeOverPaidTime);
    }
    // Getters
    public String getName() {
        return name;
    }
    public int getBadgeNumber() {
        return badgeNumber;
    }
}
