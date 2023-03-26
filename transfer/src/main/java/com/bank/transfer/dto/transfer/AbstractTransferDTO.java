package com.bank.transfer.dto.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractTransferDTO {

    @NotNull(message = "amount must not be empty")
    protected BigDecimal amount;

    protected String purpose;

    @NotNull(message = "accountDetailsId must not be empty")
    protected Long accountDetailsId;

}
