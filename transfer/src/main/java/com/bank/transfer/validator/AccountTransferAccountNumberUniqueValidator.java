package com.bank.transfer.validator;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class AccountTransferAccountNumberUniqueValidator implements Validator {

    private final TransferService<AccountTransfer> service;

    @Autowired
    public AccountTransferAccountNumberUniqueValidator(TransferService<AccountTransfer> service) {
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
        service.getByNumber(accountTransfer.getAccountNumber())
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
        //if its save and id is null - its not same transfer
        if (validated.getId() == null) {
            return false;
        }
        return validated.getId().equals(fromDB.getId());
    }

}
