public class SavingAccount extends BankAccount{
    private double interestRate;

    public SavingAccount(String accountNumber,String accountHolderName,double balance, double interestRate) {
        super(accountNumber,balance,accountHolderName);
        this.interestRate = interestRate;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public void calculateInterest(){
        double interest = interestRate * getBalance()/100;
        System.out.println("The interest is " + interest);
    }

    @Override
    public void withdraw(double amount) throws TransactionException{
        if(amount<50){
            System.out.println("Rs.5 will be charged since amount is less than 50");
            amount+=5;
        }
        super.withdraw(amount);
    }
}
