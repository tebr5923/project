package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountTransferMapper {

    AccountTransferMapper MAPPER = Mappers.getMapper(AccountTransferMapper.class);

    AccountTransferDTO ToDTO(AccountTransfer accountTransfer);

    AccountTransfer ToEntity(AccountTransferDTO accountTransferDTO);
}
