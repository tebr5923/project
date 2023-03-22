package com.bank.transfer.handler;

import com.bank.transfer.exception.AccountTransferValidationException;
import com.bank.transfer.exception.AccountTransferNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class AccountTransferControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountTransferNotFoundException.class)
    public ResponseEntity<String> handleAccountTransferNotFoundException(AccountTransferNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountTransferValidationException.class)
    public ResponseEntity<String> handleAccountTransferException(AccountTransferValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
