package com.bank.profile.mappers;

import com.bank.profile.dto.ActualRegistrationDTO;
import com.bank.profile.entity.ActualRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActualRegistrationMapper {
    ActualRegistrationMapper INSTANCE = Mappers.getMapper(ActualRegistrationMapper.class);

    ActualRegistrationDTO toActualRegistrationDTO(ActualRegistration actualRegistration);

    ActualRegistration toActualRegistration(ActualRegistrationDTO actualRegistrationDTO);
}
