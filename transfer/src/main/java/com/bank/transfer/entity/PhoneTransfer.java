package com.bank.transfer.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneTransfer extends AbstractTransfer{

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

}
