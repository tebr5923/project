package com.bank.transfer.mapper;

import com.bank.transfer.dto.transfer.AbstractPatchTransferDTO;
import com.bank.transfer.entity.AbstractTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatchTransferMapper<Entity extends AbstractTransfer, DTO extends AbstractPatchTransferDTO> {

    PatchTransferMapper<?, ?> MAPPER = Mappers.getMapper(PatchTransferMapper.class);

    DTO ToDTO(Entity transfer);

    @Mapping(target = "id", ignore = true)
    Entity ToEntity(DTO dto);
}
