package com.bank.transfer.dto.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractPatchTransferDTO {

    protected BigDecimal amount;

    protected String purpose;

    protected Long accountDetailsId;

}
