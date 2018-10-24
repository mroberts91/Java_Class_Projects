package com.robertson.domain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;


public class BaseTest {
    
    @BeforeEach
    public void setUp(){

    }

    @AfterEach
    public void tearDown(){

    }

    @Test
    public void baseTest(){
        Random random = new Random();
        int y = 0;
        for ( int i = 0; i < 1000; i++){
            int accountNumber;
            do{
                accountNumber = random.ints(999999999).findAny().getAsInt();
            } while (accountNumber < 0);
            if (accountNumber < 0){
                y++;
                System.out.println(accountNumber);
            }
        }
        System.out.println(y);
    }
}
