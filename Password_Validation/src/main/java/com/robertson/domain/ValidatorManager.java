package com.robertson.domain;

import com.robertson.domain.enums.RequiredChars;

/*
    Validation Manager is an static abstraction layer class to add a password validation manager between presentation
    layer UI and the core bushiness rules of the Password Validator.
 */
public class ValidatorManager {
    private static boolean requiresUpperCase;
    private static boolean requiresNumber;
    private static String lastResultString;


    public static boolean performValidation(String password, int minLength){
        PasswordValidator validator;
        boolean isValid;

        // Logic for determining the user's requests and how to instantiate the password validator class.
        if (requiresUpperCase && requiresNumber){
            validator = new PasswordValidator(minLength, RequiredChars.upperCase, RequiredChars.numeric);
            isValid = validator.validatePassword(password);
            setLastResultString(validator.toString());
        } else if (requiresUpperCase){
            validator = new PasswordValidator(minLength, RequiredChars.upperCase);
            isValid = validator.validatePassword(password);
            setLastResultString(validator.toString());
        } else if (requiresNumber){
            validator = new PasswordValidator(minLength, RequiredChars.numeric);
            isValid = validator.validatePassword(password);
            setLastResultString(validator.toString());
        } else {
            validator = new PasswordValidator(minLength);
            isValid = validator.validatePassword(password);
            setLastResultString(validator.toString());
        }
        return isValid;
    }
    // sets the requires number boolean field
    public static void setRequiresNumber(boolean requiresNumber) {
        ValidatorManager.requiresNumber = requiresNumber;
    }

    // Sets the requires upper case boolean field
    public static void setRequiresUpperCase(boolean requiresUpperCase) {
        ValidatorManager.requiresUpperCase = requiresUpperCase;
    }

    // Get the last password validation result string.
    public static String getLastResultString() {
        return lastResultString;
    }

    // Sets the last result string.
    private static void setLastResultString(String lastResultString) {
        ValidatorManager.lastResultString = lastResultString;
    }
}
