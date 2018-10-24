/*
    Michael Robertson
    IST 271 -- FALL 2018
    Robertson Chapter 9 Program 5
    The purpose of this program is to allow the user to enter a string that represents a potential password, the
    program will then check the password against the criteria and return to the user whether the password is valid
    or not. If not it will alert the user what requirements are missing from their password.
 */

package com.robertson.app.swing;

import com.robertson.domain.ValidatorManager;

import javax.swing.JOptionPane;

public class MainSwing {

    // Static Fields
    private static final int MIN_CHARS = 6;
    private static final boolean UPPER_REQ = true;
    private static final boolean NUMBER_REQ = true;

    public static void main(String[] args){
        // Validation Manager set up
        validationManagerConfig();

        boolean validPassword = false;
        do {
            // Receive the user's input from an Input Box
            String userInput = getUserInput();
            // If user did not click cancel, create a new password validator and check the string from proper criteria.
            if (userInput != null){
//                instantiateNewValidator();
                validPassword  = ValidatorManager.performValidation(userInput, MIN_CHARS);
                displayResultOfValidation(
                        getTypeOfMessage(validPassword)
                );
            } else
                // If user clicks cancel on the input box, it returns null. If null exit the program.
                System.exit(-1);
        } while (!validPassword);

        // Exit the program after the loop completes
        System.exit(0);
    }


    // Basic configuration for the Validation Manager
    private static void validationManagerConfig() {
        ValidatorManager.setRequiresUpperCase(UPPER_REQ);
        ValidatorManager.setRequiresNumber(NUMBER_REQ);
    }

    // Get the user input string from a JOption input box
    private static String getUserInput() {
        return JOptionPane.showInputDialog(null,
                "Please enter a password that you would like to check.",
                "Password Validator", JOptionPane.QUESTION_MESSAGE);
    }

    // Display the results of the password Validation
    private static void displayResultOfValidation(int typeOfMessage) {
        JOptionPane.showMessageDialog(null,
                ValidatorManager.getLastResultString(), "Result", typeOfMessage);
    }
    // Returns the type of JOption Message that the pane will display.
    private static int getTypeOfMessage(boolean validPassword) {
        return (validPassword) ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
    }
}
