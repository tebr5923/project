package com.bank.profile.mappers;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.entity.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PassportMapper {
    PassportMapper INSTANCE = Mappers.getMapper(PassportMapper.class);

    @Mapping(target = "registration", source = "registrationId")
    PassportDTO toPassportDTO(Passport passport);

    @Mapping(target = "registrationId", source = "registration")
    Passport toPassport(PassportDTO passportDTO);
}
