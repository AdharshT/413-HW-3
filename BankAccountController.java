package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BankAccountController {

    private BankAccountInfo bankAccountInfo;

    // Constructor to initialize the controller with the BankAccountInfo panel
    public BankAccountController(BankAccountInfo bankAccountInfo) {
        this.bankAccountInfo = bankAccountInfo;
        // Register event handlers for the UI components
        eventHandlers();
    }

    // Method to register event handlers for UI components
    private void eventHandlers(){
        // Add action listener to the updateButton in BankAccountInfo panel
       bankAccountInfo.getUpdateButton().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               clickUpdateButton();
           }
       });
    }

    // Method to handle updateButton click event
    private void clickUpdateButton(){
        // Get account number from text field
        String accountNumber = bankAccountInfo.getAccountNumber();
        // Get balance from text field
        String balanceText = bankAccountInfo.getBalance();

        // Check if balance field is not empty
        if(!balanceText.isEmpty()) {
            try {
                double balance = Double.parseDouble(balanceText);

                if (balance > 0) {
                    // Call method to update bank account balance in database
                    BankAccountManager.updateBankAccount(accountNumber, balance);
                    // Show success message
                    JOptionPane.showMessageDialog(bankAccountInfo, "Bank account updated successfully. ");
                } else {
                    // Show error message for non-positive balance value
                    JOptionPane.showMessageDialog(bankAccountInfo, "Balance must have a positive value.");
                }
            } catch (NumberFormatException e) {
                // Show error message for invalid balance input
                JOptionPane.showMessageDialog(bankAccountInfo, "Invalid balance input");
            }
        }
                else{
                    // Show error message if balance field is empty
                    JOptionPane.showMessageDialog(bankAccountInfo, "Please enter your balance value.");

            }
        }

    }
