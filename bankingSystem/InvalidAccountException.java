package com.bankingSystem;

public class InvalidAccountException extends Exception{
    public InvalidAccountException(String message){
        super(message);
    }
}
