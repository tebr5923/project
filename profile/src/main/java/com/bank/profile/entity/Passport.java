package com.bank.profile.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity(name = "passport")
@Data
@Schema(name = "Passport", description = "Данные паспорта клиента.")
public class Passport {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "series")
    @NotBlank
    int series;
    @Column(name = "number")
    @NotBlank
    int number;
    @Column(name = "last_name")
    @NotBlank
    String lastName;
    @Column(name = "first_name")
    @NotBlank
    String firstName;
    @Column(name = "middle_name")
    String middleName;
    @Column(name = "gender",
            length = 3)
    @NotBlank
    String gender;
    @Column(name = "birth_date")
    @NotBlank
    LocalDate birthDate;
    @Column(name = "birth_place",
            length = 480)
    @NotBlank
    String birthPlace;
    @Lob
    @Column(name = "issued_by",
            columnDefinition = "TEXT")
    @NotBlank
    String issuedBy;
    @Column(name = "date_of_issue")
    @NotBlank
    LocalDate dateOfIssue;
    @Column(name = "division_code")
    @NotBlank
    int divisionCode;
    @Column(name = "expiration_date")
    LocalDate expirationDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_id")
    @NotBlank
    Registration registrationId;
}
