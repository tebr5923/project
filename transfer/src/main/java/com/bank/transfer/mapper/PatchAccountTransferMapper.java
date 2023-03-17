package com.bank.transfer.mapper;

import com.bank.transfer.dto.PatchAccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatchAccountTransferMapper {

    PatchAccountTransferMapper MAPPER = Mappers.getMapper(PatchAccountTransferMapper.class);

    PatchAccountTransferDTO ToDTO(AccountTransfer accountTransfer);

    @Mapping(target = "id", ignore = true)
    AccountTransfer ToEntity(PatchAccountTransferDTO accountTransferDTO);
}
