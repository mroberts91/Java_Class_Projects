/*
 *  Michael Robertson
 *  IST 271 - Fall 2018
 *  Chapter 10 Program 9
 * This program is designed to generate a new savings account for the user and allow the user to interact with the
 * account, performing transactions.
 */

package com.robertson.app;

import com.robertson.domain.BankAccountManager;
import com.robertson.domain.exceptions.AccountException;

import java.util.Scanner;

public class Main {

    private static BankAccountManager accountManager;
    private static boolean dataStoreStatus;

    public static void main(String[] args){
        String userInput;
        boolean exit = false;
        // Perform setup config that is needed.
        startupConfiguration();
        displayApplicationStartBanner();
        // Display the web app url if connection was successful
        displayWebAppUri(accountManager.getCurrentAccountNum());
        // Main application loop
        do{
            userInput = getUserSelectionInput().toUpperCase();
            // Determine what to do based on user's input selection
            switch (userInput){
                case "B": performBalanceCheck(); break;
                case "D": makeDeposit(); break;
                case "W": makeWithdrawal(); break;
                case "F": performMonthlyServiceCharges(); break;
                case "X": exit = true; break;
                default: displaySelectionErrorMessage(); break;
            }
        } while (!exit);
        System.exit(10);
    }

    // Call the account manager to perform service charge procedure.
    private static void performMonthlyServiceCharges() {
        System.out.println(accountManager.performMonthlyServiceCharges());
    }

    // Get the user's amount input and Call the account manager to make withdrawal
    private static void makeWithdrawal() {
        try{
            double withdrawalAmount = getUserDoubleInput("Withdrawal");
            System.out.println(accountManager.makeWithdrawal(withdrawalAmount));
        } catch (AccountException ex){
            displayTransactionErrorMessage(ex.getMessage());
        }
    }

    // Get the user's amount input and Call the account manager to make deposit
    private static void makeDeposit() {
        double depositAmount = getUserDoubleInput("Deposit");
        System.out.println(accountManager.makeDeposit(depositAmount));
    }


    // Call the account manager to get the account balance
    private static void performBalanceCheck() {
        System.out.println(accountManager.performBalanceCheck());
    }

    // Get and validate the user's input for dollar amounts for transactions.
    private static double getUserDoubleInput(String transaction){
        Scanner kb = new Scanner(System.in);
        boolean isValid = false;
        String userInput;
        double parsedDouble = 0;
        do {
            System.out.println("Please enter the dollar amount you would like to " + transaction);
            System.out.print("Amount:  $");
            userInput = kb.nextLine();
            if (isDouble(userInput) && isNotNegative(userInput)){
                isValid = true;
                parsedDouble = Double.parseDouble(userInput);
            } else {
                displayNumberInputErrorMessage();
            }
        } while (!isValid);
        return parsedDouble;
    }

    // Get and validate the user's input for the selection menu.
    private static String getUserSelectionInput() {
        Scanner kb = new Scanner(System.in);
        boolean isValid = false;
        String userInput;
        do{
            System.out.println("What you like to do:\n" +
                    "\tBalance Inquiry:\t'B'\n" +
                    "\tDeposit:\t\t'D'\n" +
                    "\tWithdrawal:\t\t'W'\n" +
                    "\tRun Monthly Fees:\t'F'\n" +
                    "\tExit Program:\t\t'X'");
            System.out.print("Your Selection:  ");
            userInput = kb.nextLine();
            if (isValidChoice(userInput.toUpperCase())){
                isValid = true;
            } else{
                displaySelectionErrorMessage();
            }

        } while (!isValid);
        return userInput;
    }

    // Determine if user input was a valid choice

    private static boolean isValidChoice(String userInput) {
        switch (userInput){
            case "B": return true;
            case "D": return true;
            case "W": return true;
            case "F": return true;
            case "X": return true;
            default: return false;
        }
    }
    // Determine if a string can be parsed to a double

    private static boolean isDouble(String userInput){
        try {
            Double.parseDouble(userInput);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    // Check if a string parsed to a double is negative
    private static boolean isNotNegative(String input){
        return Double.parseDouble(input) >= 0;
    }


    // Display methods
    private static void displayApplicationStartBanner() {
        System.out.println("Welcome to Contoso National Bank");
        System.out.println("--------------------------------");
    }

    private static void displaySelectionErrorMessage() {
        System.out.println("\n! Error -- Selection made was not valid\n");
    }

    private static void displayTransactionErrorMessage(String message){
        System.out.println("\n! " + message + "\n");
    }

    private static void displayNumberInputErrorMessage(){
        System.out.println("\n! Error -- Transaction amounts can only be numeric values greater than $0");
    }

    private static void displayWebAppUri(int accountNumber) {
        if (dataStoreStatus){
            System.out.println("If you would like to view your account data online, visit \n" +
                    "\t http://accountsapi20181022063942.azurewebsites.net/BankAccounts/Details/" + accountNumber);
            System.out.println("*If you use dual screens you can watch the web app update every 7 seconds automatically*\n");
        }
    }

    // Perform initial configuration, create an account manager and attempt to create new savings account in the data store
    private static void startupConfiguration() {
        System.out.println("\nWe are setting up your new Account....\n");
        accountManager = new BankAccountManager();
        dataStoreStatus = accountManager.createBankAccountInDataStore();
    }
}
