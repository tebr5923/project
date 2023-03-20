package com.bank.profile.dto;

import lombok.Data;

@Data
public class RegistrationDTO {
    String country;
    String city;
    String district;
    String street;
    String houseNumber;
    String flatNumber;
    int index;
}
