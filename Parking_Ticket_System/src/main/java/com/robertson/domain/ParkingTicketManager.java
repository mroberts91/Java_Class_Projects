package com.robertson.domain;

import com.robertson.persistence.TicketRepository;

/*
    ParkingTicketManager is a beginning attempt to create a Facade class to manage the creation of a new parking ticket
    and also interact with the TicketRepository in the persistence layer.
 */
public class ParkingTicketManager {

    private static ParkingTicket lastCreatedParkingTicket;

    // Creates a new parking ticket and returns a boolean on whether or not the Ticket Repository was successful at
    // storing the new ticket in the data store.
    public static boolean createNewParkingTicket(ParkedCar offendingVehicle, PoliceOfficer  issuingOfficer, int timeOver){
        ParkingTicket pt = new ParkingTicket(offendingVehicle, issuingOfficer, timeOver);
        setLastCreatedParkingTicket(pt);
        return repositoryInsert(pt);
    }

    // Orchestrates the Ticket Repository insert procedure and will attempt insert 5 times
    // Returns boolean based on the Ticket Repository's success to update the data store.
    private static boolean repositoryInsert(ParkingTicket pt){
        boolean isSuccessful;
        int failureCount = 0;
        do{
            isSuccessful = TicketRepository.insertNewParkingTicket(pt);
            // Update the failure count if not successful
            if (!isSuccessful) failureCount++;
            // Return if still not successful after 5 attempts.
            if (!isSuccessful && failureCount >= 5) return false;

        } while (!isSuccessful) ;
        return true;
    }

    // Getters
    public static ParkingTicket getLastCreatedParkingTicket() {
        return lastCreatedParkingTicket;
    }

    // Setters
    private static void setLastCreatedParkingTicket(ParkingTicket lastCreatedParkingTicket) {
        ParkingTicketManager.lastCreatedParkingTicket = lastCreatedParkingTicket;
    }
}
