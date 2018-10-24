package com.robertson.domain;

import com.robertson.domain.enums.RequiredChars;

import static com.robertson.domain.enums.RequiredChars.*;

/**
    Password Validator is an Immutable class that takes an string argument to test if the string contains the required
    criteria for a valid password.
 */
public class PasswordValidator {
    private int minLength;
    private boolean requiresUpperCase;
    private boolean requiresDigit;
    private char[] chArray;
    private boolean isValidLength;
    private boolean hasUpperCase;
    private boolean hasDigit;

    // Constructor
    /**
     * If constructor is called but there are no requirements flags passed in then the Password Validator is
     * defaulted to not check for additional requirements other than length.
     * @param minLength The minimum length required for the password.
     */
    public PasswordValidator(int minLength){
        this.minLength = minLength;
        this.requiresUpperCase = false;
        this.requiresDigit = false;
        this.chArray = null;
        this.isValidLength = false;
        this.hasUpperCase = false;
        this.hasDigit = false;
    }

    /**
     * Accepts the minimum length of the string as well as one additional requirement option for the criteria
     * @param minLength The minimum length required for the password.
     * @param option RequiredChar enum option either upper case requirement or numeric requirement.
     */
    public PasswordValidator(int minLength, RequiredChars option){
        this(minLength);
        this.requiresUpperCase = (option == upperCase);
        this.requiresDigit = (option == numeric);
    }

    /**
     * Accepts the minimum length as well as two additional requirement options for the criteria
     * @param minLength  Minimum required length of the password.
     * @param option1 RequiredChar enum option either upper case requirement or numeric requirement.
     * @param option2  RequiredChar enum option either upper case requirement or numeric requirement.
     */
    public PasswordValidator(int minLength, RequiredChars option1, RequiredChars option2){
        this(minLength);
        this.requiresUpperCase = ((option1 == upperCase) || (option2 == upperCase));
        this.requiresDigit = ((option1 == numeric) || (option2 == numeric));

    }

    /**
     * Validate Password method takes in a String argument and the runs the validation check based on what flags were
     * set during instantiation of the class.
     * @param password - The String that needs to be validated for proper requirements.
     * @return True if the password meets the requirements, False if it does not meet all the requirements.
     */
    public boolean validatePassword(String password){
        chArray = password.trim().toCharArray();
        checkForValidLength();
        if (requiresUpperCase)
            checkForUpperCase();
        if (requiresDigit)
            checkForDigit();
        return isValid();
    }

    // Check if the password string is the required length
    private void checkForValidLength(){
        isValidLength = chArray.length >= minLength;
    }

    // Check the password string from an upper case letter in any position
    private void checkForUpperCase(){
        for (char c : chArray)
            if (!hasUpperCase) hasUpperCase = Character.isUpperCase(c);
    }
    // Check the password string for a digit in any position
    private void checkForDigit(){
        for (char c : chArray)
            if (!hasDigit) hasDigit = Character.isDigit(c);
    }

    // Return if all the requirements were met
    private boolean isValid() {
        if (requiresUpperCase && requiresDigit){
            return isValidLength && hasUpperCase && hasDigit;
        } else if (requiresUpperCase){
            return isValidLength && hasUpperCase;
        } else if (requiresDigit){
            return isValidLength && hasDigit;
        } else{
            return isValidLength;
        }
    }

    @Override
    public String toString(){
        String statusString = "";
        // Display that all requirements were met
        if (isValid()){
            statusString += "Password:  '" + String.valueOf(chArray) + "'\n" +
                    "Meets all the password criteria.";
        }
        // Display what requirements were not met
        else {
            if (!isValidLength)
                statusString += "Password must contain more than 6 characters.\n";
            if (requiresUpperCase && !hasUpperCase)
                statusString += "Password must contain at least one upper case character.\n";
            if (requiresDigit && !hasDigit)
                statusString += "Password must contain at least one numeric character.\n";
        }
        return statusString;
    }
}
