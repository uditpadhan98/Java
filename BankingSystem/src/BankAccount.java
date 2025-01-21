public class BankAccount {
    protected String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber,double balance){
        this.accountNumber=accountNumber;
        this.balance=balance;
    }

    public void deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("Successfully deposited Rs."+amount);
        }
        else{
            System.out.println("Invalid deposit amount...");
        }
    }

    public void withdraw(double amount){
        if(amount>0 && amount<=balance){
            balance-=amount;
            System.out.println("Successfully withdraw Rs."+amount);
        }
        else{
            System.out.println("Insufficient balance");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String toString(){
        return "Account Number: "+accountNumber+", Balance: Rs."+balance;
    }
}
