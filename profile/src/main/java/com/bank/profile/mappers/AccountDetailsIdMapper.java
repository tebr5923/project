package com.bank.profile.mappers;

import com.bank.profile.dto.AccountDetailsIdDTO;
import com.bank.profile.entity.AccountDetailsId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDetailsIdMapper {
    AccountDetailsIdMapper INSTANCE = Mappers.getMapper(AccountDetailsIdMapper.class);

    @Mapping(target = "profile.passport", ignore = true)
    @Mapping(target = "profile.actualRegistration", ignore = true)
    AccountDetailsIdDTO toAccountDetailsIdDTO(AccountDetailsId accountDetailsId);


    AccountDetailsId toAccountDetailsId(AccountDetailsIdDTO accountDetailsIdDTO);
}
