package com.bank.transfer.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "account_transfer", schema = "transfer")
public class AccountTransfer extends AbstractTransfer {

    @Column(name = "account_number", nullable = false, unique = true)
    private Long accountNumber;

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ",amount:" + amount +
                ",purpose='" + purpose + '\'' +
                ",accountDetailsId:" + accountDetailsId +
                ",accountNumber:" + accountNumber +
                "}";
    }
}
