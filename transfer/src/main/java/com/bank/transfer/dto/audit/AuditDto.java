package com.bank.transfer.dto.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {

    @NotNull(message = "entityType must not be empty")
    private String entityType;

    @NotNull(message = "operationType must not be empty")
    private String operationType;

    @NotNull(message = "createdBy must not be empty")
    private String createdBy;

    private String modifiedBy;

    @NotNull(message = "createdAt must not be empty")
    private OffsetDateTime createdAt;

    private OffsetDateTime modifiedAt;

    private String newEntityJson;

    private String entityJson;

}
