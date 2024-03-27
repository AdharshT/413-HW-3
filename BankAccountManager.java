package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankAccountManager {
    public static void updateBankAccount(String accountNumber, double balance) {
        String url = "jdbc:mysql://localhost:3306/CS413 (2)"; // database URL
        String username = "root";
        String password = "Need2know!";

        // SQL query to update bank account balance
        String sql = "UPDATE bank_accounts SET balance = ? WHERE account_number = ?";

        try (
                // Establish database connection
                Connection connection = DriverManager.getConnection(url, username, password);
                // Prepare the SQL statement with parameters
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            // Set the parameters for the prepared statement
            statement.setDouble(1, balance);
            statement.setString(2, accountNumber);

            // Execute the updated statement
            int rowsAffected = statement.executeUpdate();

            // Check if any rows were affected
            if (rowsAffected > 0) {
                System.out.println("Bank account updated successfully.");
            } else {
                System.out.println("Failed to update bank account.");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }
}
