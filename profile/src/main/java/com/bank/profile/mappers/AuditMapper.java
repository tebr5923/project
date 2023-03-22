package com.bank.profile.mappers;

import com.bank.profile.dto.AuditDTO;
import com.bank.profile.entity.audit.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuditMapper {

    AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);

    AuditDTO toAuditDTO(Audit audit);

    Audit toAudit(AuditDTO auditDTO);
}
