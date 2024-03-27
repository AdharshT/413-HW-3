package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerInfo extends JPanel {
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneNumberLabel;
    private JLabel accountNumberLabel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JTextField accountNumberField;

    // Constructor
    public CustomerInfo(){
        startProcess();
        addComponents();
    }
    // Initialize GUI components
    private void startProcess(){
        nameLabel = new JLabel("Name: ");
        emailLabel = new JLabel("Email: ");
        phoneNumberLabel = new JLabel("Phone Number: ");
        accountNumberLabel = new JLabel("Account Number: ");
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        accountNumberField = new JTextField(20);

        // Set fields as editable

        nameField.setEditable(true);
        emailField.setEditable(true);
        phoneNumberField.setEditable(true);
        accountNumberField.setEditable(true);

    }
    // Add GUI components to the panel
    private void addComponents(){
        setLayout(new GridLayout(4,2));

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(phoneNumberLabel);
        add(phoneNumberField);
        add(accountNumberLabel);
        add(accountNumberField);

    }
    // Add customer information to the database
    private void addToDatabase(String name, String email, String phoneNumber, String accountNumber){
        String url = "jdbc:mysql://localhost:3306/CSC413 (2)";
        String user = "root";
        String password = "Need2know!";

        String sql = "INSERT INTO customers (name, email, phoneNumber, accountNumber) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, accountNumber);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new customer was added!");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting customer: " + e.getMessage());
        }
        System.out.println("Adding to database: Name: " + name + ", Email: " + email + ", Phone #: " + phoneNumber + ", Account Number: " + accountNumber);
    }

    // Getters and setters for customer information
    public String getName(){
        return nameField.getText();
    }

    public String getEmail(){
        return emailField.getText();
    }

    public void setName(String name){
        nameField.setText(name);
    }

    public void setEmail(String email){
        emailField.setText(email);
    }

    public String getPhoneNumber(){
        return phoneNumberField.getText();
    }

    public void setPhoneNumber(String phoneNumber){
        phoneNumberField.setText(phoneNumber);
    }

    public String getAccountNumber(){
        return accountNumberField.getText();
    }

    public void setAccountNumber(String accountNumber){
        accountNumberField.setText(accountNumber);
    }

    // Clear all fields
    public void clearFields() {
        nameField.setText("");
        emailField.setText("");
        phoneNumberField.setText("");
        accountNumberField.setText("");
    }

    // Main method for testing
    public static void main(String[] args) {
        createCustomerObjects();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Customer Information");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            CustomerInfo customerInfoPanel = new CustomerInfo();
            frame.getContentPane().add(customerInfoPanel);
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(e -> {
                String name = customerInfoPanel.getName();
                String email = customerInfoPanel.getEmail();
                String phoneNumber = customerInfoPanel.getPhoneNumber();
                String accountNumber = customerInfoPanel.getAccountNumber();
                customerInfoPanel.addToDatabase(name, email, phoneNumber, accountNumber);
            });
            frame.getContentPane().add(submitButton, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);
        });
    }
    // Method to create random customer objects
    private static void createCustomerObjects(){

        CustomerInfo customer1 = new CustomerInfo();
        CustomerInfo customer2 = new CustomerInfo();

        customer1.setName("John Doe");
        customer1.setEmail("john@gmail.com");
        customer1.setPhoneNumber("1234567890");
        customer1.setAccountNumber("435647863");

        customer2.setName("Leo Das");
        customer2.setEmail("leo@egmail.com");
        customer2.setPhoneNumber("0987654321");
        customer2.setAccountNumber("123456789");
    }
}

