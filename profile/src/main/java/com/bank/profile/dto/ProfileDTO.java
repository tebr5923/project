package com.bank.profile.dto;

import com.bank.profile.entity.ActualRegistration;
import lombok.Data;

@Data
public class ProfileDTO {
    long phoneNumber;
    String email;
    String nameOnCard;
    long inn;
    long snils;
    PassportDTO passport;
    ActualRegistrationDTO actualRegistration;

}
