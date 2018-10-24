/*
    Michael Robertson
    IST-217 -- Fall 2018
    Robertson_8_8
    The purpose of the this program is to demonstrate how classes work together, in this instance, in issuing a
    parking ticket to a vehicle parked longer than it paid for.
 */

package com.robertson.app;

import com.robertson.domain.*;
import com.robertson.persistence.TicketRepository;

// myUtils is a personal JAR that I created for myself that contains validation utilises that I previously was having
// to re-write over and over.
import com.robertson.myutils.ValUtils;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static long HOURS_PARKED;
    private static int MINUTES_PAID_FOR;

    public static void main(String[] args){
        // Load JDBC config for repository driver
        TicketRepository.loadConfiguration();

        displayStartBanner();
        // Get user input for hours parked and hours paid for
        getAmountOfTimeParked();
        getAmountOfTimePaidFor();

        // Instantiate required objects
        ParkedCar parkedCar = instantiateParkedCar();
        ParkingMeter parkingMeter = instantiateParkingMeter();
        PoliceOfficer officer = instantiatePoliceOfficer(parkedCar, parkingMeter);

        // Check if car is over time for parking
        if (officer.checkForOffender()){
            boolean issuingSuccess = officer.issueParkingTicket();
            // If insert into repository was successful
            if (issuingSuccess){
                System.out.println("Ticket was added to the DB!");
                displayParkingTicket(ParkingTicketManager.getLastCreatedParkingTicket());
            }
            // Car was an offender but insert into repository failed
            else {
                System.out.println("Ticketing System is currently not operational!");
                System.out.println("Please print an extra copy for your records.");
                displayParkingTicket(ParkingTicketManager.getLastCreatedParkingTicket());
            }
        }
        // Car was not an offender.
        else{
            System.out.println("Well that car is not breaking the law!");
        }
        System.out.println("If you would like to validate that the data for this project is actually in the database, \n" +
                "I have set up a REST endpoint and JS table viewer to view the data \n" +
                "Please visit:  http://mrober23.istwebclass.org/IST_271/Chapter8_8_Data/");
    }

    // Get the user input for how long the car has been parked, validate the input and store it as a class field
    private static void getAmountOfTimeParked(){
        Scanner kb = new Scanner(System.in);
        try {
            String hoursParkedString;
            int hoursParked;
            System.out.println("Please enter the number of hours the car has been parked in whole hours.");
            System.out.print("Hours Parked:  ");
            hoursParkedString = kb.nextLine();
            // Validate that the input can be parsed to a double
            ValUtils.isDouble(hoursParkedString);
            // Since the program only deals in whole hours, sanitize the number to the round up to the next whole hour
            hoursParked = sanitizeInput(hoursParkedString);
            // Validate that the number is not a negative value
            ValUtils.isWithinInclusive(0, 999999, hoursParked);
            HOURS_PARKED = TimeUnit.HOURS.toMillis(hoursParked);
        } catch (Exception ex){
            displayErrorMessage("Please only enter whole numbers that are greater 0 \n");
            getAmountOfTimeParked();
        }
    }

    // Get the user input for how long the parked car has paid to park, validate the input and store it as a class field
    private static void getAmountOfTimePaidFor() {
        Scanner kb = new Scanner(System.in);
        try {
            String hoursPaidString;
            int hoursPaidFor;
            System.out.println("Please enter the number of hours paid for in whole hours.");
            System.out.print("Hours Paid For:  ");
            hoursPaidString = kb.nextLine();
            printBlankLine();
            // Validate that the input can be parsed to a double
            ValUtils.isDouble(hoursPaidString);
            // Since the program only deals in whole hours, sanitize the number to the round up to the next whole hour
            hoursPaidFor = sanitizeInput(hoursPaidString);
            // Validate that the number is not a negative value
            ValUtils.isWithinInclusive(1, 999999, hoursPaidFor);
            MINUTES_PAID_FOR = (int)TimeUnit.HOURS.toMinutes(hoursPaidFor);
        } catch (Exception ex) {
            displayErrorMessage("Please only enter whole numbers that are greater 0 \n");
            getAmountOfTimePaidFor();
        }
    }
    // Returns the number hours as and int, any fractional values are rounded up, because the city counsel are thieves
    // and think that 2.1 hours should be 3 hours.
    private static int sanitizeInput(String str){
        double parsedDouble = Double.parseDouble(str);
        // if x is greater that x.0 round to next whole number.
        parsedDouble = (parsedDouble > Math.floor(parsedDouble)) ? Math.ceil(parsedDouble) : parsedDouble;
        return (int)parsedDouble;
    }

    // Instantiation methods
    private static PoliceOfficer instantiatePoliceOfficer(ParkedCar parkedCar, ParkingMeter parkingMeter) {
        return new PoliceOfficer(
                    "Officer Floyd", 1233456684,
                    parkedCar,
                    parkingMeter
            );
    }
    private static ParkingMeter instantiateParkingMeter() {
        return new ParkingMeter(MINUTES_PAID_FOR);
    }
    private static ParkedCar instantiateParkedCar() {
        // Date .getTime() returns a long in milliseconds, HOURS PARKED is the hours the user entered but in
        // milliseconds so that the time of park can be inferred.
        long date = new Date().getTime();
        long timeOfPark = date - HOURS_PARKED;
        ParkedCar parkedCar =  new ParkedCar("Jeep", "Grand Cherokee", "Black", "ABC275");
        parkedCar.setTimeOfPark(timeOfPark);
        return parkedCar;
    }

    // Display Methods
    private static void displayStartBanner() {
        System.out.println("Welcome to the Parking Ticket Issuer.");
    }
    private static void displayParkingTicket(ParkingTicket pt){
        System.out.println(pt.toString());
    }
    private static void displayErrorMessage(String message){
        printBlankLine();
        System.out.print("INPUT ERROR -- " + message);
    }
    private static void printBlankLine(){
        System.out.println();
    }
}
