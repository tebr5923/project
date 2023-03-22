package com.bank.profile.service.serviceImpl;

import com.bank.profile.entity.Profile;
import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.serviceInterface.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public Profile findProfileById(long profileId) {
        return profileRepository.findById(profileId).get();
    }

    @Override
    public void editProfile(Long id, Profile profile) {
        profile.setId(id);
        profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(long profileId) {
        profileRepository.deleteById(profileId);
    }

    @Override
    public List<Profile> getAllProfile() {
        return profileRepository.findAll();
    }
}
