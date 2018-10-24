package com.robertson.domain;
import java.util.Random;

/*
    Parking Ticket class is used to represent a parking ticket issued by an officer for a parked car that has gone
    over the paid time while parked.
 */
public class ParkingTicket {
    private final double BASE_FINE = 25.0;
    private final double HOURLY_FINE = 10.0;
    private int timeOver;
    private int id;
    private ParkedCar offendingVehicle;
    private PoliceOfficer issuingOfficer;
    private double totalFine;

    // Constructor -- Not offering a default constructor because the only reason you need to make a new ParkingTicket
    // would be if the car is actually and offender and you would need all the constructor parameters.
    public ParkingTicket(ParkedCar offendingVehicle, PoliceOfficer issuingOfficer, int timeOver){
        this.id = generateNewId();
        this.offendingVehicle = offendingVehicle;
        this.issuingOfficer = issuingOfficer;
        this.timeOver = timeOver;
        this.totalFine = calculateTotalFine();
    }

    // Create a table for the parking ticket data and return it as a String.
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("+-----------------+--------------------+%n"));
        sb.append(row("Ticket #", this.id));
        sb.append(row("Badge #", issuingOfficer.getBadgeNumber()));
        sb.append(row("Office Name", issuingOfficer.getName()));
        sb.append(row("License", offendingVehicle.getLicenseNumber()));
        sb.append(row("Make", offendingVehicle.getMake()));
        sb.append(row("Model", offendingVehicle.getModel()));
        sb.append(row("Color", offendingVehicle.getColor()));
        sb.append(row("Total Fine", this.totalFine));
        sb.append(String.format("+-----------------+--------------------+%n"));
        return sb.toString();
    }

    // Generating a random parking ticket number from an IntStream.
    // This would not work properly in real life because you could have duplicate ticket numbers, but it works fine
    // for demonstration.
    private int generateNewId(){
        Random random = new Random();
        return random.ints(111111111,999999999)
                .findAny()
                .getAsInt();
    }

    // Return the base fine if under an hour or calculate to the total fine if over 1 hour.
    private double calculateTotalFine() {
        return (timeOver <= 60) ? BASE_FINE : calcHourlyFine();
    }

    // Determines how many hours to charge, less the first hour and calculates the total fine
    private double calcHourlyFine(){
        // Extra hours is the amount of hours that will be charged the HOURLY_FEE of $10
        double extraHours = timeOver / 60.0 - 1;
        // Round up to the next whole number if time is a fractional part of the hour.
        extraHours = (extraHours > Math.floor(extraHours)) ? Math.ceil(extraHours) : extraHours;
        return (extraHours * HOURLY_FINE) + BASE_FINE;
    }

    // Methods used to create rows of the table that is returned from the toString() method.
    private String row(String key, String value){
        return String.format("| %-15s | %-18s |%n", key, value);
    }
    private String row(String key, int value){
        return String.format("| %-15s | %-18d |%n", key, value);
    }
    private String row(String key, double value){
        return String.format("| %-15s | %-18.2f |%n", key, value);
    }

    // Getters
    public int getId(){
        return id;
    }
    public String getOfficerName(){
        return issuingOfficer.getName();
    }
    public int getOfficerBadgeNumber(){
        return issuingOfficer.getBadgeNumber();
    }
    public String getCarLicense(){
        return offendingVehicle.getLicenseNumber();
    }
    public String getCarMake(){
        return offendingVehicle.getMake();
    }
    public String getCarModel(){
        return offendingVehicle.getModel();
    }
    public String getCarColor(){
        return offendingVehicle.getColor();
    }
    public double getTotalFine() {
        return totalFine;
    }
}
