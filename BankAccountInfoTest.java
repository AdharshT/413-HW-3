package com.company;

import javax.swing.*;

// Test class to create and display the BankAccountInfo panel
public class BankAccountInfoTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the GUI on the event dispatch thread
                createAndShowGUI();
            }
        });
    }
    // Method to create and display the GUI
    private static void createAndShowGUI() {
        // Create a new JFrame
        JFrame frame = new JFrame("Bank Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of BankAccountInfo panel
        BankAccountInfo bankAccountInfo = new BankAccountInfo();

        // Set some random values for account number and balance
        bankAccountInfo.setAccountNumber("1234567890");
        bankAccountInfo.setBalance(1000.50);

        // Add the BankAccountInfo panel to the frame
        frame.getContentPane().add(bankAccountInfo);

        frame.pack();
        frame.setVisible(true);
    }
}
