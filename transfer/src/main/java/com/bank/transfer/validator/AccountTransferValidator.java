package com.bank.transfer.validator;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.service.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
        service.getByAccountNumber(accountTransfer.getAccountNumber())
                .ifPresent((value) -> errors.rejectValue(
                        "accountNumber",
                        String.format("accountNumber %s already exist!", value),
                        String.format("accountNumber %s already exist!", value))
                );
    }

}
