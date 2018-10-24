package com.robertson.persistence;
import static org.junit.jupiter.api.Assertions.*;
import com.robertson.persistence.keyValue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DatabaseTests {
    private static String url;
    private static Properties jdbcProps;

    @BeforeAll
    public static void setUpSuite(){
        url = "jdbc:mysql://{url}";
        jdbcProps = new Properties();
        jdbcProps.put("jdbc.username", "{username}");
        jdbcProps.put("jdbc.password", "{password}");
        System.out.println("Database Test Suite is Set Up");
    }

    @AfterAll
    public static void tearDownSuite(){
        url = null;
        jdbcProps = null;
        System.out.println("Database Test Suite has completed and is torn down.");
    }

    @Test
    public void dbConnectionTest(){
        try (Connection conn = DriverManager.getConnection(url,
                jdbcProps.getProperty("jdbc.username"),
                jdbcProps.getProperty("jdbc.password"))) {
            assertTrue(conn.isValid(10));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void dbInsertTest(){
        try (Connection conn = DriverManager.getConnection(url,
                jdbcProps.getProperty("jdbc.username"),
                jdbcProps.getProperty("jdbc.password"));
             Statement stmt = conn.createStatement()) {
            keyValue testObj = new keyValue("Test", "This is a Test");
            String sql = String.format("INSERT INTO test(k,v) VALUES('%s', '%s')", testObj.getKey(), testObj.getValue());
            int insertCount = stmt.executeUpdate(sql);
            assertEquals(1, insertCount);
            if (insertCount == 1){
                System.out.println("Insert was Successful");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
