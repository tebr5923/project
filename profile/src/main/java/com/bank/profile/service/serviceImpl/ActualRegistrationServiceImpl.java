package com.bank.profile.service.serviceImpl;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.service.serviceInterface.ActualRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualRegistrationServiceImpl implements ActualRegistrationService {
    private final ActualRegistrationRepository actualRegistrationRepository;

    @Autowired
    public ActualRegistrationServiceImpl(ActualRegistrationRepository actualRegistrationRepository) {
        this.actualRegistrationRepository = actualRegistrationRepository;
    }

    @Override
    public void saveActualRegistration(ActualRegistration actualRegistration) {
        actualRegistrationRepository.save(actualRegistration);
    }

    @Override
    public ActualRegistration findActualRegistrationById(long actualRegistrationId) {
        return actualRegistrationRepository.findById(actualRegistrationId).orElseThrow(NullPointerException::new);
    }

    @Override
    public void editActualRegistration(Long id, ActualRegistration actualRegistration) {
        actualRegistration.setId(id);
        actualRegistrationRepository.save(actualRegistration);
    }

    @Override
    public void deleteActualRegistration(long actualRegistrationId) {
        actualRegistrationRepository.deleteById(actualRegistrationId);
    }

    @Override
    public List<ActualRegistration> getAllActualRegistration() {
        return actualRegistrationRepository.findAll();
    }
}
