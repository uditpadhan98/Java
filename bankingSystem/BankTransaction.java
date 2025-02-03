package com.bankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class BankTransaction {
    private String transactionID;
    private String transactionType; // Deposit, Withdraw, or Transfer
    private double amount;
    private String accountNumber;
    private LocalDateTime timestamp;

    public BankTransaction(String transactionType, double amount, String accountNumber) {
        this.transactionID = UUID.randomUUID().toString();
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.timestamp = LocalDateTime.now();
    }

    public void process() throws SQLException {
        BankAccount account = BankAccount.fetchAccountFromDatabase(accountNumber);
        logTransaction();
    }

    private void logTransaction() throws SQLException {
        String sql = "INSERT INTO transactions (transaction_id, transaction_type, amount, account_number, transaction_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transactionID);
            stmt.setString(2, transactionType);
            stmt.setDouble(3, amount);
            stmt.setString(4, accountNumber);
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(timestamp));
            stmt.executeUpdate();
        }
    }
}

