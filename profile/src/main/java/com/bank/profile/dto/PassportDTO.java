package com.bank.profile.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PassportDTO {
    String lastName;
    String firstName;
    String middleName;
    String gender;
    LocalDate birthDate;
    RegistrationDTO registration;
}
