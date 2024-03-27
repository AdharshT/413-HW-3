package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Panel for displaying bank account information and updating balance

public class BankAccountInfo extends JPanel {

    // Components for displaying account information and updating balance
    private JLabel accountNumberLabel;
    private JLabel balanceLabel;
    private JTextField accountNumberField;
    private JTextField balanceField;
    private JButton updateButton;

    // Constructor to initialize the panel
    public BankAccountInfo(){
        startProcess();
        addComponents();

    }
    // Method to initialize components and event handlers
    private void startProcess(){
        // Initialize labels, text fields, and button
        accountNumberLabel = new JLabel("Account Number: ");
        balanceLabel = new JLabel("Your Balance: ");
        accountNumberField = new JTextField(20);
        balanceField = new JTextField(20);
        updateButton = new JButton("Update");

        // Set text fields as editable or non-editable
        accountNumberField.setEditable(true);
        balanceField.setEditable(false);

        // Add action listener to the update button
        updateButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();
            double newBalance = Double.parseDouble(balanceField.getText());
            addToDatabase(accountNumber, newBalance);
        });
    }
   // Method to add components to the panel
    private void addComponents(){
        setLayout(new GridLayout(3,2));

        // Add labels, text fields, and button to the panel
        add(accountNumberLabel);
        add(accountNumberField);
        add(balanceLabel);
        add(balanceField);
        add(updateButton);
    }
    // Method to get the account number from the text field
    public String getAccountNumber(){
        return accountNumberField.getText();
    }
    // Method to get the balance from the text field
    public String getBalance(){
        return balanceField.getText();
    }
    // Method to get the update button
    public JButton getUpdateButton(){
        return updateButton;
    }
    // Method to set the account number in the text field
    public void setAccountNumber(String accountNumber){
        accountNumberField.setText(accountNumber);
    }
    // Method to set the balance in the text field
    public void setBalance(double balance){
        balanceField.setText(String.valueOf(balance));
    }
    // Method to update the balance in the database
    private void addToDatabase(String accountNumber, double balance) {
        // Database URL
        String url = "jdbc:mysql://localhost:3306/CS413 (2)";
        String user = "root";
        String password = "Need2know!";

        String sql = "UPDATE bank_accounts SET balance = ? WHERE account_number = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            preparedStatement.setDouble(1, balance);
            preparedStatement.setString(2, accountNumber);

            // Execute the update statement
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Balance for Account Number: " + accountNumber);
            }
        } catch (SQLException ex) {
            System.out.println("Error updating balance: " + ex.getMessage());
        }
        System.out.println("Updating database: Account Number - " + accountNumber + ", Your Balance - " + balance);
    }
}

