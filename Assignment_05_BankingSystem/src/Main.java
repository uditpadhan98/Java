public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        TransactionProcessor processor = new TransactionProcessor(bank);

        // Create accounts
        BankAccount account1 = new BankAccount("A101", "John Doe", 1000, "Saving");
        BankAccount account2 = new BankAccount("A102", "Jane Smith", 2000, "Current");
        bank.addAccount(account1);
        bank.addAccount(account2);

        // Simulate transactions
        processor.processTransaction("Deposit", 500, "A101", null); // Deposit into A101
        processor.processTransaction("Withdraw", 300, "A102", null); // Withdraw from A102
        processor.processTransaction("Transfer", 200, "A101", "A102"); // Transfer from A101 to A102

        // Allow threads to complete
        try {
            Thread.sleep(2000); // Give threads time to finish for demo purposes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
