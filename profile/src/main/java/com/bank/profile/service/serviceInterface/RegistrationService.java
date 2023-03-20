package com.bank.profile.service.serviceInterface;

import com.bank.profile.entity.Registration;

import java.util.List;


public interface RegistrationService {
    void saveRegistration(Registration registration);

    Registration findRegistrationById(long registrationId);

    void editRegistration(Registration registration);

    void deleteRegistration(long registrationId);

    List<Registration> getAllRegistration();
}
