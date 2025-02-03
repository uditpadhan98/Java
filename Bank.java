package com.bankingSystem;

import java.sql.SQLException;
import java.util.HashMap;

public class Bank {
    HashMap<String,BankAccount> mp=new HashMap<>();

    public void addAccount(BankAccount bankAccount){
        mp.put(bankAccount.getAccountNumber(),bankAccount);
    }

    public void removeAccount(String accountNumber){
        mp.remove(accountNumber);
    }

    public BankAccount findAccount(String accountNumber){
        return mp.get(accountNumber);
    }

    public synchronized void transferFunds(String sourceAccount,String targetAccount,double amount) throws SQLException, InsufficientFundsException {
        BankAccount sourceAccountNumber=findAccount(sourceAccount);
        BankAccount targetAccountNumber=findAccount(targetAccount);

        sourceAccountNumber.withdraw(amount);
        targetAccountNumber.deposit(amount);
    }
}
