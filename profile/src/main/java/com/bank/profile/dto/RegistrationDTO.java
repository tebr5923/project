package com.bank.profile.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationDTO {
    String country;
    String region;
    String city;
    String district;
    String locality;
    String street;
    String houseNumber;
    String houseBlock;
    String flatNumber;
    int index;
}
