package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionUtil {

    private static Connection conn = null;

    private ConnectionUtil() {

    }
    public static Connection getConnection() {

        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Use a previously made connection");
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
            }

        String url = "";
        String username = System.getenv("username");
        String password = System.getenv("password");
        Properties props = new Properties();

        try {
            props.load(new FileReader("src/main/resources/application.properties"));
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Could not establish connection");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("Failed to load PostgreSQL Driver");
            throw new RuntimeException(e);
        }
    }
}
