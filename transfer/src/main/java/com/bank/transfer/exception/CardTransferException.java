package com.bank.transfer.exception;

public class CardTransferException extends RuntimeException {
    public CardTransferException(String message) {
        super(message);
    }
}
