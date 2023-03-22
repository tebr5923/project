package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDTO;
import com.bank.profile.entity.Profile;
import com.bank.profile.entity.audit.Audit;
import com.bank.profile.mappers.ProfileMapper;
import com.bank.profile.service.serviceInterface.AuditService;
import com.bank.profile.service.serviceInterface.ProfileService;
import com.bank.profile.entity.audit.OperationType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profiles")
@Tag(name = "ProfileController", description = "REST контролер для сущности Profile (банковский профиль), а так же связанных с Profile сущностями: Passport (паспорт) и ActualRegistration (адрес регистрации по паспорту).")
public class ProfileController {
    private final ProfileService profileService;
    private final AuditService auditService;

    @Autowired
    public ProfileController(ProfileService profileService, AuditService auditService) {
        this.profileService = profileService;
        this.auditService = auditService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Получение всех объектов Profile в формате ProfileDTO.",
            description = "Получение всех объектов ProfileDTO, в том числе связанных сущностей PassportDTO и ActualRegistrationDTO. В методе через stream.api каждый объект Profile приводится к ProfileDTO."
    )
    public ResponseEntity<List<ProfileDTO>> getAllProfile() {
        List<ProfileDTO> allProfileDTO;

        allProfileDTO = profileService
                .getAllProfile()
                .stream()
                .map(ProfileMapper.INSTANCE::toProfileDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(allProfileDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение конкретного объекта Profile в формате ProfileDTO, через его id.",
            description = "Получение объекта ProfileDTO, в том числе связанных сущностей PassportDTO и ActualRegistrationDTO, через profile.id."
    )
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) {
        ProfileDTO profileDTO = ProfileMapper
                .INSTANCE
                .toProfileDTO(profileService.findProfileById(id));

        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }


    @PostMapping("/")
    @Operation(
            summary = "Сохранение в бд нового объекта Profile.",
            description = "Сохранение в бд нового объекта Profile, а так же создание и сохранение объекта Audit."
    )
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        Audit audit = new Audit();
        audit.setEntityType(Profile.class.getName());
        audit.setOperationType(OperationType.Save_entity.toString());
        audit.setNewEntityJson("new entity json");
        audit.setEntityJson("entity json");

        profileService.saveProfile(
                ProfileMapper.INSTANCE.toProfile(profileDTO));
        auditService.saveAudit(audit);

        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление существующего объекта Profile.",
            description = "Обновление существующего объекта Profile, а так же создание и сохранение объекта Audit."
    )
    public ResponseEntity<ProfileDTO> editProfile(@PathVariable Long id,
                                                  @RequestBody ProfileDTO profileDTO) {

        Audit audit = new Audit();
        audit.setEntityType(Profile.class.getName());
        audit.setOperationType(OperationType.Update_entity.toString());
        audit.setNewEntityJson("new entity json");
        audit.setEntityJson("entity json");

        profileService.editProfile(id,
                ProfileMapper.INSTANCE.toProfile(profileDTO));
        auditService.saveAudit(audit);

        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление существующего объекта Profile.",
            description = "Удаление существующего объекта Profile, а так же создание и сохранение объекта Audit."
    )
    public ResponseEntity<HttpStatus> deleteProfile(@PathVariable Long id) {

        Audit audit = new Audit();
        audit.setEntityType(Profile.class.getName());
        audit.setOperationType(OperationType.Delete_entity.toString());
        audit.setNewEntityJson("new entity json");
        audit.setEntityJson("entity json");

        profileService.deleteProfile(id);
        auditService.saveAudit(audit);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
