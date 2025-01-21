public class CheckingAccount extends BankAccount{
    private double overdraftFee;

    public CheckingAccount(String accountNumber,double balance,double overdraftFee){
        super(accountNumber,balance);
        this.overdraftFee=overdraftFee;
    }

    @Override
    public void withdraw(double amount){
        if(amount>0){
            if(balance-amount<0){
                balance-=(amount+overdraftFee);
                System.out.println("Overdraft fee of Rs."+overdraftFee);
            }
            else{
                balance-=amount;
            }
            System.out.println("Successfully withdraw Rs."+amount);
        }
        else{
            System.out.println("Invalid amount");
        }
    }

    @Override
    public String toString(){
        return super.toString()+", Overdraft fee of Rs."+overdraftFee;
    }
}
