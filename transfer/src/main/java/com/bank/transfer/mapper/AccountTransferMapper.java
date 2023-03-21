package com.bank.transfer.mapper;

import com.bank.transfer.dto.transfer.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountTransferMapper {

    AccountTransferMapper MAPPER = Mappers.getMapper(AccountTransferMapper.class);

    AccountTransferDTO toDTO(AccountTransfer accountTransfer);

    @Mapping(target = "id", ignore = true)
    AccountTransfer toEntity(AccountTransferDTO accountTransferDTO);
}
