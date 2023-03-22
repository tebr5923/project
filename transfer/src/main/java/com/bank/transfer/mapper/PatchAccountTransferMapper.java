package com.bank.transfer.mapper;

import com.bank.transfer.dto.transfer.PatchAccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatchAccountTransferMapper {

    PatchAccountTransferMapper MAPPER = Mappers.getMapper(PatchAccountTransferMapper.class);

    PatchAccountTransferDTO toDTO(AccountTransfer accountTransfer);

    @Mapping(target = "id", ignore = true)
    AccountTransfer toEntity(PatchAccountTransferDTO accountTransferDTO);
}
