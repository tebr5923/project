package com.bank.transfer.mapper;

import com.bank.transfer.dto.transfer.AbstractTransferDTO;
import com.bank.transfer.entity.AbstractTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransferMapper<Entity extends AbstractTransfer, DTO extends AbstractTransferDTO> {

    TransferMapper<?, ?> MAPPER = Mappers.getMapper(TransferMapper.class);

    DTO ToDTO(Entity transfer);

    @Mapping(target = "id", ignore = true)
    Entity ToEntity(DTO dto);
}
