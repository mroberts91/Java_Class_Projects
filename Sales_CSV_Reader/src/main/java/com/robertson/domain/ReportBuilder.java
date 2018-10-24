package com.robertson.domain;

import java.util.Comparator;
import java.util.List;
/*
    Report Builder class, contains a static method that just simply formats the sales data into a readable table and
    returns that table as a string
 */
class ReportBuilder {

    /**
     *
     * @param weeklySalesList A List of WeeklySales
     * @param totalSales A double that contains the Total sales for all the weeks
     * @param averageSales A double that contains the Average weekly sale for all the weeks
     * @param highestWeek WeeklySales object, The weekly sale with the highest sale value
     * @param lowestWeek WeeklySales object, The weekly sale with the lowest sale value
     * @return Returns a String that is formatted in the form a table optimized for console output.
     */
    static String buildSalesReport(List<WeeklySales> weeklySalesList, double totalSales, double averageSales,
                                   WeeklySales highestWeek, WeeklySales lowestWeek){
        // Sort the list by week number
        weeklySalesList.sort(Comparator.comparingInt(WeeklySales::getWeekNumber));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("+----------+-------------+-------------+%n"))
                .append(String.format("| %-8s | %-11s | %-10s |%n", "Week #", "Total", "Avg per day"))
                .append(String.format("+----------+-------------+-------------+%n"));
        // Add each week and its data to the table
        for (WeeklySales week :
                weeklySalesList) {
            sb.append(row(
                    week.getWeekNumber(),
                    week.getTotalSales(),
                    week.getAverageSales()
            ));
        }
        // Display the totals and high and low week
        sb.append(String.format("+----------+-------------+-------------+%n"))
                .append(String.format("+------------------------+-------------+%n"))
                .append(row("Total Sales", totalSales))
                .append(row("Average Sales", averageSales))
                .append(row("Highest Week", highestWeek.getWeekNumber()))
                .append(row("Lowest Week", lowestWeek.getWeekNumber()))
                .append(String.format("+------------------------+-------------+%n"));
        return sb.toString();
    }

    // Helper methods for formatting each row of the table.
    private static String row(String key, int value){
        return String.format("| %-22s | Week %-6d |%n", key, value);
    }
    private static String row(String key, double value){
        return String.format("| %-22s | $%-10.2f |%n", key, value);
    }
    private static String row(int weekNum, double total, double average){
        return String.format("| %-8s | $%-10.2f | $%-10.2f |%n", weekNum, total, average);
    }
}
