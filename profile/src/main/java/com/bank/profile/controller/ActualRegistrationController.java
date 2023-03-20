package com.bank.profile.controller;

import com.bank.profile.dto.ActualRegistrationDTO;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.mappers.ActualRegistrationMapper;
import com.bank.profile.service.serviceInterface.ActualRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actual_registrations")
@Tag(name = "ActualRegistration", description = "REST контролер для сущности ActualRegistration (адрес фактического проживания).")
public class ActualRegistrationController {

    private final ActualRegistrationService actualRegistrationService;

    public ActualRegistrationController(ActualRegistrationService actualRegistrationService) {
        this.actualRegistrationService = actualRegistrationService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Получение всех объектов ActualRegistration в формате ActualRegistrationDTO.",
            description = "Получение всех объектов ActualRegistrationDTO. В методе через stream.api каждый объект ActualRegistration приводится к ActualRegistrationDTO."
    )
    public List<ActualRegistrationDTO> getAllActualRegistration() {
        return actualRegistrationService.getAllActualRegistration().stream().map(ActualRegistrationMapper.INSTANCE::toActualRegistrationDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение конкретного объекта ActualRegistration в формате ActualRegistrationDTO, через его id.",
            description = "Получение объекта ActualRegistrationDTO через actualRegistration.id."
    )
    public ActualRegistrationDTO getActualRegistration(@PathVariable Long id) {
        return ActualRegistrationMapper.INSTANCE.toActualRegistrationDTO(actualRegistrationService.findActualRegistrationById(id));
    }

    @PostMapping("/")
    @Operation(
            summary = "Сохранение в бд нового объекта ActualRegistration.",
            description = "Сохранение в бд нового объекта ActualRegistration."
    )
    public ActualRegistration createActualRegistration(@RequestBody ActualRegistration actualRegistration) {
        actualRegistrationService.saveActualRegistration(actualRegistration);
        return actualRegistration;
    }

    @PutMapping("/")
    @Operation(
            summary = "Обновление существующего объекта ActualRegistration.",
            description = "Обновление существующего объекта ActualRegistration."
    )
    public ActualRegistration editActualRegistration(@RequestBody ActualRegistration actualRegistration) {
        actualRegistrationService.editActualRegistration(actualRegistration);
        return actualRegistration;
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление существующего объекта ActualRegistration.",
            description = "Удаление существующего объекта ActualRegistration через actualRegistration.id."
    )
    public ResponseEntity<HttpStatus> deleteActualRegistration(@PathVariable Long id) {
        actualRegistrationService.deleteActualRegistration(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
