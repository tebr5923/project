package com.bank.profile.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity(name = "account_details_id")
@Data
@Schema(name = "AccountDetailsId", description = "Маппинг профиля клиента со всеми его счетами.")
public class AccountDetailsId {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "account_id")
    @NotBlank
    int accountId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    Profile profile;

}
