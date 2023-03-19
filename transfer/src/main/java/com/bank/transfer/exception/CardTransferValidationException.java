package com.bank.transfer.exception;

public class CardTransferValidationException extends RuntimeException {
    public CardTransferValidationException(String message) {
        super(message);
    }
}
