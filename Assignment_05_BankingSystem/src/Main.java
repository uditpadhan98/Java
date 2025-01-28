public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        TransactionProcessor processor = new TransactionProcessor(bank);

        // Create accounts
        BankAccount account1 = new BankAccount("123", "Udit", 3000, "Saving");
        BankAccount account2 = new BankAccount("234", "Sachin", 2000, "Current");
        bank.addAccount(account1);
        bank.addAccount(account2);

        // Simulate transactions
        processor.processTransaction("Deposit", 500, "123", null);
        processor.processTransaction("Withdraw", 200, "123", null);
        processor.processTransaction("Transfer", 500, "123", "234");

        // Allow threads to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
