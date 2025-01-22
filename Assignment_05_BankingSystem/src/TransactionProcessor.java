public class TransactionProcessor {
    private Bank bank;

    public TransactionProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processTransaction(String transactionType, double amount, String sourceAccount, String targetAccount) {
        new Thread(() -> {
            try {
                BankAccount source = bank.getAccount(sourceAccount);
                switch (transactionType) {
                    case "Deposit":
                        source.deposit(amount);
                        break;
                    case "Withdraw":
                        source.withdraw(amount);
                        break;
                    case "Transfer":
                        BankAccount target = bank.getAccount(targetAccount);
                        source.transfer(target, amount);
                        break;
                    default:
                        throw new InvalidTransactionException("Invalid transaction type.");
                }
            } catch (Exception e) {
                System.out.println("Transaction failed: " + e.getMessage());
            }
        }).start();
    }
}
