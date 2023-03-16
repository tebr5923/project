package com.bank.transfer.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class AccountTransferDTO extends AbstractTransferDTO {

    @NotNull(message = "accountNumber must not be empty")
    private Long accountNumber;

}
