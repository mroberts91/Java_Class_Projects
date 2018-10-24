package com.robertson.persistance;

import com.robertson.domain.DailySales;
import com.robertson.domain.WeeklySales;
import com.robertson.domain.interfaces.IRepositoy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    Sales CSV reader is a data repository that will read and parse a csv file and return a new WeeklySales object graph
 */
public class SalesCSVReader implements IRepositoy {
    private File salesCSV;

    // Constructor that takes the path to the CSV file
    public SalesCSVReader(String filePath){
        this.salesCSV = new File(filePath);
    }

    // Get all sales, reads the sales csv file, parses the sales data and returns a list of WeeklySales to the caller
    public List<WeeklySales> getAllSales(){
        List<WeeklySales> weeklySalesList  = new ArrayList<>();
        // Use the scanner to read the CSV file
        try (Scanner reader = new Scanner(salesCSV)){
            while (reader.hasNextLine()){
                List<DailySales> dailySalesList = new ArrayList<>();
                // Split each csv line to a String array
                String[] salesArray = reader.nextLine().split(",");
                // For each daily sale, create a new DailySales and add it to the sales list
                for (String item:
                     salesArray) {
                    dailySalesList.add(new DailySales(Double.parseDouble(item)));
                }
                // Set the week number and add the WeeklySales object to the weeklySales list
                int weekNumber = weeklySalesList.size() + 1;
                weeklySalesList.add(new WeeklySales(weekNumber, dailySalesList));
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println(fileNotFound.getMessage());
        }
        return weeklySalesList;
    }
}
