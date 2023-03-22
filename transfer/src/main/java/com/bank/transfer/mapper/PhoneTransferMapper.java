package com.bank.transfer.mapper;

import com.bank.transfer.dto.transfer.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhoneTransferMapper {

    PhoneTransferMapper MAPPER = Mappers.getMapper(PhoneTransferMapper.class);

    PhoneTransferDTO toDTO(PhoneTransfer transfer);

    @Mapping(target = "id", ignore = true)
    PhoneTransfer toEntity(PhoneTransferDTO dto);
}
