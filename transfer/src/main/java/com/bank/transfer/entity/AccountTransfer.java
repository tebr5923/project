package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account_transfer", schema = "transfer")
public class AccountTransfer extends AbstractTransfer{

    public AccountTransfer(Long id, BigDecimal amount, String purpose, Long accountDetailsId, Long accountNumber) {
        super(id, amount, purpose, accountDetailsId);
        this.accountNumber = accountNumber;
    }

    @Column(name = "account_number", nullable = false, unique = true)
    private Long accountNumber;

}
