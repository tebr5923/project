package com.bank.transfer.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(of = "accountNumber", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account_transfer")
public class AccountTransfer extends AbstractTransfer{

    @Column(name = "account_number", nullable = false, unique = true)
    private Long accountNumber;

}
