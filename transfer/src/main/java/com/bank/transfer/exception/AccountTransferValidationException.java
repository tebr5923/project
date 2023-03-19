package com.bank.transfer.exception;

public class AccountTransferValidationException extends RuntimeException {
    public AccountTransferValidationException(String message) {
        super(message);
    }
}
