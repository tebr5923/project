package com.bank.profile.service.serviceImpl;

import com.bank.profile.entity.Registration;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.service.serviceInterface.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }


    @Override
    public void saveRegistration(Registration registration) {
        registrationRepository.save(registration);
    }

    @Override
    public Registration findRegistrationById(long registrationId) {
        return registrationRepository.findById(registrationId).orElseThrow(NullPointerException::new);
    }

    @Override
    public void editRegistration(Registration registration) {
        registrationRepository.save(registration);
    }

    @Override
    public void deleteRegistration(long registrationId) {
        registrationRepository.deleteById(registrationId);
    }

    @Override
    public List<Registration> getAllRegistration() {
        return registrationRepository.findAll();
    }
}
