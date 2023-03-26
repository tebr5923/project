package com.bank.transfer.exception;

public class AccountTransferNotFoundException extends RuntimeException {

    public AccountTransferNotFoundException(String message) {
        super(message);
    }
}
