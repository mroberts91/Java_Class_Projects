package com.robertson.domain;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;

public class MinutesParkedTest {
    private ParkedCar car;
    @BeforeEach
    public void setUp(){
        car = new ParkedCar();
        System.out.println("New ParkedCar is instantiated;");
    }

    @AfterEach
    public void tearDown(){
        car = null;
        System.out.println("Test Complete, ParkedCar is null");
    }

    @Test
    public void parkingTimeTest(){
        Time currentTime = new Time(new Date().getTime());
        car.setTimeOfPark(new Date().getTime() - (1000 * 120));
        System.out.println(car.getTimeOfPark());
        System.out.println(currentTime);
        assertTrue(car.getMinutesParked() >= 2);
        System.out.println("Minutes Parked:  " + car.getMinutesParked());
    }
}
