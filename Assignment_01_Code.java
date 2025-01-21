import java.util.*;

public class BankingSystem {
    public static void main(String[] arg){
        Scanner sc=new Scanner(System.in);
        int balance=0;

        boolean run=true;

        while(run){
            System.out.println("Banking Menu:");
            System.out.println("1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.Check Balance");
            System.out.println("4.Exit");
            System.out.println("Choose an option from 1 to 4");

            int option=sc.nextInt();

            switch (option){
                case 1:
                    System.out.println("Enter amount to deposit:");
                    int amount=sc.nextInt();
                    if(amount>0){
                        balance+=amount;
                        System.out.println("Successfully deposited Rs."+amount);
                    }
                    else{
                        System.out.println("Invalid input!");
                    }
                    break;

                case 2:
                    System.out.println("Enter amount to withdraw:");
                    int withdraw=sc.nextInt();
                    if(withdraw>balance){
                        System.out.println("Insufficient balance!");
                    }
                    else{
                        balance-=withdraw;
                        System.out.println("Successfully withdraw Rs."+withdraw);
                    }
                    break;

                case 3:
                    System.out.println("Your balance is Rs."+balance);
                    break;

                case 4:
                    System.out.println("Successfully Exited!!");
                    run=false;
                    break;

                default:
                    System.out.println("Please choose a valid option");
            }
        }
    }
}
