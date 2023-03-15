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
@Table(name = "card_transfer")
public class CardTransfer extends AbstractTransfer{

    public CardTransfer(Long id, BigDecimal amount, String purpose, Long accountDetailsId, Long cardNumber) {
        super(id, amount, purpose, accountDetailsId);
        this.cardNumber = cardNumber;
    }

    @Column(name = "card_number", nullable = false, unique = true)
    private Long cardNumber;

}
