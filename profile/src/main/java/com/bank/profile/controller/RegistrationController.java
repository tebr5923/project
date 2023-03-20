package com.bank.profile.controller;

import com.bank.profile.dto.RegistrationDTO;
import com.bank.profile.entity.Registration;
import com.bank.profile.mappers.RegistrationMapper;
import com.bank.profile.service.serviceInterface.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/registrations")
@Tag(name = "RegistrationController", description = "REST контролер для сущности Registration (адрес регистрации по паспорту).")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Получение всех объектов Registration в формате RegistrationDTO.",
            description = "Получение всех объектов RegistrationDTO. В методе через stream.api каждый объект Registration приводится к RegistrationDTO."
    )
    public List<RegistrationDTO> getAllRegistration() {
        return registrationService.getAllRegistration().stream().map(RegistrationMapper.INSTANCE::toRegistrationDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение конкретного объекта Registration в формате RegistrationDTO, через его id.",
            description = "Получение объекта RegistrationDTO через registration.id."
    )
    public RegistrationDTO getRegistration(@PathVariable Long id) {
        return RegistrationMapper.INSTANCE.toRegistrationDTO(registrationService.findRegistrationById(id));
    }

    @PostMapping("/")
    @Operation(
            summary = "Сохранение в бд нового объекта Registration.",
            description = "Сохранение в бд нового объекта Registration."
    )
    public Registration createRegistration(@RequestBody Registration registration) {
        registrationService.saveRegistration(registration);
        return registration;
    }

    @PutMapping("/")
    @Operation(
            summary = "Обновление существующего объекта Registration.",
            description = "Обновление существующего объекта Registration."
    )
    public Registration editRegistration(@RequestBody Registration registration) {
        registrationService.editRegistration(registration);
        return registration;
    }

}
