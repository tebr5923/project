package com.bank.transfer.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class AccountTransferDTO extends AbstractTransferDTO {

    public AccountTransferDTO(BigDecimal amount, String purpose, Long accountDetailsId, Long accountNumber) {
        super(amount, purpose, accountDetailsId);
        this.accountNumber = accountNumber;
    }

    private Long accountNumber;

}
