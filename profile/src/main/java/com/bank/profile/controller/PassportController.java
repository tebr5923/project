package com.bank.profile.controller;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.mappers.PassportMapper;
import com.bank.profile.service.serviceInterface.PassportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PassportDTO>> getAllPassport() {
        List<PassportDTO> allPassportDTO;

        allPassportDTO = passportService
                .getAllPassport()
                .stream()
                .map(PassportMapper.INSTANCE::toPassportDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(allPassportDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение конкретного объекта Passport в формате PassportDTO, через его id.",
            description = "Получение объекта PassportDTO, в том числе связанной сущности ActualRegistrationDTO, через passport.id."
    )
    public ResponseEntity<PassportDTO> getPassport(@PathVariable Long id) {
        PassportDTO passportDTO = PassportMapper
                .INSTANCE
                .toPassportDTO(passportService.findPassportById(id));

        return new ResponseEntity<>(passportDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(
            summary = "Сохранение в бд нового объекта Passport.",
            description = "Сохранение в бд нового объекта Passport."
    )
    public ResponseEntity<PassportDTO> createPassport(@RequestBody PassportDTO passportDTO) {

        passportService.savePassport(
                PassportMapper.INSTANCE.toPassport(passportDTO));

        return new ResponseEntity<>(passportDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление существующего объекта Passport.",
            description = "Обновление существующего объекта Passport."
    )
    public ResponseEntity<PassportDTO> editPassport(@PathVariable Long id,
                                                    @RequestBody PassportDTO passportDTO) {
        passportService.editPassport(id,
                PassportMapper.INSTANCE.toPassport(passportDTO));

        return new ResponseEntity<>(passportDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление существующего объекта Passport.",
            description = "Удаление существующего объекта Passport."
    )
    public ResponseEntity<HttpStatus> deleteRegistration(@PathVariable Long id) {
        passportService.deletePassport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
