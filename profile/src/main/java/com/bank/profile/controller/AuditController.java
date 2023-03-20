package com.bank.profile.controller;

import com.bank.profile.dto.AuditDTO;
import com.bank.profile.entity.audit.Audit;
import com.bank.profile.mappers.AuditMapper;
import com.bank.profile.service.serviceInterface.AuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audits")
@Tag(name = "AuditController", description = "REST контролер для сущности Audit (объект Audit представляет собой запись CRUD операции с основными сущностями).")
public class AuditController {
    private final AuditService auditService;

    @Autowired
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Получение всех объектов Audit в формате AuditDTO.",
            description = "Получение всех объектов AuditDTO. В методе через stream.api каждый объект Audit приводится к AuditDTO."
    )
    public List<AuditDTO> getAllAudit() {
        return auditService.getAllAudit().stream().map(AuditMapper.INSTANCE::toAuditDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение конкретного объекта Audit в формате AuditDTO, через его id.",
            description = "Получение объекта AuditDTO через audit.id."
    )
    public AuditDTO getAudit(@PathVariable Long id) {
        return AuditMapper.INSTANCE.toAuditDTO(auditService.findAuditById(id));
    }

    @PostMapping("/")
    @Operation(
            summary = "Сохранение в бд нового объекта Audit.",
            description = "Сохранение в бд нового объекта Audit."
    )
    public Audit createAudit(@RequestBody Audit audit) {
        auditService.saveAudit(audit);
        return audit;
    }

    @PutMapping("/")
    @Operation(
            summary = "Обновление существующего объекта Audit.",
            description = "Обновление существующего объекта Audit."
    )
    public Audit editAudit(@RequestBody Audit audit) {
        auditService.editAudit(audit);
        return audit;
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление существующего объекта Audit.",
            description = "Удаление существующего объекта Audit через audit.id."
    )
    public ResponseEntity<HttpStatus> deleteAudit(@PathVariable Long id) {
        auditService.deleteAudit(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}