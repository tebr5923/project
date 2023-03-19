package com.bank.transfer.validator;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.service.AccountTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class AccountTransferValidator implements Validator {

    private final AccountTransferService service;

    @Autowired
    public AccountTransferValidator(AccountTransferService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountTransfer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountTransfer accountTransfer = (AccountTransfer) target;
        log.info("try to validate accountTransfer: {}", accountTransfer);
        service.getByAccountNumber(accountTransfer.getAccountNumber())
                .ifPresent((value) -> validateAccountNumber(accountTransfer, value, errors)
                );
        log.info("success validate accountTransfer: {}", accountTransfer);
    }

    private void validateAccountNumber(AccountTransfer validated, AccountTransfer fromDB, Errors errors) {
        if (!isSameTransfer(validated, fromDB)) {
            errors.rejectValue(
                    "accountNumber",
                    String.format("accountNumber %s already exist!", fromDB.getAccountNumber()),
                    String.format("accountNumber %s already exist!", fromDB.getAccountNumber()));
        }
    }

    private boolean isSameTransfer(AccountTransfer validated, AccountTransfer fromDB) {
        return validated.getId().equals(fromDB.getId());
    }

}
