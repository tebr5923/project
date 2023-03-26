package com.bank.transfer.exception;

public class CardTransferNotFoundException extends RuntimeException {

    public CardTransferNotFoundException(String message) {
        super(message);
    }
}
