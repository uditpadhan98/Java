import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String accountType;

    private final Lock lock = new ReentrantLock();

    public BankAccount(String accountNumber, String accountHolderName, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            if (amount <= 0) {
                System.out.println("Invalid deposit amount.");
                return;
            }
            balance += amount;
            System.out.println(Thread.currentThread().getName() + ": Deposited " + amount + ". New balance: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        lock.lock();
        try {
            if (amount > balance) {
                throw new InsufficientFundsException("Insufficient funds. Available balance: " + balance);
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + ": Withdrawn " + amount + ". Remaining balance: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public void transfer(BankAccount targetAccount, double amount) throws InsufficientFundsException {
        lock.lock();
        targetAccount.lock.lock();
        try {
            if (amount > balance) {
                throw new InsufficientFundsException("Insufficient funds for transfer.");
            }
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println(Thread.currentThread().getName() + ": Transferred " + amount + " to account " + targetAccount.getAccountNumber());
        } finally {
            targetAccount.lock.unlock();
            lock.unlock();
        }
    }

    public double checkBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
