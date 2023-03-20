package com.bank.profile.service.serviceInterface;

import com.bank.profile.entity.Profile;

import java.util.List;


public interface ProfileService {
    void saveProfile(Profile profile);

    Profile findProfileById(long profileId);

    void editProfile(Long id, Profile profile);

    void deleteProfile(long profileId);

    List<Profile> getAllProfile();

}
