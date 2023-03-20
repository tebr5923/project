package com.bank.profile.controller;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.entity.Passport;
import com.bank.profile.mappers.PassportMapper;
import com.bank.profile.service.serviceInterface.PassportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/passports")
@Tag(name = "PassportController", description = "REST контролер для сущности Passport (паспорт), а так же связанной с ней ActualRegistration (адрес регистрации по паспорту).")
public class PassportController {
    private final PassportService passportService;

    @Autowired
    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Получение всех объектов Passport в формате PassportDTO.",
            description = "Получение всех объектов PassportDTO, в том числе связанной сущности ActualRegistrationDTO. В методе через stream.api каждый объект Passport приводится к PassportDTO."
    )
    public List<PassportDTO> getAllPassport() {
        return passportService.getAllPassport().stream().map(PassportMapper.INSTANCE::toPassportDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение конкретного объекта Passport в формате PassportDTO, через его id.",
            description = "Получение объекта PassportDTO, в том числе связанной сущности ActualRegistrationDTO, через passport.id."
    )
    public PassportDTO getPassport(@PathVariable Long id) {
        return PassportMapper.INSTANCE.toPassportDTO(passportService.findPassportById(id));
    }

    @PostMapping("/")
    @Operation(
            summary = "Сохранение в бд нового объекта Passport.",
            description = "Сохранение в бд нового объекта Passport."
    )
    public Passport createPassport(@RequestBody Passport passport) {
        passportService.savePassport(passport);
        return passport;
    }

    @PutMapping("/")
    @Operation(
            summary = "Обновление существующего объекта Passport.",
            description = "Обновление существующего объекта Passport."
    )
    public Passport editPassport(@RequestBody Passport passport) {
        passportService.editPassport(passport);
        return passport;
    }

}
