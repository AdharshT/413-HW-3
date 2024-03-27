package com.company;

import com.company.Transaction;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TransactionInfo extends JPanel {

    // GUI components
    private JLabel typeLabel;
    private JLabel amountLabel;
    private JTextField typeField;
    private JTextField amountField;
    private JButton submitButton;

    // Transaction object
    private Transaction transaction;

    // Constructor
    public TransactionInfo(){
        startProcess();
        addComponents();
    }

    // Initialize GUI components
    private void startProcess() {

        typeLabel = new JLabel("Type: ");
        amountLabel = new JLabel("Amount: ");
        typeField = new JTextField(20);
        amountField = new JTextField(20);

        typeField.setEditable(true);
        amountField.setEditable(true);

        // Button for submitting transaction
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String type = typeField.getText();
            double amount = Double.parseDouble(amountField.getText());
            addToDatabase(type, amount);
        });
    }

    // Add GUI components to the panel

    private void addComponents(){

        setLayout(new GridLayout(2,2));

        add(typeLabel);
        add(typeField);
        add(amountLabel);
        add(amountField);
    }

    // Set transaction object and update GUI fields accordingly
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        updateFields();
    }
    // Update GUI fields with transaction information
    private void updateFields() {
        typeField.setText(transaction.getType());
        amountField.setText(String.valueOf(transaction.getAmount()));
    }
    // Getter and Setter for transaction, type, and amount object
    public Transaction getTransaction() {
        return transaction;
    }

    public String getType(){
        return typeField.getText();
    }

    public String getAmount(){
        return amountField.getText();
    }

    public void setType(String type){
        typeField.setText(type);
    }

    public void setAmount(double amount){
        amountField.setText(String.valueOf(amount));
    }

    // Add transaction to the database
    private void addToDatabase(String type, double amount) {
        String url = "jdbc:mysql://localhost:3306/CS413 (2)";
        String user = "root";
        String password = "Need2know!";

        String sql = "INSERT INTO transactions (type, amount) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, type);
            preparedStatement.setDouble(2, amount);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new transaction was made!");
            }
        } catch (SQLException ex) {
            System.out.println("Error entering transaction: " + ex.getMessage());
        }
        System.out.println("Adding to database: Type - " + type + ", Amount - " + amount);
    }
}

