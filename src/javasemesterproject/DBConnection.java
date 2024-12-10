package javasemesterproject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public Connection c;
    public Statement s;

    public DBConnection(){
        try {
            // Register JDBC Driver
            System.out.println("Loading JDBC Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection details
            String url = "jdbc:mysql://localhost:3306/ELearningSystem?useSSL=false&serverTimezone=UTC";
            String username = "root"; 
            String password = "MySql.123";  

            System.out.println("Attempting to connect to the database...");
            // Establish connection
            c = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully!");

            // Create Statement object with scroll capability
            s = c.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            System.out.println("Statement created successfully!");

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to create a new statement with scroll capability
    public Statement createScrollableStatement() throws SQLException {
        if (c != null) {
            return c.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
        }
        return null;
    }

    public void Close() {
        try {
            if (c != null) {
                System.out.println("Closing the database connection...");
                c.close();
                System.out.println("Connection closed successfully.");
            } else {
                System.out.println("No connection to close.");
            }
        } catch (SQLException ex) {
            System.err.println("Error closing connection: " + ex.getMessage());
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}