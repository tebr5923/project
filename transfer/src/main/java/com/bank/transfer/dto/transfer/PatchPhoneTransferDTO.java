package com.bank.transfer.dto.transfer;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class PatchPhoneTransferDTO extends AbstractPatchTransferDTO{

    private Long phoneNumber;

}
