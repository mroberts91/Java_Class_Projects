package com.robertson.persistence;

import com.robertson.domain.ParkingTicket;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TicketRepository {

    private static Properties jdbcProps;

    // Base insert method that uses the url of the Ticket Repository jdbc properties field.
    public static boolean insertNewParkingTicket(ParkingTicket pt){
        return insertNewParkingTicket(pt,
                jdbcProps.getProperty("jdbc.url"),
                jdbcProps.getProperty("jdbc.username"),
                jdbcProps.getProperty("jdbc.password")
                );
    }
    // Insert a new parking ticket into the database
    public static boolean insertNewParkingTicket(ParkingTicket pt, String url, String username, String password){
        try (Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement(
             )){
            // Format the SQL data
            String data = String.format("'%d', '%d', '%s', '%s', '%s', '%s', '%s', '%f'",
                    pt.getId(), pt.getOfficerBadgeNumber(), pt.getOfficerName(),
                    pt.getCarLicense(), pt.getCarMake(), pt.getCarModel(), pt.getCarColor(), pt.getTotalFine());
            // Format the SQL INSERT string
            String sql = "INSERT INTO parking_tickets VALUES (" + data + ")";
            // Execute the Update and check if the row count returns 1
            int insertRowCount = stmt.executeUpdate(sql);
            return insertRowCount == 1;
        } catch (SQLException ex){
            return false;
        }
    }

    // Loads the JDBC properties keys and values from the db.properties file and sets the jdbcProps field.
    public static void loadConfiguration(){
        Properties prop = new Properties();
        try {
            InputStream stream = new FileInputStream(".\\src\\main\\resources\\db.properties");
            prop.load(stream);
            stream.close();
        } catch (Exception ex){ }
        setJdbcProps(prop);
    }

    // Getters
    public static Properties getJdbcProps() {
        return jdbcProps;
    }

    // Setters
    public static void setJdbcProps(Properties jdbcProps) {
        TicketRepository.jdbcProps = jdbcProps;
    }
}
