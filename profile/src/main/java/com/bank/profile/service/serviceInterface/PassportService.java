package com.bank.profile.service.serviceInterface;

import com.bank.profile.entity.Passport;

import java.util.List;

public interface PassportService {
    void savePassport(Passport passport);

    Passport findPassportById(long passportId);

    void editPassport(Passport passport);

    void deletePassport(long passportId);

    List<Passport> getAllPassport();
}
