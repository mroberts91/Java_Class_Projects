package com.robertson.domain;
import static org.junit.jupiter.api.Assertions.*;

import com.robertson.domain.enums.RequiredChars;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidatorLogicTest {
    private PasswordValidator validator;

    @BeforeEach
    void setUp(){
        validator = new PasswordValidator(6, RequiredChars.upperCase, RequiredChars.numeric);
    }

    @AfterEach
    void tearDown(){
        validator = null;
    }
    
    @Test
    void isCorrectWithSix(){
        assertTrue(validator.validatePassword("passW8"));
        displayToString("isCorrectWithSix", validator.toString());
    }

    @Test
    void isCorrectMoreThanSix(){
        assertTrue(validator.validatePassword("passwordPASSWORDpassword99"));
        displayToString("isCorrectMoreThanSix", validator.toString());
    }

    @Test
    void tooShortTest(){
        assertFalse(validator.validatePassword("P8"));
        displayToString("tooShortTest", validator.toString());
    }

    @Test
    void noUpperCase(){
        assertFalse(validator.validatePassword("passw8"));
        displayToString("noUpperCase", validator.toString());
    }

    @Test
    void noDigit(){
        assertFalse(validator.validatePassword("passwD"));
        displayToString("noDigit", validator.toString());
     }

    @Test
    void meetNoRequirements(){
        assertFalse(validator.validatePassword("pass"));
        displayToString("meetNoRequirements", validator.toString());
    }

    private void displayToString(String methodName, String classString){
        System.out.println(methodName + "\n" + classString + "\n");
    }
}
