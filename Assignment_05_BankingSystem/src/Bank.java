import java.util.HashMap;

public class Bank {
    private HashMap<String, BankAccount> accountsMap;

    public Bank() {
        accountsMap = new HashMap<>();
    }

    public void addAccount(BankAccount account) {
        accountsMap.put(account.getAccountNumber(), account);
    }

    public BankAccount getAccount(String accountNumber) throws InvalidAccountException {
        BankAccount account = accountsMap.get(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("Account not found: " + accountNumber);
        }
        return account;
    }
}
