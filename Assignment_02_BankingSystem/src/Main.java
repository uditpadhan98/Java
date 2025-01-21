public class Main {
    public static void main(String[] args) {
        SavingAccount saving=new SavingAccount("Saving123",500.0,5.0);
        CheckingAccount checking=new CheckingAccount("Checking123",5000.0,10.0);
        BusinessAccount business=new BusinessAccount("Business123",5000.0,20.0);

        System.out.println("Saving Account");
        System.out.println(saving);
        saving.deposit(2000);
        saving.applyInterest();
        System.out.println("balance is Rs."+saving.getBalance());

        System.out.println("Checking account");
        System.out.println(checking);
        checking.withdraw(390);
        checking.deposit(234);
        System.out.println("Balance is Rs."+checking.getBalance());

        System.out.println("Business Account");
        System.out.println(business);
        business.applyMonthlyFee();
        business.withdraw(354);
        System.out.println("Balance is Rs."+business.getBalance());
    }
}