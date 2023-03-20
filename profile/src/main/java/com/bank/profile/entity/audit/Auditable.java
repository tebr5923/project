package com.bank.profile.entity.audit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@Schema(name = "Auditable", description = "Класс, наследующийся от AuditingEntityListener.class, для реализации сбора и сохранения информации об объектах.")
public abstract class Auditable<T> {
    @Column(name = "created_by")
    @NotBlank
    @CreatedBy
    protected T createdBy;

    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP")
    @NotBlank
    @CreatedDate
    protected LocalDateTime createdAt;

    @Column(name = "modified_by")
    @LastModifiedBy
    protected T modifiedBy;

    @Column(name = "modified_at",
            columnDefinition = "TIMESTAMP")
    @LastModifiedDate
    protected LocalDateTime modifiedAt;

}
