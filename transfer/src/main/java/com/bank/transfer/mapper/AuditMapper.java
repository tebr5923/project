package com.bank.transfer.mapper;

import com.bank.transfer.dto.audit.AuditDto;
import com.bank.transfer.entity.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuditMapper {

    AuditMapper MAPPER = Mappers.getMapper(AuditMapper.class);

    AuditDto toDTO(Audit audit);

    @Mapping(target = "id", ignore = true)
    Audit toEntity(AuditDto auditDTO);
}
