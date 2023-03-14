package com.bank.transfer.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class AbstractTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "amount", nullable = false)
    protected Float amount;

    @Column(name = "purpose")
    protected String purpose;

    @Column(name = "account_details_id", nullable = false)
    protected Long accountDetailsId;

}
