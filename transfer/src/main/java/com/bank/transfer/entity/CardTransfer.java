package com.bank.transfer.entity;

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
@Table(name = "card_transfer", schema = "transfer")
public class CardTransfer extends AbstractTransfer{

    @Column(name = "card_number", nullable = false, unique = true)
    private Long cardNumber;

}
