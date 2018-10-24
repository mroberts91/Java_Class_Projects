/*
    Michael Robertson
    IST 271 - Fall 2018
    Chapter 9 Program 11
    The purpose this program is for the program to read a text file and parse out the weekly sales data and display it
    to the user as report.
 */


package com.robertson.app;
import com.robertson.domain.RepositoryFactory;
import com.robertson.domain.SalesManager;


public class Main {
    // Path for the sales data file
    private static String DATA_URL = ".\\src\\main\\resources\\SalesData.txt";
    // Longer csv data set to test the program with different data.
    //    private static String DATA_URL = ".\\src\\main\\resources\\testData.csv";

    public static void main(String[] args){
        // Instantiate a new SalesManager with a new CSV Repository
        SalesManager sm = new SalesManager(
                RepositoryFactory.csvRepository(DATA_URL)
        );
        // Populate the sales data into the program
        sm.populateSales();

        // Display the sales report to the console
        System.out.println(sm.getSalesReport());
    }
}
