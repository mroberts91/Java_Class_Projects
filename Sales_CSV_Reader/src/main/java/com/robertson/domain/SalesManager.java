package com.robertson.domain;

import com.robertson.domain.interfaces.IRepositoy;

import java.util.Comparator;
import java.util.List;
/*
    SalesManager orchestrates the calling and organizing of the sales data and is the main entry point into the domain
    from the presentation layer.
 */
public class SalesManager {
    private List<WeeklySales> weeklySalesList;
    private IRepositoy salesRepository;
    private double averageSales;
    private double totalSales;
    private WeeklySales highestWeek;
    private WeeklySales lowestWeek;

    // Constructor that takes a IRepository for the sales data repository.
    public SalesManager(IRepositoy salesRepository){
        this.salesRepository = salesRepository;
        this.weeklySalesList = null;
        this.averageSales = 0.0;
        this.totalSales = 0.0;
        this.highestWeek = null;
        this.lowestWeek = null;
    }

    // Get the list of WeeklySales from the repository and calculate the sales and set high/low weeks
    public void populateSales(){
        weeklySalesList =  salesRepository.getAllSales();
        calculateTotalSales();
        calculateAverageSales();
        determineHighestWeek();
        determineLowestWeek();
    }

    // Calc the total sales
    private void calculateTotalSales() {
        weeklySalesList.forEach(sales -> totalSales += sales.getTotalSales());
    }

    // Calculate the average sales
    private void calculateAverageSales() {
        averageSales = totalSales / weeklySalesList.size();
    }

    // Sort the weekly sales list from high to low, must reverse the list
    private void determineHighestWeek() {
        weeklySalesList.sort(Comparator.comparingDouble(WeeklySales::getTotalSales).reversed());
        highestWeek = weeklySalesList.get(0);

    }

    // Sort the weekly sales list, default sort, is lowest to highest
    private void determineLowestWeek() {
        weeklySalesList.sort(Comparator.comparingDouble(WeeklySales::getTotalSales));
        lowestWeek = weeklySalesList.get(0);
    }

    // Returns a sales report as a string, the report is in table format
    public String getSalesReport(){
        return ReportBuilder.buildSalesReport(weeklySalesList, totalSales, averageSales, highestWeek, lowestWeek);
    }


    // Getters
    public List<WeeklySales> getWeeklySalesList(){
        return this.weeklySalesList;
    }

    public double getAverageSales() {
        return averageSales;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public WeeklySales getHighestWeek() {
        return highestWeek;
    }

    public WeeklySales getLowestWeek() {
        return lowestWeek;
    }
}
