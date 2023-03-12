package com.bank.transfer.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(of = "phoneNumber", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "phone_transfer")
public class PhoneTransfer extends AbstractTransfer{

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

}
