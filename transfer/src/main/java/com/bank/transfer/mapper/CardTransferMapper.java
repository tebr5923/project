package com.bank.transfer.mapper;

import com.bank.transfer.dto.transfer.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardTransferMapper {

    CardTransferMapper MAPPER = Mappers.getMapper(CardTransferMapper.class);

    CardTransferDTO toDTO(CardTransfer transfer);

    @Mapping(target = "id", ignore = true)
    CardTransfer toEntity(CardTransferDTO dto);
}
