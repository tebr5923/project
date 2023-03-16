package com.bank.transfer.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractTransferDTO {

    @NotNull(message = "amount must not be empty")
    protected BigDecimal amount;

    protected String purpose;

    @NotNull(message = "accountDetailsId must not be empty")
    protected Long accountDetailsId;

}
