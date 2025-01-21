import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Money");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter account number: ");
                        String accountNumber = scanner.next();
                        System.out.print("Enter account holder name: ");
                        String holderName = scanner.next();
                        System.out.print("Enter initial balance: ");
                        double balance = scanner.nextDouble();
                        System.out.print("Enter interest rate (for savings account): ");
                        double interestRate = scanner.nextDouble();

                        SavingAccount savingsAccount = new SavingAccount(accountNumber, holderName, balance, interestRate);
                        bank.addAccount(savingsAccount);
                        System.out.println("Account created successfully.");
                        break;

                    case 2:
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.next();
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        BankAccount account = bank.findAccount(accountNumber);

                        if (account != null) {
                            account.deposit(depositAmount);
                            System.out.println("Deposit successful.");
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.next();
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        account = bank.findAccount(accountNumber);

                        if (account != null) {
                            account.withdraw(withdrawAmount);
                            System.out.println("Withdrawal successful.");
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.next();
                        account = bank.findAccount(accountNumber);

                        if (account != null) {
                            account.displayBalance();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter source account number: ");
                        String fromAccount = scanner.next();
                        System.out.print("Enter destination account number: ");
                        String toAccount = scanner.next();
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();

                        bank.transferMoney(fromAccount, toAccount, transferAmount);
                        break;

                    case 6:
                        System.out.println("Exiting program.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (TransactionException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}