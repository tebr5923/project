package com.bank.profile.dto;

import lombok.Data;

@Data
public class ProfileDTO {
    long phoneNumber;
    String email;
    String nameOnCard;
    long inn;
    long snils;
    PassportDTO passport;

}
