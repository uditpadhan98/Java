public class BusinessAccount extends BankAccount{
    private double monthlyFee;

    public BusinessAccount(String accountNumber,double balance,double monthlyFee){
        super(accountNumber,balance);
        this.monthlyFee=monthlyFee;
    }

    public void applyMonthlyFee(){
        balance-=monthlyFee;
        System.out.println("Monthly fee applied of Rs:"+monthlyFee);
    }

    @Override
    public String toString(){
        return super.toString()+", Monthly Fee applied if Rs."+monthlyFee;
    }
}
