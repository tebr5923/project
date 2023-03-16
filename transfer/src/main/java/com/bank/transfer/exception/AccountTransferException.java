package com.bank.transfer.exception;

public class AccountTransferException extends RuntimeException{
    public AccountTransferException(String message) {
        super(message);
    }
}
