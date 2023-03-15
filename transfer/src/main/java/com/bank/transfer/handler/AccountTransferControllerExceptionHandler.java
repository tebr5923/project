package com.bank.transfer.handler;

import com.bank.transfer.exception.AccountTransferNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AccountTransferControllerExceptionHandler {

    @ExceptionHandler(AccountTransferNotFoundException.class)
    public ResponseEntity<String> handleAccountTransferNotFoundException(AccountTransferNotFoundException e) {
        log.error("AccountTransfer not found", e);
        return new ResponseEntity<>("AccountTransfer not found", HttpStatus.NOT_FOUND);
    }

}
