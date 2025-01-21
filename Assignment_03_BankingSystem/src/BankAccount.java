public class BankAccount {
    private String accountNumber;
    private double balance;
    private String accountHolderName;

    public BankAccount(String accountNumber, double balance, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void deposit(double amount) throws TransactionException {
        if(amount<=0){
           throw new TransactionException("Deposit amount must be greater than zero");
        }
        balance += amount;
    }
    public void withdraw(double amount) throws TransactionException {
        if(amount<=0){
            throw new TransactionException("Withdraw amount must be greater tha zero");
        }
        if(balance<amount){
            throw new TransactionException("Insufficient balance");
        }
        balance -= amount;
    }

    public void displayBalance(){
        System.out.println("Balance is Rs."+balance);
    }
}
