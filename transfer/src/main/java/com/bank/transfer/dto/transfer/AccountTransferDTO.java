package com.bank.transfer.dto.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@ToString
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountTransferDTO extends AbstractTransferDTO {

    @NotNull(message = "accountNumber must not be empty")
    private Long accountNumber;

}
