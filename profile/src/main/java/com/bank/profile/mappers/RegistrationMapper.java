package com.bank.profile.mappers;

import com.bank.profile.dto.RegistrationDTO;
import com.bank.profile.entity.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegistrationMapper {
    RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

    RegistrationDTO toRegistrationDTO(Registration registration);

    Registration toRegistration(RegistrationDTO registrationDTO);
}
