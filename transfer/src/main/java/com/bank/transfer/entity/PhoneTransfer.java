package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneTransfer extends AbstractTransfer{

    public PhoneTransfer(Long id, BigDecimal amount, String purpose, Long accountDetailsId, Long phoneNumber) {
        super(id, amount, purpose, accountDetailsId);
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

}
