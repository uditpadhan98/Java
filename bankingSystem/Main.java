package com.bankingSystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5); // Thread pool with 5 threads

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Banking System!");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        executor.submit(() -> {
                            try {
                                addNewAccount(scanner);
                            } catch (SQLException e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                        });
                        break;
                    case 2:
                        executor.submit(() -> {
                            try {
                                depositMoney(scanner);
                            } catch (SQLException e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                        });
                        break;
                    case 3:
                        executor.submit(() -> {
                            try {
                                withdrawMoney(scanner);
                            } catch (SQLException | InsufficientFundsException e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                        });
                        break;
                    case 4:
                        executor.submit(() -> {
                            try {
                                transferMoney(scanner);
                            } catch (SQLException | InsufficientFundsException | InvalidAccountException e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                        });
                        break;
                    case 5:
                        System.out.println("Exiting the Banking System. Have a great day!");
                        executor.shutdown(); // Gracefully shutdown the thread pool
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void addNewAccount(Scanner scanner) throws SQLException {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter account type: (Saving)");
        String accountType = scanner.nextLine();

        try (Connection conn = DataBaseUtil.getConnection()) {
            String sql = "INSERT INTO accounts (account_number, account_holder_name, balance, account_type) VALUES (?, ?, ?, ?)";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNumber);
            stmt.setString(2, accountHolderName);
            stmt.setDouble(3, balance);
            stmt.setString(4, accountType);
            stmt.executeUpdate();
            System.out.println("Account created successfully!");
        }
    }

    private static void depositMoney(Scanner scanner) throws SQLException {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        BankTransaction deposit = new BankTransaction("Deposit", amount, accountNumber);
        deposit.process();
    }

    private static void withdrawMoney(Scanner scanner) throws SQLException, InsufficientFundsException {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        BankTransaction withdraw = new BankTransaction("Withdraw", amount, accountNumber);
        withdraw.process();
    }

    private static void transferMoney(Scanner scanner) throws SQLException, InsufficientFundsException, InvalidAccountException {
        System.out.print("Enter your account number: ");
        String sourceAccountNumber = scanner.nextLine();
        System.out.print("Enter target account number: ");
        String targetAccountNumber = scanner.nextLine();
        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        BankTransaction sourceTransaction = new BankTransaction("Transfer to " + targetAccountNumber, amount, sourceAccountNumber);
        sourceTransaction.process();

        BankTransaction targetTransaction = new BankTransaction("Transfer from " + sourceAccountNumber, amount, targetAccountNumber);
        targetTransaction.process();
    }
}
