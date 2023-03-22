package com.bank.profile.controller;

import com.bank.profile.dto.AccountDetailsIdDTO;
import com.bank.profile.entity.AccountDetailsId;
import com.bank.profile.mappers.AccountDetailsIdMapper;
import com.bank.profile.service.serviceInterface.AccountDetailsIdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account_details_id")
@Tag(name = "AccountDetailsId", description = "REST контролер для сущности AccountDetailsId (маппинг профиля клиента и его счетов).")
public class AccountDetailsIdController {
    private final AccountDetailsIdService accountDetailsIdService;

    @Autowired
    public AccountDetailsIdController(AccountDetailsIdService accountDetailsIdService) {
        this.accountDetailsIdService = accountDetailsIdService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Получение всех объектов AccountDetailsId.",
            description = "Получение всех объектов AccountDetailsId."
    )
    public ResponseEntity<List<AccountDetailsIdDTO>> getAllAccountDetailsId() {
        List<AccountDetailsIdDTO> allAccountDetailsIdDTO;

        allAccountDetailsIdDTO = accountDetailsIdService
                .getAllAccountDetailsId()
                .stream()
                .map(AccountDetailsIdMapper.INSTANCE::toAccountDetailsIdDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(allAccountDetailsIdDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение конкретного объекта AccountDetailsId.",
            description = "Получение конкретного объекта AccountDetailsId через accountDetailsId.id."
    )
    public ResponseEntity<AccountDetailsIdDTO> getAccountDetailsId(@PathVariable Long id) {
        AccountDetailsIdDTO accountDetailsIdDTO = AccountDetailsIdMapper
                .INSTANCE
                .toAccountDetailsIdDTO(accountDetailsIdService.findAccountDetailsIdById(id));

        return new ResponseEntity<>(accountDetailsIdDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(
            summary = "Сохранение в бд нового объекта AccountDetailsId.",
            description = "Сохранение в бд нового объекта AccountDetailsId."
    )
    public ResponseEntity<AccountDetailsIdDTO> createAccountDetailsId(@RequestBody AccountDetailsIdDTO accountDetailsIdDTO) {

        accountDetailsIdService.saveAccountDetailsId(
                AccountDetailsIdMapper.INSTANCE.toAccountDetailsId(accountDetailsIdDTO));

        return new ResponseEntity<>(accountDetailsIdDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление существующего объекта AccountDetailsId.",
            description = "Обновление существующего объекта AccountDetailsId."
    )
    public ResponseEntity<AccountDetailsIdDTO> editAccountDetailsId(@PathVariable Long id,
                                                                    @RequestBody AccountDetailsIdDTO accountDetailsIdDTO) {
        accountDetailsIdService.editAccountDetailsId(id,
                AccountDetailsIdMapper.INSTANCE.toAccountDetailsId(accountDetailsIdDTO));

        return new ResponseEntity<>(accountDetailsIdDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление существующего объекта AccountDetailsId.",
            description = "Удаление существующего объекта AccountDetailsId."
    )
    public ResponseEntity<HttpStatus> deleteAccountDetailsId(@PathVariable Long id) {
        accountDetailsIdService.deleteAccountDetailsId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
