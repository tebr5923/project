package com.bank.profile.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PassportDTO {
    int series;
    int number;
    String lastName;
    String firstName;
    String middleName;
    String gender;
    LocalDate birthDate;
    String birthPlace;
    String issuedBy;
    LocalDate dateOfIssue;
    int divisionCode;
    LocalDate expirationDate;
    RegistrationDTO registration;
}
