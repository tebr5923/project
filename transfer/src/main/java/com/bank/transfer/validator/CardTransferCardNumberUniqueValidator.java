package com.bank.transfer.validator;

import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class CardTransferCardNumberUniqueValidator implements Validator {

    private final TransferService<CardTransfer> service;

    @Autowired
    public CardTransferCardNumberUniqueValidator(TransferService<CardTransfer> service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CardTransfer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CardTransfer cardTransfer = (CardTransfer) target;
        log.info("try to validate cardTransfer: {}", cardTransfer);

        service.getByNumber(cardTransfer.getCardNumber())
                .ifPresent((value) -> validateCardNumber(cardTransfer, value, errors)
                );
        log.info("success validate cardTransfer: {}", cardTransfer);
    }

    private void validateCardNumber(CardTransfer validated, CardTransfer fromDB, Errors errors) {
        if (!isSameTransfer(validated, fromDB)) {
            errors.rejectValue(
                    "cardNumber",
                    String.format("cardNumber %s already exist!", fromDB.getCardNumber()),
                    String.format("cardNumber %s already exist!", fromDB.getCardNumber()));
        }
    }

    private boolean isSameTransfer(CardTransfer validated, CardTransfer fromDB) {
        //if its save and id is null - its not same transfer
        if (validated.getId() == null) {
            return false;
        }
        return validated.getId().equals(fromDB.getId());
    }

}


