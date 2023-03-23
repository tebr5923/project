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
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneTransfer extends AbstractTransfer{

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ",amount=" + amount +
                ",purpose='" + purpose + '\'' +
                ",accountDetailsId:" + accountDetailsId +
                ",phoneNumber:" + phoneNumber +
                '}';
    }
}
