package com.bank.profile.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditDTO {
    String entityType;
    String operationType;
    String createdBy;
    LocalDateTime createdAt;
    String modifiedBy;
    LocalDateTime modifiedAt;
    String newEntityJson;
    String entityJson;
}
