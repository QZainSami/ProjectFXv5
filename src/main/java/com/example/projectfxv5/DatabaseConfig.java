package com.example.projectfxv5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

/**
 * Database configuration and connection management class.
 * Provides centralized database connection handling.
 */
public class DatabaseConfig {
    private static final String DEFAULT_DB_PATH = "C:/Users/qsami/Desktop/AutomotiveInventory.accdb";
    private static final String CONFIG_FILE = "config.properties";
    private static String dbPath;
    
    static {
        // Load configuration from properties file if available
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                Properties prop = new Properties();
                prop.load(input);
                dbPath = prop.getProperty("db.path", DEFAULT_DB_PATH);
            } else {
                dbPath = DEFAULT_DB_PATH;
            }
        } catch (IOException ex) {
            System.err.println("Could not load configuration file. Using default database path.");
            dbPath = DEFAULT_DB_PATH;
        }
    }
    
    /**
     * Get a connection to the database.
     * 
     * @return A Connection object or null if connection fails
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the database driver cannot be loaded
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        return DriverManager.getConnection("jdbc:ucanaccess://" + dbPath);
    }
    
    /**
     * Safely close a database connection.
     * 
     * @param connection The connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }
}