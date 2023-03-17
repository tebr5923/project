package com.bank.transfer.dto;


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
public class PatchAccountTransferDTO {

    private BigDecimal amount;

    private String purpose;

    private Long accountDetailsId;

    private Long accountNumber;

}
