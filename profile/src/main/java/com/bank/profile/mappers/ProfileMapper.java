package com.bank.profile.mappers;

import com.bank.profile.dto.ProfileDTO;
import com.bank.profile.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    @Mapping(target = "passport", source = "passportId")
    @Mapping(target = "passport.registration", source = "passportId.registrationId")
    ProfileDTO toProfileDTO(Profile profile);
    @Mapping(target = "passportId", source = "passport")
    @Mapping(target = "passportId.registrationId", source = "passport.registration")
    Profile toProfile(ProfileDTO profileDTO);
}
