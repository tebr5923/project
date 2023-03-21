package com.bank.transfer.mapper;

import com.bank.transfer.dto.transfer.PatchCardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatchCardTransferMapper {

    PatchCardTransferMapper MAPPER = Mappers.getMapper(PatchCardTransferMapper.class);

    PatchCardTransferDTO toDTO(CardTransfer transfer);

    @Mapping(target = "id", ignore = true)
    CardTransfer toEntity(PatchCardTransferDTO dto);
}
