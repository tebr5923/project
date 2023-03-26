package com.bank.transfer.dto.transfer;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PatchAccountTransferDTO extends AbstractPatchTransferDTO{

    private Long accountNumber;

}
