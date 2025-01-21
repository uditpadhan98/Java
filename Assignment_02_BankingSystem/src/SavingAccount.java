public class SavingAccount extends BankAccount{
    private double interestRate;

    public SavingAccount(String accountNumber,double balance,double interestRate){
        super(accountNumber,balance);
        this.interestRate=interestRate;
    }

    public void applyInterest(){
        double interest=balance*(interestRate/100);
        balance+=interest;
        System.out.println("Successfully applied interest of Rs."+interest);
    }

    @Override
    public String toString(){
        return super.toString()+", Interest Rate: "+interestRate;
    }
}
