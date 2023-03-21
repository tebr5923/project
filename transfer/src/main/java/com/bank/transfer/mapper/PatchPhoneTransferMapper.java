package com.bank.transfer.mapper;

import com.bank.transfer.dto.transfer.PatchPhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatchPhoneTransferMapper {

    PatchPhoneTransferMapper MAPPER = Mappers.getMapper(PatchPhoneTransferMapper.class);

    PatchPhoneTransferDTO toDTO(PhoneTransfer transfer);

    @Mapping(target = "id", ignore = true)
    PhoneTransfer toEntity(PatchPhoneTransferDTO dto);
}
