package com.bank.profile.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "profile")
@Data
@Schema(name = "Profile", description = "Банковский профиль клиента.")
public class Profile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "phone_number")
    @NotBlank
    long phoneNumber;
    @Column(name = "email")
    String email;
    @Column(name = "name_on_card")
    String nameOnCard;
    @Column(name = "inn",
            unique = true)
    long inn;
    @Column(name = "snils",
            unique = true)
    long snils;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    @NotBlank
    Passport passportId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actual_registration_id")
    ActualRegistration actualRegistrationId;

}
