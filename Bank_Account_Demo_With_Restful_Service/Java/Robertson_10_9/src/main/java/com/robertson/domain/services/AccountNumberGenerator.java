package com.robertson.domain.services;

import java.util.Random;

/**
 * Used for demonstration purposes -- DO NOT USE IN ACTUAL PRODUCTION WITH WELLS FARGO SYSTEMS!
 * Returns are random number to represent the number of the new account.
 */
public class AccountNumberGenerator {

    public static int generateAccountNumber(){
        Random random = new Random();
        int accountNumber;
        do{
            accountNumber = random.ints(999999999).findAny().getAsInt();
        } while (accountNumber < 0);
        return accountNumber;
    }
}
