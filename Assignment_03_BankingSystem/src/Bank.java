import java.util.*;
public class Bank {
    private Map<String,BankAccount> accounts=new HashMap<>();

    public void addAccount(BankAccount account){
        accounts.put(account.getAccountNumber(),account);
    }

    public void removeAccount(String accountNumber){
        accounts.remove(accountNumber);
    }

    public BankAccount findAccount(String accountNumber){
        return accounts.get(accountNumber);
    }

    public void transferMoney(String fromAccountNumber, String toAccountNumber, double amount) throws TransactionException{
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);

        if(fromAccount==null || toAccount==null){
            throw new TransactionException("Account does not exist");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        System.out.println("Transfer successful");
    }
}
