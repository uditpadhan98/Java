package com.bankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String accountType;

    public BankAccount(String accountNumber, String accountHolderName, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) throws SQLException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
        updateBalanceInDatabase();
        System.out.println("Deposit successful of Rs." + amount);
    }

    public synchronized void withdraw(double amount) throws SQLException, InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > this.balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        this.balance -= amount;
        updateBalanceInDatabase();
        System.out.println("Withdrawal successful of Rs." + amount);
    }

    public synchronized void transfer(BankAccount targetAccount, double amount) throws SQLException, InvalidAccountException, InsufficientFundsException {
        if (targetAccount == null) {
            throw new InvalidAccountException("Target account cannot be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
        withdraw(amount);
        targetAccount.deposit(amount);
        System.out.println("Transfer successful. Transferred " + amount + " to " + targetAccount.getAccountNumber());
    }

    private void updateBalanceInDatabase() throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, this.balance);
            stmt.setString(2, this.accountNumber);
            stmt.executeUpdate();
        }
    }

    public static BankAccount fetchAccountFromDatabase(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BankAccount(
                        rs.getString("account_number"),
                        rs.getString("account_holder_name"),
                        rs.getDouble("balance"),
                        rs.getString("account_type")
                );
            } else {
                throw new SQLException("Account not found: " + accountNumber);
            }
        }
    }

}


