package com.bank.transfer.dto.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractPatchTransferDTO {

    protected BigDecimal amount;

    protected String purpose;

    protected Long accountDetailsId;

}
