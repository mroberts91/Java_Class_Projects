package com.robertson.domain;

/*
    DailySales class represents the sales amount for one day of the week
 */
public class DailySales {
    private double amount;

    // Constructor with the sales amount for that say
    public DailySales(double amount){
        this.amount = amount;
    }

    // Return the amount of the sale
    public double getAmount() {
        return amount;
    }
}
