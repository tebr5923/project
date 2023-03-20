package com.bank.profile.service.serviceImpl;

import com.bank.profile.entity.Passport;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.service.serviceInterface.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    @Autowired
    public PassportServiceImpl(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @Override
    public void savePassport(Passport passport) {
        passportRepository.save(passport);
    }

    @Override
    public Passport findPassportById(long passportId) {
        return passportRepository.findById(passportId).orElseThrow(NullPointerException::new);
    }

    @Override
    public void editPassport(Long id, Passport passport) {
        passport.setId(id);
        passportRepository.save(passport);
    }

    @Override
    public void deletePassport(long passportId) {
        passportRepository.deleteById(passportId);
    }

    @Override
    public List<Passport> getAllPassport() {
        return passportRepository.findAll();
    }
}
