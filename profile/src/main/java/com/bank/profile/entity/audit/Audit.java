package com.bank.profile.entity.audit;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity(name = "audit")
@Data
@Schema(name = "Audit", description = "Сущность, объекты которой сохраняют в бд все CRUD операции с основными сущностями. Наследуется от абстрактного класса Auditable.")
public class Audit extends Auditable<String> implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "entity_type",
            length = 40)
    @NotBlank
    String entityType;
    @Column(name = "operation_type")
    @NotBlank
    String operationType;
    @Column(name = "new_entity_json",
            columnDefinition = "TEXT")
    String newEntityJson;
    @Column(name = "entity_json",
            columnDefinition = "TEXT")
    @NotBlank
    String entityJson;

}
