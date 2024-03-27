package com.company;

import com.company.Transaction;

import javax.swing.*;
import java.awt.*;


public class TransactionInfoTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    // Method to create and show the GUI
    private static void createAndShowGUI() {

        // Create the main frame
        JFrame frame = new JFrame("Transaction Info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a sample transaction
        Transaction transaction = new Transaction("Deposit", 500.0);

        // Create a TransactionInfo panel
        TransactionInfo transactionInfo = new TransactionInfo();

        // Add TransactionInfo panel to the frame
        frame.getContentPane().add(transactionInfo, BorderLayout.CENTER);

        // Create a submit button
        JButton submitButton = new JButton("Submit");

        // Add action listener to the submit button
        submitButton.addActionListener(e -> {
            String type = transactionInfo.getType();
            double amount = Double.parseDouble(transactionInfo.getAmount());

            // Display the input values
            JOptionPane.showMessageDialog(frame, "Type: " + type + "\nAmount: " + amount);
        });

        // Add the submit button to the frame
        frame.getContentPane().add(submitButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }
}

