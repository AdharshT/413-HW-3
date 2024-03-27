package com.company;

public class Transaction {
    private String type;
    private double amount;

    // Constructor to initialize type and amount of the transaction
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    // Getter method for retrieving the type of transaction
    public String getType() {
        return type;
    }
    // Setter method for setting the type of transaction
    public void setType(String type) {
        this.type = type;
    }
    // Getter method for retrieving the amount of the transaction
    public double getAmount() {
        return amount;
    }
    // Setter method for setting the amount of the transaction
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}

