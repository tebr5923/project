package com.bank.profile.service.serviceInterface;

import com.bank.profile.entity.ActualRegistration;

import java.util.List;

public interface ActualRegistrationService {
    void saveActualRegistration(ActualRegistration actualRegistration);

    ActualRegistration findActualRegistrationById(long actualRegistrationId);

    void editActualRegistration(Long id, ActualRegistration actualRegistration);

    void deleteActualRegistration(long actualRegistrationId);

    List<ActualRegistration> getAllActualRegistration();
}
