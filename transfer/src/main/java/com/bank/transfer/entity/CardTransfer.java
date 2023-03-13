package com.bank.transfer.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(of = "cardNumber", callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "card_transfer")
public class CardTransfer extends AbstractTransfer{

    @Column(name = "card_number", nullable = false, unique = true)
    private Long cardNumber;


}
