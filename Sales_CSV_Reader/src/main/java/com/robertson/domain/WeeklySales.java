package com.robertson.domain;

import java.util.List;

/*
    WeeklySales represents one week of sales, it contains a list of DailySales with are the sales from each day of
     the week. This class also calculates its own total sales and average sales for the week.

 */
public class WeeklySales {
    private int weekNumber;
    private List<DailySales> salesList;
    private double totalSales;
    private double averageSales;

    public WeeklySales(int weekNumber, List<DailySales> salesList){
        this.weekNumber = weekNumber;
        this.salesList = salesList;
        this.totalSales = calcTotalSales();
        this.averageSales = calcAverageSale();
    }

    // Calculate the total sales
    private double calcTotalSales() {
        return salesList.stream()
                .mapToDouble(DailySales::getAmount)
                .sum();
    }

    // Calculate the average sales
    private double calcAverageSale() {
        return salesList.stream()
                .mapToDouble(DailySales::getAmount)
                .average()
                .getAsDouble();
    }

    // Getters
    public List<DailySales> getSales() {
        return salesList;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public double getAverageSales() {
        return averageSales;
    }

    public int getWeekNumber() {
        return weekNumber;
    }
}
